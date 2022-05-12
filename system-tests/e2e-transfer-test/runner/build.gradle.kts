/*
 *  Copyright (c) 2022 Bayerische Motoren Werke Aktiengesellschaft (BMW AG)
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Bayerische Motoren Werke Aktiengesellschaft (BMW AG) - initial API and implementation
 *       Bayerische Motoren Werke Aktiengesellschaft (BMW AG)
 *
 */

plugins {
    java
}

val jupiterVersion: String by project
val restAssured: String by project
val awaitility: String by project
val assertj: String by project

dependencies {
    testImplementation(project(":extensions:sql:common-sql"))

    testImplementation(testFixtures(project(":common:util")))
    testImplementation(testFixtures(project(":launchers:junit")))

    testImplementation("org.postgresql:postgresql:42.2.6")
    testImplementation("io.rest-assured:rest-assured:${restAssured}")
    testImplementation("org.assertj:assertj-core:${assertj}")
    testImplementation("org.awaitility:awaitility:${awaitility}")
    testImplementation("org.junit.jupiter:junit-jupiter-api:${jupiterVersion}")

    testCompileOnly(project(":system-tests:e2e-transfer-test:backend-service"))
    testCompileOnly(project(":system-tests:e2e-transfer-test:control-plane"))
    testCompileOnly(project(":system-tests:e2e-transfer-test:data-plane"))
}
