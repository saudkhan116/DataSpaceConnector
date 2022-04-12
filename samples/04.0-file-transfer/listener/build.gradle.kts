/*
 *  Copyright (c) 2020, 2021 Microsoft Corporation
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

<<<<<<<< HEAD:samples/04.0-file-transfer/listener/build.gradle.kts
plugins {
    `java-library`
    id("application")
========
package org.eclipse.dataspaceconnector.ids.spi;

public interface IdsConstants {
    String IDS_WEBHOOK_ADDRESS_PROPERTY = "idsWebhookAddress";
>>>>>>>> 98038f7e6bb101d81f96096a58d5a402f3494cc7:data-protocols/ids/ids-spi/src/main/java/org/eclipse/dataspaceconnector/ids/spi/IdsConstants.java
}

dependencies {
    api(project(":spi"))
    implementation(project(":extensions:http"))
}