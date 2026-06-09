/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mandatoryattribute;

import ch.nolix.baseapi.commontypetool.stringtool.StringCatalog;

/**
 * A {@link IDatabaseNameHolder} has a database name.
 * 
 * @author Silvan Wyss
 */
public interface IDatabaseNameHolder {
  /**
   * @return the database name of the current {@link IDatabaseNameHolder}
   */
  String getDatabaseName();

  /**
   * @return the database name of the current {@link IDatabaseNameHolder} in
   *         single quotes
   */
  default String getDatabaseNameInSingleQuotes() {
    return StringCatalog.SINGLE_QUOTE + getDatabaseName() + StringCatalog.SINGLE_QUOTE;
  }
}
