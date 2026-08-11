/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectschema.adapter;

import ch.nolix.base.argumentcaptor.andargumentcaptor.AndPasswordCaptor;
import ch.nolix.base.argumentcaptor.andargumentcaptor.AndPortCaptor;
import ch.nolix.base.argumentcaptor.toargumentcaptor.ToDatabaseNameCaptor;
import ch.nolix.base.argumentcaptor.toargumentcaptor.ToHostCaptor;
import ch.nolix.base.argumentcaptor.withargumentcaptor.WithLoginNameCaptor;
import ch.nolix.base.sql.connection.MsSqlConnection;

/**
 * @author Silvan Wyss
 */
public final class MsSqlSchemaAdapterBuilder
extends
ToHostCaptor< //
AndPortCaptor< //
ToDatabaseNameCaptor< //
WithLoginNameCaptor< //
AndPasswordCaptor< //
MsSqlSchemaAdapter>>>>> {
  private MsSqlSchemaAdapterBuilder() {
    super(
      new AndPortCaptor<>(
        new ToDatabaseNameCaptor<>(
          new WithLoginNameCaptor<>(
            new AndPasswordCaptor<>()))));

    setBuilder(this::buildMsSqlSchemaAdapter);
  }

  public static MsSqlSchemaAdapterBuilder createMsSqlSchemaAdapter() {
    return new MsSqlSchemaAdapterBuilder();
  }

  private MsSqlSchemaAdapter buildMsSqlSchemaAdapter() {
    final var databaseName = scsArgCpt().scsArgCpt().getDatabaseName();

    final var msSqlConnection = //
    MsSqlConnection.toHostAndPortAndWithUserNameAndUserPassword(
      getHost(),
      scsArgCpt().getPort(),
      scsArgCpt().scsArgCpt().scsArgCpt().getLoginName(),
      scsArgCpt().scsArgCpt().scsArgCpt().scsArgCpt().getPassword());

    return MsSqlSchemaAdapter.forDatabaseNameAndSqlConnection(databaseName, msSqlConnection);
  }
}
