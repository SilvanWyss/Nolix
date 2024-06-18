//package declaration
package ch.nolix.system.objectdata.dataadapter;

import ch.nolix.core.programstructure.builder.andargumentcaptor.AndDatabaseNameCaptor;
import ch.nolix.core.programstructure.builder.andargumentcaptor.AndLoginPasswordCaptor;
import ch.nolix.core.programstructure.builder.andargumentcaptor.AndPortCaptor;
import ch.nolix.core.programstructure.builder.andargumentcaptor.AndSchemaCaptor;
import ch.nolix.core.programstructure.builder.toargumentcaptor.ToIpOrDomainCaptor;
import ch.nolix.core.programstructure.builder.withargumentcaptor.WithLoginNameCaptor;
import ch.nolix.coreapi.netapi.netconstantapi.PortCatalogue;
import ch.nolix.systemapi.objectdataapi.schemaapi.ISchema;

//class
public final class MsSqlDataAdapterBuilder
extends
ToIpOrDomainCaptor< //
AndPortCaptor< //
AndDatabaseNameCaptor< //
WithLoginNameCaptor< //
AndLoginPasswordCaptor< //
AndSchemaCaptor<ISchema, MsSqlDataAdapter>>>>>> {

  //constant
  public static final int DEFAULT_PORT = PortCatalogue.MS_SQL;

  //constructor
  private MsSqlDataAdapterBuilder() {

    super(
      new AndPortCaptor<>(
        new AndDatabaseNameCaptor<>(
          new WithLoginNameCaptor<>(
            new AndLoginPasswordCaptor<>(
              new AndSchemaCaptor<>())))));

    setBuilder(this::buildMsSqlDataAdapter);
  }

  //static method
  public static MsSqlDataAdapterBuilder createMsSqlDataAdapter() {
    return new MsSqlDataAdapterBuilder();
  }

  //method
  private MsSqlDataAdapter buildMsSqlDataAdapter() {
    return new MsSqlDataAdapter(
      getIpOrDomain(),
      next().getPort(),
      next().next().getDatabaseName(),
      next().next().next().getLoginName(),
      next().next().next().next().getLoginPassword(),
      next().next().next().next().next().getStoredSchema());
  }
}
