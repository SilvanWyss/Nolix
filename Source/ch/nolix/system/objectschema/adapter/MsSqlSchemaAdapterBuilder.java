/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectschema.adapter;

import ch.nolix.base.argumentcaptor.andargumentcaptor.AndLoginPasswordCaptor;
import ch.nolix.base.argumentcaptor.andargumentcaptor.AndPortCaptor;
import ch.nolix.base.argumentcaptor.toargumentcaptor.ToDatabaseNameCaptor;
import ch.nolix.base.argumentcaptor.toargumentcaptor.ToIpOrDomainCaptor;
import ch.nolix.base.argumentcaptor.withargumentcaptor.WithLoginNameCaptor;
import ch.nolix.base.sql.connectionpool.SqlConnectionPoolBuilder;
import ch.nolix.baseapi.sql.sqlproperty.SqlDatabaseEngine;

/**
 * @author Silvan Wyss
 */
public final class MsSqlSchemaAdapterBuilder
extends
ToIpOrDomainCaptor< //
AndPortCaptor< //
ToDatabaseNameCaptor< //
WithLoginNameCaptor< //
AndLoginPasswordCaptor< //
MsSqlSchemaAdapter>>>>> {
  private MsSqlSchemaAdapterBuilder() {
    super(
      new AndPortCaptor<>(
        new ToDatabaseNameCaptor<>(
          new WithLoginNameCaptor<>(
            new AndLoginPasswordCaptor<>()))));

    setBuilder(this::buildMsSqlSchemaAdapter);
  }

  public static MsSqlSchemaAdapterBuilder createMsSqlSchemaAdapter() {
    return new MsSqlSchemaAdapterBuilder();
  }

  private MsSqlSchemaAdapter buildMsSqlSchemaAdapter() {
    final var databaseName = nxtArgCpt().nxtArgCpt().getDatabaseName();

    return //
    new MsSqlSchemaAdapter(
      databaseName,
      ch.nolix.system.sqlmidschema.adapter.SqlSchemaAdapter
        .forDatabaseNameAndSqlConnection(
          databaseName,
          SqlConnectionPoolBuilder
            .createConnectionPool()
            .forIpOrDomain(getIpOrDomain())
            .andPort(nxtArgCpt().getPort())
            .andDatabase(databaseName)
            .withSqlDatabaseEngine(SqlDatabaseEngine.MS_SQL)
            .andLoginName(nxtArgCpt().nxtArgCpt().nxtArgCpt().getLoginName())
            .andLoginPassword(nxtArgCpt().nxtArgCpt().nxtArgCpt().nxtArgCpt().getLoginPassword())
            .borrowResource()));
  }
}
