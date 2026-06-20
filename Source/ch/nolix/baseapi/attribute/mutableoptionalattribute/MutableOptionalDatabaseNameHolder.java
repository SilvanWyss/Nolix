/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutableoptionalattribute;

import ch.nolix.baseapi.attribute.optionalattribute.OptionalDatabaseNameHolder;

/**
 * A {@link MutableOptionalDatabaseNameHolder} is a
 * {@link OptionalDatabaseNameHolder} whose database name can be set and
 * removed programmatically.
 * 
 * @author Silvan Wyss
 */
public interface MutableOptionalDatabaseNameHolder extends OptionalDatabaseNameHolder {
  /**
   * Removes the database name of the current
   * {@link MutableOptionalDatabaseNameHolder}.
   */
  void removeDatabaseName();

  /**
   * Sets the database name of the current
   * {@link MutableOptionalDatabaseNameHolder}.
   * 
   * @param databaseName
   * @throws RuntimeException if the given databaseName is null or blank
   */
  void setDatabaseName(String databaseName);
}
