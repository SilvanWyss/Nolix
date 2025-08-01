package ch.nolix.system.objectdata.adapter;

import ch.nolix.core.argumentcaptor.andargumentcaptor.AndDatabaseNameCaptor;
import ch.nolix.core.argumentcaptor.andargumentcaptor.AndLoginPasswordCaptor;
import ch.nolix.core.argumentcaptor.andargumentcaptor.AndPortCaptor;
import ch.nolix.core.argumentcaptor.andargumentcaptor.AndSchemaCaptor;
import ch.nolix.core.argumentcaptor.toargumentcaptor.ToIpOrDomainCaptor;
import ch.nolix.core.argumentcaptor.withargumentcaptor.WithLoginNameCaptor;
import ch.nolix.coreapi.net.netconstant.PortCatalog;
import ch.nolix.systemapi.objectdata.model.IEntityTypeSet;

public final class MsSqlDataAdapterBuilder
extends
ToIpOrDomainCaptor< //
AndPortCaptor< //
AndDatabaseNameCaptor< //
WithLoginNameCaptor< //
AndLoginPasswordCaptor< //
AndSchemaCaptor<IEntityTypeSet, MsSqlDataAdapter>>>>>> {

  public static final int DEFAULT_PORT = PortCatalog.MS_SQL;

  private MsSqlDataAdapterBuilder() {

    super(
      new AndPortCaptor<>(
        new AndDatabaseNameCaptor<>(
          new WithLoginNameCaptor<>(
            new AndLoginPasswordCaptor<>(
              new AndSchemaCaptor<>())))));

    setBuilder(this::buildMsSqlDataAdapter);
  }

  public static MsSqlDataAdapterBuilder createMsSqlDataAdapter() {
    return new MsSqlDataAdapterBuilder();
  }

  private MsSqlDataAdapter buildMsSqlDataAdapter() {
    return new MsSqlDataAdapter(
      getIpOrDomain(),
      nxtArgCpt().getPort(),
      nxtArgCpt().nxtArgCpt().getDatabaseName(),
      nxtArgCpt().nxtArgCpt().nxtArgCpt().getLoginName(),
      nxtArgCpt().nxtArgCpt().nxtArgCpt().nxtArgCpt().getLoginPassword(),
      nxtArgCpt().nxtArgCpt().nxtArgCpt().nxtArgCpt().nxtArgCpt().getStoredSchema());
  }
}
