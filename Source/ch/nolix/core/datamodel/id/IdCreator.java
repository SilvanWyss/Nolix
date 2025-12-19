package ch.nolix.core.datamodel.id;

import java.util.UUID;

import ch.nolix.coreapi.commontypetool.stringtool.StringCatalog;

/**
 * @author Silvan Wyss
 */
public final class IdCreator {
  private IdCreator() {
  }

  public static String createIdOf10HexadecimalCharacters() {
    return UUID.randomUUID().toString().replace(StringCatalog.MINUS, StringCatalog.EMPTY_STRING).substring(22, 32);
  }
}
