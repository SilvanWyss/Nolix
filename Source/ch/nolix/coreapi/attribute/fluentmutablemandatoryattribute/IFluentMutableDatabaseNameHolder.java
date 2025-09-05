package ch.nolix.coreapi.attribute.fluentmutablemandatoryattribute;

import ch.nolix.coreapi.attribute.mandatoryattribute.IDatabaseNameHolder;

/**
 * A {@link IFluentMutableDatabaseNameHolder} is a {@link IDatabaseNameHolder}
 * whose database name can be set programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @version 2025-06-01
 * @param <H> is the type of a {@link IFluentMutableDatabaseNameHolder}.
 */
public interface IFluentMutableDatabaseNameHolder<H extends IFluentMutableDatabaseNameHolder<H>>
extends IDatabaseNameHolder {
  /**
   * Sets the database name of the current
   * {@link IFluentMutableDatabaseNameHolder}.
   * 
   * @param databaseName
   * @return the current {@link IFluentMutableDatabaseNameHolder}.
   * @throws RuntimeException if the given databaseName is null or blank.
   */
  H setDatabaseName(String databaseName);
}
