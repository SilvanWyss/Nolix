package ch.nolix.coreapi.attribute.mutablemandatoryattribute;

import ch.nolix.coreapi.attribute.mandatoryattribute.IDatabaseNameHolder;

/**
 * A {@link IMutableDatabaseNameHolder} is a {@link IDatabaseNameHolder} whose
 * database name can be set programmatically.
 * 
 * @author Silvan Wyss
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
