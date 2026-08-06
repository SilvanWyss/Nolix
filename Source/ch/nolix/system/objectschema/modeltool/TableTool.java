/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectschema.modeltool;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.ITable;
import ch.nolix.systemapi.objectschema.modeltool.ITableTool;

/**
 * @author Silvan Wyss
 */
public final class TableTool extends DatabaseObjectExaminer implements ITableTool {
  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<? extends IColumn> getStoredBackReferencingColumns(final ITable table) {
    if (!table.belongsToDatabase()) {
      return TableToolHelper.getStoredBackReferencingColumnsWhenDoesNotBelongToDatabase(table);
    }

    return TableToolHelper.getStoredBackReferencingColumnsWhenBelongsToDatabase(table);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<? extends IColumn> getStoredReferencingColumns(final ITable table) {
    if (!table.belongsToDatabase()) {
      return TableToolHelper.getStoredReferencingColumnsWhenDoesNotBelongToDatabase(table);
    }

    return TableToolHelper.getStoredReferencingColumnsWhenBelongsToDatabase(table);
  }
}
