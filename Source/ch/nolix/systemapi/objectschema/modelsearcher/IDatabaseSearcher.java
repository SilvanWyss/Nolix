/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectschema.modelsearcher;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.IDatabase;
import ch.nolix.systemapi.objectschema.model.ITable;

/**
 * @author Silvan Wyss
 */
public interface IDatabaseSearcher {
  /**
   * @param database
   * @return the base back reference columns of the given database
   */
  ExtendedIterable<? extends IColumn> getStoredBaseBackReferenceColumns(IDatabase database);

  /**
   * @param database
   * @param tableName
   * @return the {@link ITable} with the given tableName from the given database
   * @throws RuntimeException if the given database does not contain a
   *                          {@link ITable} with the given tableName
   */
  ITable getStoredTableByName(IDatabase database, String tableName);
}
