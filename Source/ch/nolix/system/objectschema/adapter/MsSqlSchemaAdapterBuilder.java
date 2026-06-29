/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectschema.adapter;

import ch.nolix.base.argumentcaptor.andargumentcaptor.AndLoginPasswordCaptor;
import ch.nolix.base.argumentcaptor.andargumentcaptor.AndPortCaptor;
import ch.nolix.base.argumentcaptor.toargumentcaptor.ToDatabaseNameCaptor;
import ch.nolix.base.argumentcaptor.toargumentcaptor.ToIpOrDomainCaptor;
import ch.nolix.base.argumentcaptor.withargumentcaptor.WithLoginNameCaptor;
import ch.nolix.base.sql.connection.MsSqlConnection;

/**
 * @author Silvan Wyss
 */
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
    final var databaseName = scsArgCpt().scsArgCpt().getDatabaseName();

    final var msSqlConnection = //
    MsSqlConnection.toHostAndPortAndWithUserNameAndUserPassword(
      getIpOrDomain(),
      scsArgCpt().getPort(),
      scsArgCpt().scsArgCpt().scsArgCpt().getLoginName(),
      scsArgCpt().scsArgCpt().scsArgCpt().scsArgCpt().getLoginPassword());

    return MsSqlSchemaAdapter.forDatabaseNameAndSqlConnection(databaseName, msSqlConnection);
  }
}
