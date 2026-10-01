/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectschema.adapter;

import ch.nolix.base.argumentcaptor.andargumentcaptor.AndPasswordCaptor;
import ch.nolix.base.argumentcaptor.andargumentcaptor.AndPortCaptor;
import ch.nolix.base.argumentcaptor.toargumentcaptor.ToDatabaseCaptor;
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
ToDatabaseCaptor< //
WithLoginNameCaptor< //
AndPasswordCaptor< //
MsSqlSchemaAdapter>>>>> {
  private MsSqlSchemaAdapterBuilder() {
    super(
      new AndPortCaptor<>(
        new ToDatabaseCaptor<>(
          new WithLoginNameCaptor<>(
            new AndPasswordCaptor<>()))));

    setBuilder(this::buildMsSqlSchemaAdapter);
  }

  public static MsSqlSchemaAdapterBuilder createMsSqlSchemaAdapter() {
    return new MsSqlSchemaAdapterBuilder();
  }

  private MsSqlSchemaAdapter buildMsSqlSchemaAdapter() {
    final var databaseName = suArCa().suArCa().getDatabase();

    final var msSqlConnection = //
    MsSqlConnection.toHostAndPortAndWithUserNameAndUserPassword(
      getHost(),
      suArCa().getPort(),
      suArCa().suArCa().suArCa().getLoginName(),
      suArCa().suArCa().suArCa().suArCa().getPassword());

    return MsSqlSchemaAdapter.forDatabaseNameAndSqlConnection(databaseName, msSqlConnection);
  }
}
