/*
 *  Copyright (c) 2022 ZF Friedrichshafen AG
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       ZF Friedrichshafen AG - Initial API and Implementation
 *
 */

package org.eclipse.dataspaceconnector.sql.policy;

import org.eclipse.dataspaceconnector.spi.EdcSetting;
import org.eclipse.dataspaceconnector.spi.policy.store.PolicyStore;
import org.eclipse.dataspaceconnector.spi.system.Inject;
import org.eclipse.dataspaceconnector.spi.system.Provides;
import org.eclipse.dataspaceconnector.spi.system.ServiceExtension;
import org.eclipse.dataspaceconnector.spi.system.ServiceExtensionContext;
import org.eclipse.dataspaceconnector.spi.transaction.TransactionContext;
import org.eclipse.dataspaceconnector.spi.transaction.datasource.DataSourceRegistry;
import org.eclipse.dataspaceconnector.sql.policy.store.PostgressStatements;
import org.eclipse.dataspaceconnector.sql.policy.store.SqlPolicyStore;
import org.eclipse.dataspaceconnector.sql.policy.store.SqlPolicyStoreStatements;

@Provides(PolicyStore.class)
public class SqlPolicyStoreExtension implements ServiceExtension {

    @EdcSetting(required = true)
    private static final String DATASOURCE_SETTING_NAME = "edc.datasource.policy.name";

    @Inject
    private DataSourceRegistry dataSourceRegistry;

    @Inject
    private TransactionContext transactionContext;

    @Inject(required = false)
    private SqlPolicyStoreStatements statements;

    @Override
    public void initialize(ServiceExtensionContext context) {

        var sqlPolicyStore = new SqlPolicyStore(dataSourceRegistry, getDataSourceName(context), transactionContext, context.getTypeManager(), getStatementImpl());

        context.registerService(PolicyStore.class, sqlPolicyStore);
    }

    /**
     * returns an externally-provided sql statement dialect, or postgres as a default
     */
    private SqlPolicyStoreStatements getStatementImpl() {
        return statements != null ? statements : new PostgressStatements();
    }

    private String getDataSourceName(ServiceExtensionContext context) {
        return context.getConfig().getString(DATASOURCE_SETTING_NAME);
    }
}
