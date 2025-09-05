package ch.nolix.core.sql.connectionpool;

import ch.nolix.core.argumentcaptor.andargumentcaptor.AndDatabaseNameCaptor;
import ch.nolix.core.argumentcaptor.andargumentcaptor.AndLoginNameCaptor;
import ch.nolix.core.argumentcaptor.andargumentcaptor.AndLoginPasswordCaptor;
import ch.nolix.core.argumentcaptor.andargumentcaptor.AndPortCaptor;
import ch.nolix.core.argumentcaptor.forargumentcaptor.ForIpOrDomainCaptor;
import ch.nolix.core.argumentcaptor.withargumentcaptor.WithSqlDatabaseEngineCaptor;

public final class SqlConnectionPoolBuilder
extends
ForIpOrDomainCaptor< //
AndPortCaptor< //
AndDatabaseNameCaptor< //
WithSqlDatabaseEngineCaptor< //
AndLoginNameCaptor< //
AndLoginPasswordCaptor<SqlConnectionPool>>>>>> {
  private SqlConnectionPoolBuilder() {
    super(
      new AndPortCaptor<>(
        new AndDatabaseNameCaptor<>(
          new WithSqlDatabaseEngineCaptor<>(
            new AndLoginNameCaptor<>(
              new AndLoginPasswordCaptor<>())))));

    setBuilder(this::buildSqlConnectionPool);
  }

  public static SqlConnectionPoolBuilder createConnectionPool() {
    return new SqlConnectionPoolBuilder();
  }

  private SqlConnectionPool buildSqlConnectionPool() {
    return new SqlConnectionPool(
      getIpOrDomain(),
      nxtArgCpt().getPort(),
      nxtArgCpt().nxtArgCpt().getDatabaseName(),
      nxtArgCpt().nxtArgCpt().nxtArgCpt().getSqlDatabaseEngine(),
      nxtArgCpt().nxtArgCpt().nxtArgCpt().nxtArgCpt().getLoginName(),
      nxtArgCpt().nxtArgCpt().nxtArgCpt().nxtArgCpt().nxtArgCpt().getLoginPassword());
  }
}
