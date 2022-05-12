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

package org.eclipse.dataspaceconnector.catalog.cache.loader;

import org.eclipse.dataspaceconnector.catalog.spi.Loader;
import org.eclipse.dataspaceconnector.catalog.spi.LoaderManager;
import org.eclipse.dataspaceconnector.catalog.spi.model.UpdateResponse;
import org.eclipse.dataspaceconnector.spi.monitor.Monitor;
import org.eclipse.dataspaceconnector.spi.retry.WaitStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.String.format;

public class LoaderManagerImpl implements LoaderManager {
    private static final int DEFAULT_BATCH_SIZE = 5;
    private static final int DEFAULT_WAIT_TIME_MILLIS = 2000;
    private final List<Loader> loaders;
    private final AtomicBoolean isRunning;
    private final ReentrantLock lock;
    private final int batchSize;
    private final WaitStrategy waitStrategy;
    private final Monitor monitor;
    private BlockingQueue<UpdateResponse> queue;
    private ExecutorService executor;

    protected LoaderManagerImpl(List<Loader> loaders, int batchSize, WaitStrategy waitStrategy, Monitor monitor) {
        this.loaders = loaders;
        this.batchSize = batchSize;
        this.waitStrategy = waitStrategy;
        this.monitor = monitor;
        isRunning = new AtomicBoolean(false);
        lock = new ReentrantLock();
    }

    public int getBatchSize() {
        return batchSize;
    }

    @Override
    public void start(BlockingQueue<UpdateResponse> queue) {
        this.queue = queue;
        isRunning.set(true);
        executor = Executors.newSingleThreadExecutor();
        executor.submit(this::beginDequeue);
    }

    @Override
    public void stop() {
        isRunning.set(false);
        if (executor != null) {
            executor.shutdownNow();
        }
    }

    @Override
    public void addLoader(Loader loader) {
        loaders.add(loader);
    }

    private void beginDequeue() {
        while (isRunning.get()) {
            boolean isBatchFull = true;
            try {
                lock.lock();
                isBatchFull = queue.size() >= batchSize;

                if (isBatchFull) {
                    var batch = new ArrayList<UpdateResponse>(batchSize);
                    // take the elements out of the queue and forward to loaders
                    queue.drainTo(batch, batchSize);
                    monitor.debug(format("LoaderManager: batch full, begin loading (%s items, %s workers)", batchSize, loaders.size()));
                    loaders.forEach(Loader::clear);
                    loaders.forEach(l -> l.load(batch));
                    monitor.debug("LoaderManager: loading complete");
                }
                // else wait and retry on next iteration
            } finally {
                if (!isBatchFull) {
                    //monitor.debug(format("LoaderManager: batch not full (%s/%s), yield", queue.size(), batchSize));
                    try {
                        long l = waitStrategy.retryInMillis();
                        Thread.sleep(l);
                    } catch (InterruptedException e) {
                        isRunning.set(false);
                    }
                } else {
                    waitStrategy.success();
                }
                lock.unlock();
            }
        }
    }


    public static final class Builder {
        private List<Loader> loaders;
        private int batchSize = DEFAULT_BATCH_SIZE;
        private WaitStrategy waitStrategy = () -> DEFAULT_WAIT_TIME_MILLIS;
        private Monitor monitor;

        private Builder() {
        }

        public static Builder newInstance() {
            return new Builder();
        }


        public Builder loaders(List<Loader> loaders) {
            this.loaders = loaders;
            return this;
        }

        public Builder batchSize(int batchSize) {
            this.batchSize = batchSize;
            return this;
        }

        public Builder monitor(Monitor monitor) {
            this.monitor = monitor;
            return this;
        }

        public Builder waitStrategy(WaitStrategy waitStrategy) {
            this.waitStrategy = waitStrategy;
            return this;
        }

        public LoaderManagerImpl build() {
            Objects.requireNonNull(loaders);
            if (batchSize < 0) {
                throw new IllegalArgumentException("Batch Size cannot be negative!");
            }
            return new LoaderManagerImpl(loaders, batchSize, waitStrategy, monitor);
        }
    }
}
