package ch.nolix.system.objectschema.adapter;

import ch.nolix.core.argumentcaptor.andargumentcaptor.AndLoginPasswordCaptor;
import ch.nolix.core.argumentcaptor.andargumentcaptor.AndPortCaptor;
import ch.nolix.core.argumentcaptor.toargumentcaptor.ToDatabaseNameCaptor;
import ch.nolix.core.argumentcaptor.toargumentcaptor.ToIpOrDomainCaptor;
import ch.nolix.core.argumentcaptor.withargumentcaptor.WithLoginNameCaptor;
import ch.nolix.core.sql.connectionpool.SqlConnectionPoolBuilder;
import ch.nolix.coreapi.sqlapi.sqlproperty.SqlDatabaseEngine;

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

    return new MsSqlSchemaAdapter(
      databaseName,
      ch.nolix.system.sqlmidschema.adapter.MsSqlSchemaAdapter
        .forDatabaseNameAndSqlConnection(
          databaseName,
          SqlConnectionPoolBuilder
            .createConnectionPool()
            .forIpOrDomain(getIpOrDomain())
            .andPort(nxtArgCpt().getPort())
            .andDatabase(databaseName)
            .withSqlDatabaseEngine(SqlDatabaseEngine.MSSQL)
            .andLoginName(nxtArgCpt().nxtArgCpt().nxtArgCpt().getLoginName())
            .andLoginPassword(nxtArgCpt().nxtArgCpt().nxtArgCpt().nxtArgCpt().getLoginPassword())
            .borrowResource()));
  }
}
