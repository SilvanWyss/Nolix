package ch.nolix.coreapi.attributeapi.mutablemandatoryattributeapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IDatabaseNameHolder;

/**
 * A {@link IMutableDatabaseNameHolder} is a {@link IDatabaseNameHolder} whose
 * database name can be set programmatically.
 * 
 * @author Silvan Wyss
 * @version 2025-06-01
 */
public interface IMutableDatabaseNameHolder extends IDatabaseNameHolder {

  /**
   * Sets the database name of the current {@link IMutableDatabaseNameHolder}.
   * 
   * @param databaseName
   * @throws RuntimeException if the given databaseName is null or blank.
   */
  void setDatabaseName(String databaseName);
}
