/*
 *  Copyright (c) 2021 Daimler TSS GmbH
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Daimler TSS GmbH - Initial build file
 *
 */

plugins {
    `java-library`
    `java-test-fixtures`
    `maven-publish`
}

val apacheCommonsPool2Version: String by project
val mockitoVersion: String by project
val h2Version: String by project
val assertj: String by project

dependencies {
    api(project(":extensions:transaction:transaction-datasource-spi"))
    api(project(":extensions:transaction:transaction-spi"))
    api(project(":extensions:sql:common"))


    testImplementation(testFixtures(project(":launchers:junit")))
    testImplementation(project(":core:base"))
    testImplementation(project(":extensions:transaction:transaction-local"))
    testImplementation(testFixtures(project(":extensions:sql:lease")))
    testImplementation("com.h2database:h2:${h2Version}")
    testImplementation("org.assertj:assertj-core:${assertj}")
}

publishing {
    publications {
        create<MavenPublication>("sql-lease") {
            artifactId = "sql-lease"
            from(components["java"])
        }
    }
}
