//package declaration
package ch.nolix.core.sql;

//own imports
import ch.nolix.core.builder.andargumentcapturer.AndDatabaseNameCapturer;
import ch.nolix.core.builder.andargumentcapturer.AndLoginPasswordCapturer;
import ch.nolix.core.builder.andargumentcapturer.AndPortCapturer;
import ch.nolix.core.builder.usingargumentcapturer.UsingLoginNameCapturer;
import ch.nolix.core.builder.withargumentcapturer.WithSqlDatabaseEngineCapturer;

//class
public final class SqlConnectionPoolBuilder
extends
AndPortCapturer<AndDatabaseNameCapturer< //
WithSqlDatabaseEngineCapturer< //
UsingLoginNameCapturer< //
AndLoginPasswordCapturer< //
SqlConnectionPool //
>>>>> {

  //constructor
  public SqlConnectionPoolBuilder(final String ipOrDomain, final int defaultPort) {

    super(
      defaultPort,
      new AndDatabaseNameCapturer<>(
        new WithSqlDatabaseEngineCapturer<>(
          new UsingLoginNameCapturer<>(
            new AndLoginPasswordCapturer<>()))));

    setBuilder(() -> build(ipOrDomain));
  }

  //method
  private SqlConnectionPool build(final String ipOrDomain) {
    return new SqlConnectionPool(
      ipOrDomain,
      getPort(),
      next().getDatabaseName(),
      next().next().getSqlDatabaseEngine(),
      next().next().next().getLoginName(),
      next().next().next().next().getLoginPassword());
  }
}
