//package declaration
package ch.nolix.system.objectdatabase.dataadapter;

//own imports
import ch.nolix.core.programstructure.builder.andargumentcapturer.AndDatabaseNameCapturer;
import ch.nolix.core.programstructure.builder.andargumentcapturer.AndLoginPasswordCapturer;
import ch.nolix.core.programstructure.builder.andargumentcapturer.AndPortCapturer;
import ch.nolix.core.programstructure.builder.andargumentcapturer.AndSchemaCapturer;
import ch.nolix.core.programstructure.builder.withargumentcapturer.WithLoginNameCapturer;
import ch.nolix.coreapi.netapi.netconstantapi.PortCatalogue;
import ch.nolix.systemapi.objectdatabaseapi.schemaapi.ISchema;

//class
public final class MsSqlDataAdapterBuilder
extends
AndPortCapturer< //
AndDatabaseNameCapturer< //
WithLoginNameCapturer< //
AndLoginPasswordCapturer< //
AndSchemaCapturer<ISchema, MsSqlDataAdapter>>>>> {

  //constant
  public static final int DEFAULT_PORT = PortCatalogue.MS_SQL;

  //constructor
  public MsSqlDataAdapterBuilder(final String ipOrDomain) {

    super(
      new AndDatabaseNameCapturer<>(
        new WithLoginNameCapturer<>(
          new AndLoginPasswordCapturer<>(
            new AndSchemaCapturer<>()))));

    setBuilder(() -> build(ipOrDomain));
  }

  //method
  private MsSqlDataAdapter build(final String ipOrDomain) {
    return new MsSqlDataAdapter(
      ipOrDomain,
      getPort(),
      next().getDatabaseName(),
      next().next().getLoginName(),
      next().next().next().getLoginPassword(),
      next().next().next().next().getStoredSchema());
  }
}
