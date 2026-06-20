/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutablemandatoryattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.DatabaseNameHolder;

/**
 * A {@link FluentMutableDatabaseNameHolder} is a {@link DatabaseNameHolder}
 * whose database name can be set programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link FluentMutableDatabaseNameHolder}
 */
public interface FluentMutableDatabaseNameHolder<H extends FluentMutableDatabaseNameHolder<H>>
extends DatabaseNameHolder {
  /**
   * Sets the database name of the current
   * {@link FluentMutableDatabaseNameHolder}.
   * 
   * @param databaseName
   * @return the current {@link FluentMutableDatabaseNameHolder}
   * @throws RuntimeException if the given databaseName is null or blank
   */
  H setDatabaseName(String databaseName);
}
