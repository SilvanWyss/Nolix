/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutablemandatoryattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.DatabaseNameHolder;

/**
 * A {@link MutableDatabaseNameHolder} is a {@link DatabaseNameHolder} whose
 * database name can be set programmatically.
 * 
 * @author Silvan Wyss
 */
public interface MutableDatabaseNameHolder extends DatabaseNameHolder {
  /**
   * Sets the database name of the current {@link MutableDatabaseNameHolder}.
   * 
   * @param databaseName
   * @throws RuntimeException if the given databaseName is null or blank
   */
  void setDatabaseName(String databaseName);
}
