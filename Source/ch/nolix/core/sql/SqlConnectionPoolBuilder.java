//package declaration
package ch.nolix.core.sql;

//own imports
import ch.nolix.core.programstructure.builder.andargumentcapturer.AndDatabaseNameCapturer;
import ch.nolix.core.programstructure.builder.andargumentcapturer.AndLoginNameCapturer;
import ch.nolix.core.programstructure.builder.andargumentcapturer.AndLoginPasswordCapturer;
import ch.nolix.core.programstructure.builder.andargumentcapturer.AndPortCapturer;
import ch.nolix.core.programstructure.builder.withargumentcapturer.WithSqlDatabaseEngineCapturer;

//class
public final class SqlConnectionPoolBuilder
extends
AndPortCapturer<AndDatabaseNameCapturer< //
WithSqlDatabaseEngineCapturer< //
AndLoginNameCapturer< //
AndLoginPasswordCapturer< //
SqlConnectionPool //
>>>>> {

  //constructor
  public SqlConnectionPoolBuilder(final String ipOrDomain) {

    super(
      new AndDatabaseNameCapturer<>(
        new WithSqlDatabaseEngineCapturer<>(
          new AndLoginNameCapturer<>(
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
