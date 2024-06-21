//package declaration
package ch.nolix.system.objectschema.schemaadapter;

import ch.nolix.core.argumentcaptor.andargumentcaptor.AndLoginPasswordCaptor;
import ch.nolix.core.argumentcaptor.andargumentcaptor.AndPortCaptor;
import ch.nolix.core.argumentcaptor.toargumentcaptor.ToDatabaseNameCaptor;
import ch.nolix.core.argumentcaptor.toargumentcaptor.ToIpOrDomainCaptor;
import ch.nolix.core.argumentcaptor.withargumentcaptor.WithLoginNameCaptor;
import ch.nolix.core.sql.connectionpool.SqlConnectionPoolBuilder;
import ch.nolix.coreapi.sqlapi.sqlproperty.SqlDatabaseEngine;

//class
public final class MsSqlSchemaAdapterBuilder
extends
ToIpOrDomainCaptor< //
AndPortCaptor< //
ToDatabaseNameCaptor< //
WithLoginNameCaptor< //
AndLoginPasswordCaptor< //
MsSqlSchemaAdapter>>>>> {

  //constructor
  private MsSqlSchemaAdapterBuilder() {

    super(
      new AndPortCaptor<>(
        new ToDatabaseNameCaptor<>(
          new WithLoginNameCaptor<>(
            new AndLoginPasswordCaptor<>()))));

    setBuilder(this::buildMsSqlSchemaAdapter);
  }

  //static method
  public static MsSqlSchemaAdapterBuilder createMsSqlSchemaAdapter() {
    return new MsSqlSchemaAdapterBuilder();
  }

  //method
  private MsSqlSchemaAdapter buildMsSqlSchemaAdapter() {

    final var databaseName = next().next().getDatabaseName();

    return new MsSqlSchemaAdapter(
      databaseName,
      ch.nolix.system.sqlrawschema.schemaadapter.MsSqlSchemaAdapter
        .forDatabaseWithGivenNameUsingConnectionFromGivenPool(
          databaseName,
          SqlConnectionPoolBuilder
            .createConnectionPool()
            .forIpOrDomain(getIpOrDomain())
            .andPort(next().getPort())
            .andDatabase(databaseName)
            .withSqlDatabaseEngine(SqlDatabaseEngine.MSSQL)
            .andLoginName(next().next().next().getLoginName())
            .andLoginPassword(next().next().next().next().getLoginPassword())));
  }
}
