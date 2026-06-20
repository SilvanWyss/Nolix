/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutableoptionalattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.DatabaseNameHolder;

/**
 * A {@link FluentMutableOptionalDatabaseNameHolder} is a
 * {@link DatabaseNameHolder} whose database name can be set programmatically
 * and fluently and removed programmatically.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link FluentMutableOptionalDatabaseNameHolder}
 */
public interface FluentMutableOptionalDatabaseNameHolder<H extends FluentMutableOptionalDatabaseNameHolder<H>>
extends DatabaseNameHolder {
  /**
   * Removes the database name of the current
   * {@link FluentMutableOptionalDatabaseNameHolder}.
   */
  void removeDatabaseName();

  /**
   * Sets the database name of the current
   * {@link FluentMutableOptionalDatabaseNameHolder}.
   * 
   * @param databaseName
   * @return the current {@link FluentMutableOptionalDatabaseNameHolder}
   * @throws RuntimeException if the given databaseName is null or blank
   */
  H setDatabaseName(String databaseName);
}
