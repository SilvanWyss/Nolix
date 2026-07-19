/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.document.json;

import ch.nolix.baseapi.generalcatalog.textcatalog.StringCatalog;

/**
 * @author Silvan Wyss
 */
public final class JsonStringPartCatalog {
  public static final String OBJECT_BEGIN_FLAT_STRING = StringCatalog.OPEN_BRACE + StringCatalog.SPACE;

  public static final String OBJECT_END_FLAT_STRING = StringCatalog.SPACE + StringCatalog.CLOSED_BRACE;

  public static final String EMPTY_ARRAY_FLAT_STRING = //
  StringCatalog.OPEN_SQUARE_BRACKET + StringCatalog.SPACE + StringCatalog.CLOSED_SQUARE_BRACKET;

  public static final String ARRAY_FLAT_DELIMITER = StringCatalog.COMMA + StringCatalog.SPACE;

  public static final String NAME_VALUE_PAIR_FLAT_DELIMITER = StringCatalog.COMMA + StringCatalog.SPACE;

  public static final String NAME_VALUE_PAIR_FLAT_MIDDLE = StringCatalog.COLON + StringCatalog.SPACE;

  private JsonStringPartCatalog() {
  }
}
