/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.foundation.util;

import java.util.UUID;

import ch.nolix.baseapi.generalcatalog.textcatalog.StringCatalog;

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
