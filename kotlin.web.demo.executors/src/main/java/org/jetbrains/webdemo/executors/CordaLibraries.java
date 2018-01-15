/*
 * Copyright r3
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

package org.jetbrains.webdemo.executors;

import java.util.Arrays;
import java.util.List;

public class CordaLibraries {
    public static List<String> cordaLibraries = Arrays.asList(
            "HikariCP-2.5.1.jar",
            "annotations-13.0.jar",
            "antlr-2.7.7.jar",
            "aopalliance-repackaged-2.5.0-b30.jar",
            "artemis-amqp-protocol-2.1.0.jar",
            "artemis-commons-2.1.0.jar",
            "artemis-core-client-2.1.0.jar",
            "artemis-jdbc-store-2.1.0.jar",
            "artemis-jms-client-2.1.0.jar",
            "artemis-journal-2.1.0.jar",
            "artemis-native-2.1.0.jar",
            "artemis-selector-2.1.0.jar",
            "artemis-server-2.1.0.jar",
            "asm-5.0.4.jar",
            "bcpkix-jdk15on-1.57.jar",
            "bcprov-jdk15on-1.57.jar",
            "byte-buddy-1.5.12.jar",
            "byte-buddy-agent-1.5.12.jar",
            "catalyst-buffer-1.1.2.jar",
            "catalyst-common-1.1.2.jar",
            "catalyst-concurrent-1.1.2.jar",
            "catalyst-netty-1.1.2.jar",
            "catalyst-serializer-1.1.2.jar",
            "catalyst-transport-1.1.2.jar",
            "cdi-api-1.1.jar",
            "classmate-1.3.0.jar",
            "commons-beanutils-1.9.2.jar",
            "commons-codec-1.10.jar",
            "commons-collections-3.2.1.jar",
            "commons-fileupload-1.3.2.jar",
            "commons-io-2.2.jar",
            "commons-jexl3-3.0.jar",
            "commons-logging-1.2.jar",
            "compilers.jar",
            "config-1.3.1.jar",
            "copycat-client-1.2.3.jar",
            "copycat-protocol-1.2.3.jar",
            "copycat-server-1.2.3.jar",
            "corda-1.0.0.jar",
            "corda-confidential-identities-1.0.0.jar",
            "corda-core-1.0.0.jar",
            "corda-finance-1.0.0.jar",
            "corda-jackson-1.0.0.jar",
            "corda-mock-1.0.0.jar",
            "corda-node-1.0.0.jar",
            "corda-node-api-1.0.0.jar",
            "corda-node-driver-1.0.0.jar",
            "corda-rpc-1.0.0.jar",
            "corda-test-common-1.0.0.jar",
            "corda-test-utils-1.0.0.jar",
            "corda-webserver-1.0.0.jar",
            "corda-webserver-impl-1.0.0.jar",
            "cordform-common-1.0.0.jar",
            "crash.cli-9d242da2a10e686f33a3aefc69e4768824ad0716.jar",
            "crash.shell-9d242da2a10e686f33a3aefc69e4768824ad0716.jar",
            "dom4j-1.6.1.jar",
            "eddsa-0.2.0.jar",
            "el-api-2.2.jar",
            "error_prone_annotations-2.0.15.jar",
            "fast-classpath-scanner-2.0.21.jar",
            "forms-1.1-preview.jar",
            "forms_rt-7.0.3.jar",
            "geronimo-jms_2.0_spec-1.0-alpha-2.jar",
            "geronimo-json_1.0_spec-1.0-alpha-1.jar",
            "geronimo-jta_1.1_spec-1.1.1.jar",
            "groovy-all-1.8.9.jar",
            "guava-21.0.jar",
            "guava-testlib-21.0.jar",
            "h2-1.4.194.jar",
            "hamcrest-core-1.3.jar",
            "hibernate-commons-annotations-5.0.1.Final.jar",
            "hibernate-core-5.2.6.Final.jar",
            "hibernate-java8-5.2.6.Final.jar",
            "hibernate-jpa-2.1-api-1.0.0.Final.jar",
            "hk2-api-2.5.0-b30.jar",
            "hk2-locator-2.5.0-b30.jar",
            "hk2-utils-2.5.0-b30.jar",
            "jackson-annotations-2.8.5.jar",
            "jackson-core-2.8.5.jar",
            "jackson-databind-2.8.5.jar",
            "jackson-dataformat-yaml-2.8.5.jar",
            "jackson-datatype-jsr310-2.8.5.jar",
            "jackson-jaxrs-base-2.8.4.jar",
            "jackson-jaxrs-json-provider-2.8.4.jar",
            "jackson-module-jaxb-annotations-2.8.4.jar",
            "jackson-module-kotlin-2.8.5.jar",
            "jandex-2.0.3.Final.jar",
            "jansi-1.14.jar",
            "javassist-3.20.0-GA.jar",
            "javax.annotation-api-1.2.jar",
            "javax.inject-1.jar",
            "javax.inject-2.5.0-b30.jar",
            "javax.servlet-api-3.1.0.jar",
            "javax.ws.rs-api-2.0.1.jar",
            "jboss-interceptors-api_1.1_spec-1.0.0.Beta1.jar",
            "jboss-logging-3.3.0.Final.jar",
            "jcabi-log-0.14.jar",
            "jcabi-manifests-1.1.jar",
            "jcl-over-slf4j-1.7.25.jar",
            "jdom-1.0.jar",
            "jersey-client-2.25.jar",
            "jersey-common-2.25.jar",
            "jersey-container-jetty-http-2.25.jar",
            "jersey-container-servlet-core-2.25.jar",
            "jersey-entity-filtering-2.25.jar",
            "jersey-guava-2.25.jar",
            "jersey-media-jaxb-2.25.jar",
            "jersey-media-json-jackson-2.25.jar",
            "jersey-server-2.25.jar",
            "jetty-continuation-9.2.14.v20151106.jar",
            "jetty-http-9.3.9.v20160517.jar",
            "jetty-io-9.3.9.v20160517.jar",
            "jetty-security-9.3.9.v20160517.jar",
            "jetty-server-9.3.9.v20160517.jar",
            "jetty-servlet-9.3.9.v20160517.jar",
            "jetty-util-9.3.9.v20160517.jar",
            "jetty-webapp-9.3.9.v20160517.jar",
            "jetty-xml-9.3.9.v20160517.jar",
            "jgroups-3.6.13.Final.jar",
            "jimfs-1.1.jar",
            "johnzon-core-0.9.5.jar",
            "jolokia-agent-war-2.0.0-M3.war",
            "jolokia-server-core-2.0.0-M3.jar",
            "jolokia-server-detector-2.0.0-M3.jar",
            "jolokia-service-discovery-2.0.0-M3.jar",
            "jolokia-service-history-2.0.0-M3.jar",
            "jolokia-service-jmx-2.0.0-M3.jar",
            "jolokia-service-jsr160-2.0.0-M3.jar",
            "jolokia-service-notif-pull-2.0.0-M3.jar",
            "jolokia-service-notif-sse-2.0.0-M3.jar",
            "jolokia-service-serializer-2.0.0-M3.jar",
            "jopt-simple-5.0.2.jar",
            "json-simple-1.1.1.jar",
            "jsr250-api-1.0.jar",
            "jsr305-3.0.1.jar",
            "jul-to-slf4j-1.7.25.jar",
            "junit-4.12.jar",
            "junixsocket-common-2.0.4.jar",
            "junixsocket-native-common-2.0.4.jar",
            "kotlin-reflect-1.1.4.jar",
            "kotlin-stdlib-1.1.4.jar",
            "kotlin-stdlib-jre7-1.1.4.jar",
            "kotlin-stdlib-jre8-1.1.4.jar",
            "kotlin-test-1.1.4.jar",
            "kotlin.web.demo.common.jar",
            "kotlin.web.demo.executors.jar",
            "kotlinx-html-jvm-0.6.3.jar",
            "kryo-4.0.0.jar",
            "kryo-serializers-0.41.jar",
            "library-master-v1.1-beta-g6215ec8-87.jar",
            "log4j-1.2.17.jar",
            "log4j-api-2.7.jar",
            "log4j-core-2.7.jar",
            "log4j-slf4j-impl-2.7.jar",
            "log4j-web-2.7.jar",
            "metrics-core-3.1.2.jar",
            "minlog-1.3.0.jar",
            "mockito-core-2.4.5.jar",
            "mockito-kotlin-1.1.0.jar",
            "native-lib-loader-2.0.2.jar",
            "netty-all-4.1.9.Final.jar",
            "objenesis-2.4.jar",
            "okhttp-3.5.0.jar",
            "okio-1.11.0.jar",
            "osgi-resource-locator-1.0.1.jar",
            "proton-j-0.21.0.jar",
            "quasar-core-0.7.9-jdk8.jar",
            "reflectasm-1.11.3.jar",
            "rxjava-1.2.4.jar",
            "slf4j-api-1.7.25.jar",
            "snakeyaml-1.17.jar",
            "validation-api-1.1.0.Final.jar");
}