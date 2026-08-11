/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.objectcomposition.datamodelcomponent;

/**
 * A {@link TableComponent} can belong to a table.
 * 
 * @author Silvan Wyss
 * @param <T> the type of the table a {@link TableComponent} can belong to.
 */
public interface TableComponent<T> {
  /**
   * @return true if the current {@link TableComponent} belongs to a table, false
   *         otherwise
   */
  boolean belongsToTable();

  /**
   * @return the table of the current {@link TableComponent}
   * @throws RuntimeException if the current {@link TableComponent} does not
   *                          belong to a table
   */
  T getStoredParentTable();
}
