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
      scsArgCpt().getPort(),
      scsArgCpt().scsArgCpt().getDatabase(),
      scsArgCpt().scsArgCpt().scsArgCpt().getLoginName(),
      scsArgCpt().scsArgCpt().scsArgCpt().scsArgCpt().getPassword(),
      scsArgCpt().scsArgCpt().scsArgCpt().scsArgCpt().scsArgCpt().getStoredSchema());
  }
}
