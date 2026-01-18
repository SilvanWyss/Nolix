/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectschema.modeltool;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.ITable;
import ch.nolix.systemapi.objectschema.modeltool.IColumnTool;
import ch.nolix.systemapi.objectschema.modeltool.ITableTool;

/**
 * @author Silvan Wyss
 */
public final class TableTool extends DatabaseObjectExaminer implements ITableTool {
  private static final IColumnTool COLUMN_TOOL = new ColumnTool();

  @Override
  public int getColumnCount(final ITable table) {
    return table.getStoredColumns().getCount();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<? extends IColumn> getStoredBaseBackReferenceColumns(final ITable table) {
    return table.getStoredColumns().getStoredSelected(COLUMN_TOOL::isABackReferenceColumn);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<? extends IColumn> getStoredBackReferencingColumns(final ITable table) {
    if (!table.belongsToDatabase()) {
      return TableToolHelper.getStoredBackReferencingColumnsWhenDoesNotBelongToDatabase(table);
    }

    return TableToolHelper.getStoredBackReferencingColumnsWhenBelongsToDatabase(table);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<? extends IColumn> getStoredReferencingColumns(final ITable table) {
    if (!table.belongsToDatabase()) {
      return TableToolHelper.getStoredReferencingColumnsWhenDoesNotBelongToDatabase(table);
    }

    return TableToolHelper.getStoredReferencingColumnsWhenBelongsToDatabase(table);
  }
}
