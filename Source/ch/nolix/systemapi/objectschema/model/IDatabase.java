/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectschema.model;

import ch.nolix.baseapi.attribute.mandatoryattribute.NameHolder;
import ch.nolix.baseapi.objectcomposition.databasemanager.TableManager;
import ch.nolix.systemapi.database.databaseobject.DatabaseObject;

/**
 * @author Silvan Wyss
 */
public interface IDatabase extends DatabaseObject, NameHolder, TableManager<ITable> {
  /**
   * Adds the given table to the current {@link IDatabase}.
   * 
   * @param table
   * @return the current {@link IDatabase}
   */
  IDatabase addTable(ITable table);

  /**
   * Creates a new {@link ITable} with the given name in the current
   * {@link IDatabase}.
   * 
   * @param name
   * @return the current {@link IDatabase}
   */
  IDatabase createTableWithName(String name);

  /**
   * @return the number of {@link ITable}s of the current {@link IDatabase}
   */
  int getTableCount();
}
