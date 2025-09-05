package ch.nolix.coreapi.attribute.fluentmutableoptionalattribute;

import ch.nolix.coreapi.attribute.mandatoryattribute.IDatabaseNameHolder;

/**
 * A {@link IFluentMutableOptionalDatabaseNameHolder} is a
 * {@link IDatabaseNameHolder} whose database name can be set and removed
 * programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @version 2025-06-01
 * @param <H> is the type of a {@link IFluentMutableOptionalDatabaseNameHolder}.
 */
public interface IFluentMutableOptionalDatabaseNameHolder<H extends IFluentMutableOptionalDatabaseNameHolder<H>>
extends IDatabaseNameHolder {
  /**
   * Removes the database name of the current
   * {@link IFluentMutableOptionalDatabaseNameHolder}.
   */
  void removeDatabaseName();

  /**
   * Sets the database name of the current
   * {@link IFluentMutableOptionalDatabaseNameHolder}.
   * 
   * @param databaseName
   * @return the current {@link IFluentMutableOptionalDatabaseNameHolder}.
   * @throws RuntimeException if the given databaseName is null or blank.
   */
  H setDatabaseName(String databaseName);
}
