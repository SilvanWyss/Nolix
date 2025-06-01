package ch.nolix.coreapi.attributeapi.mutableoptionalattributeapi;

import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalDatabaseNameHolder;

/**
 * A {@link IMutableOptionalDatabaseNameHolder} is a
 * {@link IOptionalDatabaseNameHolder} whose database name can be set and
 * removed programmatically.
 * 
 * @author Silvan Wyss
 * @version 2025-06-01
 */
public interface IMutableOptionalDatabaseNameHolder extends IOptionalDatabaseNameHolder {

  /**
   * Removes the database name of the current
   * {@link IMutableOptionalDatabaseNameHolder}.
   */
  void removeDatabaseName();

  /**
   * Sets the database name of the current
   * {@link IMutableOptionalDatabaseNameHolder}.
   * 
   * @param databaseName
   * @throws RuntimeException if the given databaseName is null or blank.
   */
  void setDatabaseName(String databaseName);
}
