/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectschema.modelsearcher;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.ITable;

/**
 * @author Silvan Wyss
 */
public interface ITableSearcher {
  /**
   * @param table
   * @return the base back reference columns of the given table
   */
  ExtendedIterable<? extends IColumn> getStoredBaseBackReferenceColumns(ITable table);
}
