package ch.nolix.core.programstructure.data;

import java.util.UUID;

import ch.nolix.coreapi.programatomapi.stringcatalogueapi.StringCatalogue;

public final class GlobalIdCreator {

  private GlobalIdCreator() {
  }

  public static String createIdOf10HexadecimalCharacters() {
    return UUID.randomUUID().toString().replace(StringCatalogue.MINUS, StringCatalogue.EMPTY_STRING).substring(22, 32);
  }
}
