/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectschema.modelsearcher;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.system.objectschema.modelexaminer.ColumnExaminer;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.ITable;
import ch.nolix.systemapi.objectschema.modelsearcher.ITableSearcher;

/**
 * @author Silvan Wyss
 */
public final class TableSearcher implements ITableSearcher {
  private static final ColumnExaminer COLUMN_EXAMINER = new ColumnExaminer();

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<? extends IColumn> getStoredBaseBackReferenceColumns(final ITable table) {
    return table.getStoredColumns().getStoredSelected(COLUMN_EXAMINER::isBaseBackReferenceColumn);
  }
}
