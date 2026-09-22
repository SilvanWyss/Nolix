/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.adapter;

import ch.nolix.base.argumentcaptor.andargumentcaptor.AndDatabaseNameCaptor;
import ch.nolix.base.argumentcaptor.andargumentcaptor.AndPasswordCaptor;
import ch.nolix.base.argumentcaptor.andargumentcaptor.AndPortCaptor;
import ch.nolix.base.argumentcaptor.andargumentcaptor.AndSchemaCaptor;
import ch.nolix.base.argumentcaptor.toargumentcaptor.ToHostCaptor;
import ch.nolix.base.argumentcaptor.withargumentcaptor.WithLoginNameCaptor;
import ch.nolix.baseapi.net.netcatalog.PortCatalog;
import ch.nolix.systemapi.objectdata.model.IEntityTypeSet;

/**
 * @author Silvan Wyss
 */
public final class MsSqlDataAdapterBuilder
extends
ToHostCaptor< //
AndPortCaptor< //
AndDatabaseNameCaptor< //
WithLoginNameCaptor< //
AndPasswordCaptor< //
AndSchemaCaptor<IEntityTypeSet, MsSqlDataAdapter>>>>>> {
  public static final int DEFAULT_PORT = PortCatalog.MS_SQL;

  private MsSqlDataAdapterBuilder() {
    super(
      new AndPortCaptor<>(
        new AndDatabaseNameCaptor<>(
          new WithLoginNameCaptor<>(
            new AndPasswordCaptor<>(
              new AndSchemaCaptor<>())))));

    setBuilder(this::buildMsSqlDataAdapter);
  }

  public static MsSqlDataAdapterBuilder createMsSqlDataAdapter() {
    return new MsSqlDataAdapterBuilder();
  }

  private MsSqlDataAdapter buildMsSqlDataAdapter() {
    return //
    MsSqlDataAdapter.toHostAndPortAndWithDatabaseNameAndUserNameAndUserPasswordAndEntityTypeSet(
      getHost(),
      suArCa().getPort(),
      suArCa().suArCa().getDatabase(),
      suArCa().suArCa().suArCa().getLoginName(),
      suArCa().suArCa().suArCa().suArCa().getPassword(),
      suArCa().suArCa().suArCa().suArCa().suArCa().getStoredSchema());
  }
}
