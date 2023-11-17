//package declaration
package ch.nolix.system.objectschema.schemaadapter;

import ch.nolix.core.builder.andargumentcapturer.AndLoginPasswordCapturer;
import ch.nolix.core.builder.andargumentcapturer.AndPortCapturer;
import ch.nolix.core.builder.toargumentcapturer.ToDatabaseNameCapturer;
import ch.nolix.core.builder.usingargumentcapturer.UsingLoginNameCapturer;
import ch.nolix.core.sql.SqlConnectionPool;
import ch.nolix.core.sql.SqlDatabaseEngine;

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
      ch.nolix.system.sqldatabaserawschema.schemaadapter.MsSqlSchemaAdapter
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
