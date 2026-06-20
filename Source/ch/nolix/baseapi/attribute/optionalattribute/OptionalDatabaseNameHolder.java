/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.optionalattribute;

/**
 * A {@link OptionalDatabaseNameHolder} can have a database name.
 * 
 * @author Silvan Wyss
 */
public interface OptionalDatabaseNameHolder {
  /**
   * @return the database name of the current {@link OptionalDatabaseNameHolder}
   * @throws RuntimeException if the current {@link OptionalDatabaseNameHolder}
   *                          does not have a database name
   */
  String getDatabaseName();

  /**
   * @return true if the current {@link OptionalDatabaseNameHolder} has a
   *         database name, false otherwise
   */
  boolean hasDatabaseName();
}
