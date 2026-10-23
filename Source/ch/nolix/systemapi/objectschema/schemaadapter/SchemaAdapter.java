/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectschema.schemaadapter;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.resourcecontrol.savecontrol.IResettableChangeSaver;
import ch.nolix.systemapi.objectschema.model.ITable;

/**
 * @author Silvan Wyss
 */
public interface SchemaAdapter extends IResettableChangeSaver {
  /**
   * Adds the given table to the database of the current {@link SchemaAdapter}.
   * 
   * @param table
   * @return the current {@link SchemaAdapter}
   */
  SchemaAdapter addTable(ITable table);

  /**
   * @return true if the database of the current {@link SchemaAdapter} is empty,
   *         false otherwise
   */
  boolean databaseIsEmpty();

  /**
   * @param name
   * @return the {@link ITable} with the given name from the database of the
   *         current {@link SchemaAdapter}
   * @throws RuntimeException if the database of the current {@link SchemaAdapter}
   *                          does not contain a {@link ITable} with the given
   *                          name
   */
  ITable getStoredTableByName(String name);

  /**
   * @return the {@link ITable}s of the database of the current
   *         {@link SchemaAdapter}
   */
  ExtendedIterable<? extends ITable> getStoredTables();

  /**
   * @return the number of {@link ITable}s of the database of the current
   *         {@link SchemaAdapter}
   */
  int getTableCount();
}
