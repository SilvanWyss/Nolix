/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.sql.connectionpool;

import ch.nolix.base.argumentcaptor.andargumentcaptor.AndDatabaseNameCaptor;
import ch.nolix.base.argumentcaptor.andargumentcaptor.AndLoginNameCaptor;
import ch.nolix.base.argumentcaptor.andargumentcaptor.AndPasswordCaptor;
import ch.nolix.base.argumentcaptor.andargumentcaptor.AndPortCaptor;
import ch.nolix.base.argumentcaptor.forargumentcaptor.ForHostCaptor;
import ch.nolix.base.argumentcaptor.withargumentcaptor.WithSqlDatabaseEngineCaptor;

/**
 * @author Silvan Wyss
 */
public final class SqlConnectionPoolBuilder
extends
ForHostCaptor< //
AndPortCaptor< //
AndDatabaseNameCaptor< //
WithSqlDatabaseEngineCaptor< //
AndLoginNameCaptor< //
AndPasswordCaptor<SqlConnectionPool>>>>>> {
  private SqlConnectionPoolBuilder() {
    super(
      new AndPortCaptor<>(
        new AndDatabaseNameCaptor<>(
          new WithSqlDatabaseEngineCaptor<>(
            new AndLoginNameCaptor<>(
              new AndPasswordCaptor<>())))));

    setBuilder(this::buildSqlConnectionPool);
  }

  public static SqlConnectionPoolBuilder createConnectionPool() {
    return new SqlConnectionPoolBuilder();
  }

  private SqlConnectionPool buildSqlConnectionPool() {
    return //
    SqlConnectionPool.withHostAndPortAndDatabaseNameAndSqlDatabaseEngineAndLoginNameAndLoginPassword(
      getHost(),
      scsArgCpt().getPort(),
      scsArgCpt().scsArgCpt().getDatabase(),
      scsArgCpt().scsArgCpt().scsArgCpt().getSqlDatabaseEngine(),
      scsArgCpt().scsArgCpt().scsArgCpt().scsArgCpt().getLoginName(),
      scsArgCpt().scsArgCpt().scsArgCpt().scsArgCpt().scsArgCpt().getPassword());
  }
}
