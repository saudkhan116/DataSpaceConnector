/*
 *  Copyright (c) 2021 Microsoft Corporation
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Microsoft Corporation - Initial implementation
 *
 */

package org.eclipse.dataspaceconnector.core.defaults.assetindex;

import org.assertj.core.api.Assertions;
import org.eclipse.dataspaceconnector.spi.types.domain.DataAddress;
import org.eclipse.dataspaceconnector.spi.types.domain.asset.Asset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InMemoryDataAddressResolverTest {
    private InMemoryAssetIndex resolver;


    @BeforeEach
    void setUp() {
        resolver = new InMemoryAssetIndex();
    }

    @Test
    void resolveForAsset() {
        String id = UUID.randomUUID().toString();
        var testAsset = createAsset("foobar", id);
        DataAddress address = createDataAddress(testAsset);
        resolver.accept(testAsset, address);

        Assertions.assertThat(resolver.resolveForAsset(testAsset.getId())).isEqualTo(address);
    }

    @Test
    void resolveForAsset_assetNull_raisesException() {
        String id = UUID.randomUUID().toString();
        var testAsset = createAsset("foobar", id);
        DataAddress address = createDataAddress(testAsset);
        resolver.accept(testAsset, address);

        assertThatThrownBy(() -> resolver.resolveForAsset(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    void resolveForAsset_whenAssetDeleted_raisesException() {
        var testAsset = createAsset("foobar", UUID.randomUUID().toString());
        var address = createDataAddress(testAsset);
        resolver.accept(testAsset, address);

        resolver.deleteById(testAsset.getId());
        assertThatThrownBy(() -> resolver.resolveForAsset(testAsset.getId()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("No DataAddress found for Asset ID=%s", testAsset.getId()));
    }

    private Asset createAsset(String name, String id) {
        return Asset.Builder.newInstance().id(id).name(name).version("1").contentType("type").build();
    }

    private DataAddress createDataAddress(Asset asset) {
        return DataAddress.Builder.newInstance()
                .keyName("test-keyname")
                .type(asset.getContentType())
                .build();
    }
}
