//package declaration
package ch.nolix.system.objectdatabase.dataadapter;

import ch.nolix.core.programstructure.builder.andargumentcapturer.AndLoginPasswordCapturer;
import ch.nolix.core.programstructure.builder.andargumentcapturer.AndPortCapturer;
import ch.nolix.core.programstructure.builder.andargumentcapturer.AndSchemaCapturer;
import ch.nolix.core.programstructure.builder.toargumentcapturer.ToDatabaseNameCapturer;
import ch.nolix.core.programstructure.builder.usingargumentcapturer.UsingLoginNameCapturer;
import ch.nolix.coreapi.netapi.netconstantapi.PortCatalogue;
import ch.nolix.systemapi.objectdatabaseapi.schemaapi.ISchema;

//class
public final class MsSqlDataAdapterBuilder
extends
AndPortCapturer< //
ToDatabaseNameCapturer< //
UsingLoginNameCapturer< //
AndLoginPasswordCapturer< //
AndSchemaCapturer< //
ISchema, //
MsSqlDataAdapter //
>>>>> {

  //constant
  public static final int DEFAULT_PORT = PortCatalogue.MS_SQL;

  //constructor
  public MsSqlDataAdapterBuilder(final String ipOrDomain) {

    super(
      new ToDatabaseNameCapturer<>(
        new UsingLoginNameCapturer<>(
          new AndLoginPasswordCapturer<>(
            new AndSchemaCapturer<>(null)))));

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
