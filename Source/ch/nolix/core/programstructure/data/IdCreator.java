package ch.nolix.core.programstructure.data;

import java.util.UUID;

import ch.nolix.coreapi.programatomapi.stringcatalogapi.StringCatalog;

public final class IdCreator {

  private IdCreator() {
  }

  public static String createIdOf10HexadecimalCharacters() {
    return UUID.randomUUID().toString().replace(StringCatalog.MINUS, StringCatalog.EMPTY_STRING).substring(22, 32);
  }
}
