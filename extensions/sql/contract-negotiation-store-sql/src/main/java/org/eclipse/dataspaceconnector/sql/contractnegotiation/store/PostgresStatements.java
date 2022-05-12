/*
 *  Copyright (c) 2020 - 2022 Microsoft Corporation
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

package org.eclipse.dataspaceconnector.sql.contractnegotiation.store;

import static java.lang.String.format;

/**
 * Provides statements required by the ContractNegotiationStore in Postgres dialect
 */
public final class PostgresStatements implements ContractNegotiationStatements {
    @Override
    public String getFindTemplate() {
        return format("SELECT * FROM %s LEFT OUTER JOIN %s ON %s.%s = %s.%s WHERE %s.%s = ?;", getContractNegotiationTable(), getContractAgreementTable(),
                getContractNegotiationTable(), getContractAgreementIdFkColumn(), getContractAgreementTable(), getContractAgreementIdColumn(), getContractNegotiationTable(), getIdColumn());
    }

    @Override
    public String getFindByCorrelationIdTemplate() {
        return format("SELECT * FROM %s LEFT OUTER JOIN %s ON %s.%s = %s WHERE %s.%s = ?;", getContractNegotiationTable(), getContractAgreementTable(), getContractNegotiationTable(), getContractAgreementIdFkColumn(),
                getContractAgreementIdColumn(), getContractNegotiationTable(), getCorrelationIdColumn());
    }

    @Override
    public String getFindContractAgreementTemplate() {
        return format("SELECT * FROM %s where %s=?;", getContractAgreementTable(), getContractAgreementIdColumn());
    }

    @Override
    public String getFindContractAgreementByDefinitionIdTemplate() {
        return format("SELECT * FROM %s where %s LIKE ?", getContractAgreementTable(), getContractAgreementIdColumn());
    }

    @Override
    public String getUpdateNegotiationTemplate() {
        return format("UPDATE %s\n" +
                        "SET %s=?,\n" +
                        "    %s=?,\n" +
                        "    %s=?,\n" +
                        "    %s=?,\n" +
                        "    %s=?,\n" +
                        "    %s=?,\n" +
                        "    %s=?\n" +
                        "WHERE id = ?;", getContractNegotiationTable(), getStateColumn(), getStateCountColumn(), getStateTimestampColumn(),
                getErrorDetailColumn(), getContractOffersColumn(), getTraceContextColumn(), getContractAgreementIdFkColumn());
    }

    @Override
    public String getInsertNegotiationTemplate() {
        return format("INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)\n" +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ",
                getContractNegotiationTable(), getIdColumn(), getCorrelationIdColumn(), getCounterPartyIdColumn(), getCounterPartyAddressColumn(), getTypeColumn(), getProtocolColumn(), getStateColumn(), getStateCountColumn(),
                getStateTimestampColumn(), getErrorDetailColumn(), getContractAgreementIdFkColumn(), getContractOffersColumn(), getTraceContextColumn()
        );
    }

    @Override
    public String getDeleteTemplate() {
        return format("DELETE FROM %s WHERE %s = ? AND %s IS NULL;", getContractNegotiationTable(), getIdColumn(), getContractAgreementIdFkColumn());
    }

    @Override
    public String getNextForStateTemplate() {
        return format("SELECT * FROM %s\n" +
                "WHERE %s=?\n" +
                "  AND (%s IS NULL OR %s IN (SELECT %s FROM %s WHERE (? > (%s + %s))))\n" +
                "LIMIT ?;", getContractNegotiationTable(), getStateColumn(), getLeaseIdColumn(), getLeaseIdColumn(), getLeaseIdColumn(), getLeaseTableName(), getLeasedAtColumn(), getLeaseDurationColumn());
    }

    @Override
    public String getQueryNegotiationsTemplate() {
        // todo: add WHERE ... AND ... ORDER BY... statements here
        return format("SELECT * FROM %s LEFT OUTER JOIN %s ON %s.%s = %s.%s LIMIT ? OFFSET ?;", getContractNegotiationTable(), getContractAgreementTable(),
                getContractNegotiationTable(), getContractAgreementIdFkColumn(), getContractAgreementTable(), getContractAgreementIdColumn());
    }

    @Override
    public String getQueryAgreementsTemplate() {
        // todo: add WHERE ... AND ... ORDER BY... statements here
        return format("SELECT * FROM %s LIMIT ? OFFSET ?;", getContractAgreementTable());
    }

    @Override
    public String getInsertAgreementTemplate() {
        return format("INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?, ?, ?);",
                getContractAgreementTable(), getContractAgreementIdColumn(), getProviderAgentColumn(), getConsumerAgentColumn(),
                getSigningDateColumn(), getStartDateColumn(), getEndDateColumn(), getAssetIdColumn(), getPolicyIdColumn());
    }

    @Override
    public String getSelectByPolicyIdTemplate() {
        return format("SELECT DISTINCT %s FROM %s WHERE %s = ?", getPolicyColumnSeralized(), getContractAgreementTable(), getPolicyIdColumn());
    }

    @Override
    public String getUpdateAgreementTemplate() {
        return format("UPDATE %s SET %s=?, %s=?, %s=?, %s=?, %s=?, %s=?, %s=? WHERE %s =?",
                getContractAgreementTable(), getProviderAgentColumn(), getConsumerAgentColumn(), getSigningDateColumn(),
                getStartDateColumn(), getEndDateColumn(), getAssetIdColumn(), getPolicyIdColumn(), getContractAgreementIdColumn());
    }

    @Override
    public String getDeleteLeaseTemplate() {
        return format("DELETE FROM %s WHERE %s=?", getLeaseTableName(), getLeaseIdColumn());
    }

    @Override
    public String getInsertLeaseTemplate() {
        return format("INSERT INTO %s (%s, %s, %s, %s) VALUES (?, ?, ?, ?);",
                getLeaseTableName(), getLeaseIdColumn(), getLeasedByColumn(), getLeasedAtColumn(), getLeaseDurationColumn());
    }

    @Override
    public String getUpdateLeaseTemplate() {
        return format("UPDATE %s SET %s=? WHERE %s = ?;", getContractNegotiationTable(), getLeaseIdColumn(), getIdColumn());
    }

    @Override
    public String getFindLeaseByEntityTemplate() {
        return format("SELECT * FROM %s  WHERE %s = (SELECT lease_id FROM %s WHERE %s=? )",
                getLeaseTableName(), getLeaseIdColumn(), getContractNegotiationTable(), getIdColumn());
    }

}
