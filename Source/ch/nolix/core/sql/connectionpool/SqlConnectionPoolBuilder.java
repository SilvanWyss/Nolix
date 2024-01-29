//package declaration
package ch.nolix.core.sql.connectionpool;

//own imports
import ch.nolix.core.programstructure.builder.andargumentcapturer.AndDatabaseNameCapturer;
import ch.nolix.core.programstructure.builder.andargumentcapturer.AndLoginNameCapturer;
import ch.nolix.core.programstructure.builder.andargumentcapturer.AndLoginPasswordCapturer;
import ch.nolix.core.programstructure.builder.andargumentcapturer.AndPortCapturer;
import ch.nolix.core.programstructure.builder.forargumentcapturer.ForIpOrDomainCapturer;
import ch.nolix.core.programstructure.builder.withargumentcapturer.WithSqlDatabaseEngineCapturer;

//class
public final class SqlConnectionPoolBuilder
extends
ForIpOrDomainCapturer< //
AndPortCapturer< //
AndDatabaseNameCapturer< //
WithSqlDatabaseEngineCapturer< //
AndLoginNameCapturer< //
AndLoginPasswordCapturer<SqlConnectionPool>>>>>> {

  //constructor
  private SqlConnectionPoolBuilder() {

    super(
      new AndPortCapturer<>(
        new AndDatabaseNameCapturer<>(
          new WithSqlDatabaseEngineCapturer<>(
            new AndLoginNameCapturer<>(
              new AndLoginPasswordCapturer<>())))));

    setBuilder(this::buildSqlConnectionPool);
  }

  //static method
  public static SqlConnectionPoolBuilder createConnectionPool() {
    return new SqlConnectionPoolBuilder();
  }

  //method
  private SqlConnectionPool buildSqlConnectionPool() {
    return new SqlConnectionPool(
      getIpOrDomain(),
      next().getPort(),
      next().next().getDatabaseName(),
      next().next().next().getSqlDatabaseEngine(),
      next().next().next().next().getLoginName(),
      next().next().next().next().next().getLoginPassword());
  }
}
