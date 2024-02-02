//package declaration
package ch.nolix.system.objectdata.dataadapter;

//own imports
import ch.nolix.core.programstructure.builder.andargumentcapturer.AndDatabaseNameCapturer;
import ch.nolix.core.programstructure.builder.andargumentcapturer.AndLoginPasswordCapturer;
import ch.nolix.core.programstructure.builder.andargumentcapturer.AndPortCapturer;
import ch.nolix.core.programstructure.builder.andargumentcapturer.AndSchemaCapturer;
import ch.nolix.core.programstructure.builder.toargumentcapturer.ToIpOrDomainCapturer;
import ch.nolix.core.programstructure.builder.withargumentcapturer.WithLoginNameCapturer;
import ch.nolix.coreapi.netapi.netconstantapi.PortCatalogue;
import ch.nolix.systemapi.objectdataapi.schemaapi.ISchema;

//class
public final class MsSqlDataAdapterBuilder
extends
ToIpOrDomainCapturer< //
AndPortCapturer< //
AndDatabaseNameCapturer< //
WithLoginNameCapturer< //
AndLoginPasswordCapturer< //
AndSchemaCapturer<ISchema, MsSqlDataAdapter>>>>>> {

  //constant
  public static final int DEFAULT_PORT = PortCatalogue.MS_SQL;

  //constructor
  private MsSqlDataAdapterBuilder() {

    super(
      new AndPortCapturer<>(
        new AndDatabaseNameCapturer<>(
          new WithLoginNameCapturer<>(
            new AndLoginPasswordCapturer<>(
              new AndSchemaCapturer<>())))));

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
