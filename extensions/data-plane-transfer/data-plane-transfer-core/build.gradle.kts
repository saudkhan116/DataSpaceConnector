/*
 *  Copyright (c) 2022 Amadeus
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Amadeus - initial API and implementation
 *
 */

plugins {
    `java-library`
}

val nimbusVersion: String by project

dependencies {
    api(project(":spi:core-spi"))
    api(project(":spi:transfer-spi"))
    api(project(":extensions:data-plane-transfer:data-plane-transfer-spi"))
    api(project(":common:token-generation-lib"))

    api("com.nimbusds:nimbus-jose-jwt:${nimbusVersion}")
}

publishing {
    publications {
        create<MavenPublication>("data-plane-transfer-core") {
            artifactId = "data-plane-transfer-core"
            from(components["java"])
        }
    }
}
