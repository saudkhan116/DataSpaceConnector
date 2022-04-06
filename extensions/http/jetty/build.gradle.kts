/*
 *  Copyright (c) 2021 Copyright Holder (Catena-X Consortium)
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *      Catena-X Consortium - initial API and implementation
 *      Fraunhofer Institute for Software and Systems Engineering - added dependency
 *
 */

plugins {
    `java-library`
}

val jettyVersion: String by project
val jerseyVersion: String by project
val okHttpVersion: String by project

dependencies {
    implementation("org.eclipse.jetty.websocket:websocket-jakarta-server:${jettyVersion}")

    api(project(":spi:core-spi"))
    api(project(":spi:web-spi"))

    testImplementation("com.squareup.okhttp3:okhttp:${okHttpVersion}")
    testImplementation("org.glassfish.jersey.core:jersey-server:${jerseyVersion}")
    testImplementation("org.glassfish.jersey.containers:jersey-container-servlet-core:${jerseyVersion}")
    testImplementation("org.glassfish.jersey.core:jersey-common:${jerseyVersion}")
    testImplementation("org.glassfish.jersey.media:jersey-media-json-jackson:${jerseyVersion}")
    testImplementation("org.glassfish.jersey.media:jersey-media-multipart:${jerseyVersion}")
    testImplementation("org.glassfish.jersey.inject:jersey-hk2:${jerseyVersion}")
    testImplementation("org.glassfish.jersey.containers:jersey-container-servlet:${jerseyVersion}")
    testImplementation(testFixtures(project(":common:util")))
}

publishing {
    publications {
        create<MavenPublication>("jetty") {
            artifactId = "jetty"
            from(components["java"])
        }
    }
}
