/*
 * Copyright 2000-2015 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.webdemo.backend

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.jetbrains.webdemo.ErrorWriter
import org.jetbrains.webdemo.Project
import org.jetbrains.webdemo.ResponseUtils
import org.jetbrains.webdemo.backend.executor.ExecutorUtils
import org.jetbrains.webdemo.backend.executor.result.ExecutionResult
import org.jetbrains.webdemo.kotlin.KotlinWrappersManager
import org.jetbrains.webdemo.kotlin.datastructures.ErrorDescriptor
import org.jetbrains.webdemo.kotlin.datastructures.TranslationResult
import java.io.IOException
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class MyHttpSession {
    private val objectMapper = jacksonObjectMapper()

    fun handle(request: HttpServletRequest, response: HttpServletResponse) {
        try {
            when (request.getParameter("type")) {
                "run" -> sendExecutorResult(request, response)
                "highlight" -> sendHighlightingResult(request, response)
                "convertToKotlin" -> sendConversionResult(request, response)
                "complete" -> sendCompletionResult(request, response)
                "getKotlinVersions" -> sendKotlinVersions(response)
            }
        } catch (e: Throwable) {
            ErrorWriter.ERROR_WRITER.writeExceptionToExceptionAnalyzer(e, "")
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error")
        }

    }

    private fun sendKotlinVersions(response: HttpServletResponse) {
        try {
            val message = objectMapper.writeValueAsString(KotlinWrappersManager.wrappersConfig)
            response.sendResponse(HttpServletResponse.SC_OK, message)
        } catch (e: IOException) {
            ErrorWriter.ERROR_WRITER.writeExceptionToExceptionAnalyzer(e, "Can't send kotlin versions")
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error")
        }

    }

    private fun sendConversionResult(request: HttpServletRequest, response: HttpServletResponse) {
        try {
            val currentProject = objectMapper.readValue(request.getParameter("project"), Project::class.java)
            val wrapper = KotlinWrappersManager.getKotlinWrapper(currentProject!!.compilerVersion)
            val javaCode = request.getParameter("text")
            val kotlinCode = wrapper!!.translateJavaToKotlin(javaCode)
            response.sendResponse(HttpServletResponse.SC_OK, kotlinCode)
        } catch (e: Exception) {
            ErrorWriter.ERROR_WRITER.writeExceptionToExceptionAnalyzer(e, "Error during java to kotlin translation")
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error")
        }

    }

    private fun sendExecutorResult(request: HttpServletRequest, response: HttpServletResponse) {
        try {
            val currentProject = objectMapper.readValue<Project>(request.getParameter("project"))
            val wrapper = KotlinWrappersManager.getKotlinWrapper(currentProject.compilerVersion)

            val files = getFilesContentFromProject(currentProject)
            val isJs = currentProject.confType == "js" || currentProject.confType == "canvas"
            val errorDescriptors = wrapper!!.getErrors(files, isJs)

            if (currentProject.confType != "js" && currentProject.confType != "canvas") {
                var executionResult: ExecutionResult? = null
                if (isOnlyWarnings(errorDescriptors)) {
                    val compilationResult = wrapper.compileCorrectFiles(getFilesContentFromProject(currentProject))
                    executionResult = ExecutorUtils.executeCompiledFiles(
                            compilationResult.files,
                            compilationResult.mainClass,
                            wrapper.kotlinRuntimeLibraries,
                            currentProject.args,
                            wrapper.wrapperFolder.resolve("executors.policy"),
                            currentProject.confType == "junit")
                    executionResult.addWarningsFromCompiler(errorDescriptors)
                    //TODO add method positions for junit tests
                } else {
                    executionResult = ExecutionResult(errorDescriptors)
                }

                val message = objectMapper.writeValueAsString(executionResult)
                response.sendResponse(HttpServletResponse.SC_OK, message)
            } else {
                val translationResult: TranslationResult
                if (isOnlyWarnings(errorDescriptors)) {
                    translationResult = wrapper.compileKotlinToJS(files, ResponseUtils.splitArguments(currentProject.args))
                    translationResult.addWarningsFromAnalyzer(errorDescriptors)
                } else {
                    translationResult = TranslationResult(errorDescriptors)
                }
                response.sendResponse(HttpServletResponse.SC_OK, objectMapper.writeValueAsString(translationResult))
            }
        } catch (e: IOException) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Can't parse project")
        } catch (e: NullPointerException) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Can't get parameters")
        } catch (e: Exception) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.message ?: "")
        }

    }

    private fun isOnlyWarnings(map: Map<String, List<ErrorDescriptor>>): Boolean {
        for (errorDescriptors in map.values) {
            for (errorDescriptor in errorDescriptors) {
                if (errorDescriptor.severity == org.jetbrains.webdemo.kotlin.datastructures.Severity.ERROR) {
                    return false
                }
            }
        }
        return true
    }

    fun sendCompletionResult(request: HttpServletRequest, response: HttpServletResponse) {
        try {
            val fileName = request.getParameter("filename")
            val line = Integer.parseInt(request.getParameter("line"))
            val ch = Integer.parseInt(request.getParameter("ch"))
            val currentProject = objectMapper.readValue(request.getParameter("project"), Project::class.java)
            val wrapper = KotlinWrappersManager.getKotlinWrapper(currentProject!!.compilerVersion)

            val files = getFilesContentFromProject(currentProject)
            val isJs = currentProject.confType == "js" || currentProject.confType == "canvas"
            val completionVariants = wrapper!!.getCompletionVariants(files, fileName, line, ch, isJs)
            response.sendResponse(HttpServletResponse.SC_OK, objectMapper.writeValueAsString(completionVariants))
        } catch (e: IOException) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Can't parse project")
        } catch (e: NullPointerException) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Can't get parameters")
        }

    }

    fun sendHighlightingResult(request: HttpServletRequest, response: HttpServletResponse) {
        try {
            val currentProject: Project = objectMapper.readValue<Project>(request.getParameter("project"))
            val wrapper = KotlinWrappersManager.getKotlinWrapper(currentProject.compilerVersion)
            if(wrapper == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "")
                return;
            }
            val files = getFilesContentFromProject(currentProject)
            val isJs = currentProject.confType == "js" || currentProject.confType == "canvas"
            val analysisResult = wrapper.getErrors(files, isJs)
            val result = objectMapper.writeValueAsString(analysisResult)
            response.sendResponse(HttpServletResponse.SC_OK, result.replace("\\n".toRegex(), ""))
        } catch (e: IOException) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Can't parse project")
        } catch (e: NullPointerException) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Can't get parameters")
        }

    }

    private fun getFilesContentFromProject(project: Project): Map<String, String> {
        val result = HashMap<String, String>()
        for (file in project.files) {
            result.put(file.name, file.text)
        }
        return result
    }
}


fun HttpServletResponse.sendResponse(statusCode: Int, message: String = "") {
    try {
        characterEncoding = "UTF-8"
        status = statusCode
        if (message != "") {
            writer.use { it.write(message) }
        }
    } catch (e: IOException) {
        //This is an exception we can't send data to client
        ErrorWriter.ERROR_WRITER.writeExceptionToExceptionAnalyzer(e, "Can't send data to client")
    }
}