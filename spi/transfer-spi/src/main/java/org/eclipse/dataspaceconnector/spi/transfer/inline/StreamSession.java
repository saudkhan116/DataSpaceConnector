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
 *       Amadeus - Initial implementation
 *
 */

package org.eclipse.dataspaceconnector.spi.transfer.inline;

/**
 * A stateful connection to a streaming topic.
 */
public interface StreamSession extends AutoCloseable {

    /**
     * Publishes the data.
     */
    void publish(byte[] data);

    /**
     * Closes the session, releasing acquired resources.
     */
    void close();
}
