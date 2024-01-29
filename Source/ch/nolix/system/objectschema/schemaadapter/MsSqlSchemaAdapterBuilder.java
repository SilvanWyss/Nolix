//package declaration
package ch.nolix.system.objectschema.schemaadapter;

//own imports
import ch.nolix.core.programstructure.builder.andargumentcapturer.AndLoginPasswordCapturer;
import ch.nolix.core.programstructure.builder.andargumentcapturer.AndPortCapturer;
import ch.nolix.core.programstructure.builder.toargumentcapturer.ToDatabaseNameCapturer;
import ch.nolix.core.programstructure.builder.toargumentcapturer.ToIpOrDomainCapturer;
import ch.nolix.core.programstructure.builder.withargumentcapturer.WithLoginNameCapturer;
import ch.nolix.core.sql.connectionpool.SqlConnectionPoolBuilder;
import ch.nolix.coreapi.sqlapi.sqlproperty.SqlDatabaseEngine;

//class
public final class MsSqlSchemaAdapterBuilder
extends
ToIpOrDomainCapturer< //
AndPortCapturer< //
ToDatabaseNameCapturer< //
WithLoginNameCapturer< //
AndLoginPasswordCapturer< //
MsSqlSchemaAdapter>>>>> {

  //constructor
  private MsSqlSchemaAdapterBuilder() {

    super(
      new AndPortCapturer<>(
        new ToDatabaseNameCapturer<>(
          new WithLoginNameCapturer<>(
            new AndLoginPasswordCapturer<>()))));

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
