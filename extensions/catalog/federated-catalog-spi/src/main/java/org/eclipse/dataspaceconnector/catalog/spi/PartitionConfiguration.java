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

package org.eclipse.dataspaceconnector.catalog.spi;

import org.eclipse.dataspaceconnector.catalog.spi.model.ExecutionPlan;
import org.eclipse.dataspaceconnector.catalog.spi.model.RecurringExecutionPlan;
import org.eclipse.dataspaceconnector.spi.EdcSetting;
import org.eclipse.dataspaceconnector.spi.system.ServiceExtensionContext;

import java.time.Duration;
import java.util.Random;

/**
 * Object that provides configuration for the {@link PartitionManager}.
 * All configuration values that do not allow for default values are resolved instantly, all others are resolved
 * lazily from the context.
 */
public class PartitionConfiguration {

    @EdcSetting
    private static final String PART_WORK_ITEM_QUEUE_SIZE_SETTING = "edc.catalog.cache.partition.queue.size";
    @EdcSetting
    private static final String PART_NUM_CRAWLER_SETTING = "edc.catalog.cache.partition.num.crawlers";
    @EdcSetting
    private static final String PART_LOADER_BATCH_SIZE_SETTING = "edc.catalog.cache.loader.batch.size";
    @EdcSetting
    private static final String PART_LOADER_RETRY_TIMEOUT = "edc.catalog.cache.loader.timeout.millis";
    @EdcSetting
    private static final String PART_EXECUTION_PLAN_PERIOD_SECONDS = "edc.catalog.cache.execution.period.seconds";
    @EdcSetting
    private static final String PART_EXECUTION_PLAN_DELAY_SECONDS = "edc.catalog.cache.execution.delay.seconds";
    private static final int DEFAULT_EXECUTION_PERIOD_SECONDS = 60;
    private final ServiceExtensionContext context;

    public PartitionConfiguration(ServiceExtensionContext context) {
        this.context = context;
    }

    public int getWorkItemQueueSize(int defaultValue) {
        return context.getSetting(PART_WORK_ITEM_QUEUE_SIZE_SETTING, defaultValue);
    }

    public int getNumCrawlers(int defaultValue) {
        return context.getSetting(PART_NUM_CRAWLER_SETTING, defaultValue);
    }

    public int getLoaderBatchSize(int defaultValue) {
        return context.getSetting(PART_LOADER_BATCH_SIZE_SETTING, defaultValue);
    }

    public long getLoaderRetryTimeout(int defaultValue) {
        return context.getSetting(PART_LOADER_RETRY_TIMEOUT, defaultValue);
    }

    public ExecutionPlan getExecutionPlan() {
        var periodSeconds = context.getSetting(PART_EXECUTION_PLAN_PERIOD_SECONDS, DEFAULT_EXECUTION_PERIOD_SECONDS);
        var setting = context.getSetting(PART_EXECUTION_PLAN_DELAY_SECONDS, null);
        int initialDelaySeconds;
        if ("random".equals(setting) || setting == null) {
            initialDelaySeconds = randomSeconds();
        } else {
            try {
                initialDelaySeconds = Integer.parseInt(setting);
            } catch (NumberFormatException ex) {
                initialDelaySeconds = 0;
            }
        }
        return new RecurringExecutionPlan(Duration.ofSeconds(periodSeconds), Duration.ofSeconds(initialDelaySeconds));
    }

    private int randomSeconds() {
        var rnd = new Random();
        return 10 + rnd.nextInt(90);
    }
}
