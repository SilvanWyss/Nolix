/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mandatoryattribute;

import ch.nolix.baseapi.generalcatalog.textcatalog.StringCatalog;

/**
 * A {@link DatabaseNameHolder} has a database name.
 * 
 * @author Silvan Wyss
 */
public interface DatabaseNameHolder {
  /**
   * @return the database name of the current {@link DatabaseNameHolder}
   */
  String getDatabaseName();

  /**
   * @return the database name of the current {@link DatabaseNameHolder} in single
   *         quotes
   */
  default String getDatabaseNameInSingleQuotes() {
    return StringCatalog.SINGLE_QUOTE + getDatabaseName() + StringCatalog.SINGLE_QUOTE;
  }
}
