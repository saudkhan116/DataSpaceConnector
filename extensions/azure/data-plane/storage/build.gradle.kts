/*
 *  Copyright (c) 2022 Microsoft Corporation
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Microsoft Corporation - initial API and implementation
 *
 */

val storageBlobVersion: String by project
val jodahFailsafeVersion: String by project

plugins {
    `java-library`
}


dependencies {
    api(project(":extensions:data-plane:data-plane-spi"))
    api(project(":extensions:azure:blobstorage:blob-core"))
    implementation(project(":common:util"))
    implementation("com.azure:azure-storage-blob:${storageBlobVersion}")
    implementation("net.jodah:failsafe:${jodahFailsafeVersion}")

    testImplementation(testFixtures(project(":extensions:azure:azure-test")))
    testImplementation(testFixtures(project(":extensions:azure:blobstorage:blob-core")))
    testImplementation(testFixtures(project(":common:util")))
}

publishing {
    publications {
        create<MavenPublication>("data-plane-azure-storage") {
            artifactId = "data-plane-azure-storage"
            from(components["java"])
        }
    }
}
