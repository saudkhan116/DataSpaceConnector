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
 *       Microsoft Corporation - Initial implementation
 *
 */

package org.eclipse.dataspaceconnector.transaction.atomikos;

import org.eclipse.dataspaceconnector.spi.EdcException;
import org.eclipse.dataspaceconnector.spi.monitor.Monitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.transaction.Status;
import javax.transaction.Transaction;
import javax.transaction.TransactionManager;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AtomikosTransactionContextTest {
    private AtomikosTransactionContext transactionContext;
    private TransactionManager transactionManager;
    private Transaction transaction;

    @Test
    void verifyCommit() throws Exception {
        when(transactionManager.getTransaction()).thenReturn(null, transaction);

        transactionContext.execute(() -> {
        });

        verify(transactionManager, times(1)).begin();
        verify(transactionManager, times(1)).commit();
    }

    @Test
    void verifyJoinCommit() throws Exception {
        when(transactionManager.getTransaction()).thenReturn(null, transaction);

        transactionContext.execute(() -> {
            transactionContext.execute(() -> {
            });
        });

        verify(transactionManager, times(1)).begin();
        verify(transactionManager, times(1)).commit();
    }

    @Test
    void verifyJoinRollback() throws Exception {
        when(transactionManager.getTransaction()).thenReturn(null, transaction);
        when(transactionManager.getStatus()).thenReturn(Status.STATUS_MARKED_ROLLBACK);

        assertThatExceptionOfType(EdcException.class)
                .isThrownBy(() ->
                        transactionContext.execute(() -> {
                            transactionContext.execute(() -> {
                                throw new RuntimeException();
                            });
                        }));

        verify(transactionManager, times(1)).begin();
        verify(transactionManager, times(1)).rollback();
    }

    @Test
    void verifyRollback() throws Exception {
        when(transactionManager.getTransaction()).thenReturn(null, transaction);
        when(transactionManager.getStatus()).thenReturn(Status.STATUS_MARKED_ROLLBACK);
        assertThatExceptionOfType(EdcException.class)
                .isThrownBy(() ->
                        transactionContext.execute(() -> {
                            throw new RuntimeException();
                        }));

        verify(transaction, times(1)).setRollbackOnly();
        verify(transactionManager, times(1)).rollback();
    }

    @BeforeEach
    void setUp() {
        transactionContext = new AtomikosTransactionContext(mock(Monitor.class));
        transactionManager = mock(TransactionManager.class);
        transactionContext.initialize(transactionManager);
        transaction = mock(Transaction.class);
    }
}
