//package declaration
package ch.nolix.system.objectschema.schemaadapter;

//own imports
import ch.nolix.core.programstructure.builder.andargumentcapturer.AndLoginPasswordCapturer;
import ch.nolix.core.programstructure.builder.andargumentcapturer.AndPortCapturer;
import ch.nolix.core.programstructure.builder.toargumentcapturer.ToDatabaseNameCapturer;
import ch.nolix.core.programstructure.builder.usingargumentcapturer.UsingLoginNameCapturer;
import ch.nolix.core.sql.SqlConnectionPool;
import ch.nolix.coreapi.sqlapi.sqlproperty.SqlDatabaseEngine;

//class
public final class MsSqlSchemaAdapterBuilder
extends
AndPortCapturer<ToDatabaseNameCapturer<UsingLoginNameCapturer<AndLoginPasswordCapturer<MsSqlSchemaAdapter>>>> {

  //constructor
  public MsSqlSchemaAdapterBuilder(final String ipOrDomain, final int defaultPort) {

    super(
      defaultPort,
      new ToDatabaseNameCapturer<>(
        new UsingLoginNameCapturer<>(
          new AndLoginPasswordCapturer<>())));

    setBuilder(() -> build(ipOrDomain));
  }

  //method
  private MsSqlSchemaAdapter build(final String ipOrDomain) {
    return new MsSqlSchemaAdapter(
      next().getDatabaseName(),
      ch.nolix.system.sqlrawschema.schemaadapter.MsSqlSchemaAdapter
        .forDatabaseWithGivenNameUsingConnectionFromGivenPool(
          next().getDatabaseName(),
          SqlConnectionPool
            .forIpOrDomain(ipOrDomain)
            .andPort(getPort())
            .andDatabase(next().getDatabaseName())
            .withSqlDatabaseEngine(SqlDatabaseEngine.MSSQL)
            .usingLoginName(next().next().getLoginName())
            .andLoginPassword(next().next().next().getLoginPassword())));
  }
}
