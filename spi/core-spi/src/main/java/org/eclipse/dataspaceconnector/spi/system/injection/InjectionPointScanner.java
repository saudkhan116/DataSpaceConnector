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

package org.eclipse.dataspaceconnector.spi.system.injection;

import org.eclipse.dataspaceconnector.spi.system.Feature;
import org.eclipse.dataspaceconnector.spi.system.Inject;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Scans a particular (partly constructed) object for fields that are annotated with {@link Inject} and returns them
 * in a {@link Set}
 */
public class InjectionPointScanner {
    public <T> Set<InjectionPoint<T>> getInjectionPoints(T instance) {

        var targetClass = instance.getClass();

        return Arrays.stream(targetClass.getDeclaredFields())
                .filter(f -> f.getAnnotation(Inject.class) != null)
                .map(f -> {
                    var isRequired = f.getAnnotation(Inject.class).required();
                    return new FieldInjectionPoint<>(instance, f, getFeatureValue(f.getType()), isRequired);
                })
                .collect(Collectors.toSet());
    }

    private String getFeatureValue(Class<?> featureClass) {
        var annotation = featureClass.getAnnotation(Feature.class);
        if (annotation == null) {
            return featureClass.getName();
        }
        return annotation.value();
    }

}
