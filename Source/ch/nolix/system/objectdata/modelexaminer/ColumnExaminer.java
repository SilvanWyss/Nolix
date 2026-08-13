/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.modelexaminer;

import ch.nolix.system.database.databaseobjectexaminer.AbstractDatabaseObjectExaminer;
import ch.nolix.systemapi.objectdata.model.IColumn;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.modelexaminer.IColumnExaminer;

/**
 * @author Silvan Wyss
 */
public final class ColumnExaminer extends AbstractDatabaseObjectExaminer<IColumn> implements IColumnExaminer {
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsReferenceableTable(final IColumn column, final ITable<IEntity> referenceableTable) {
    final var referenceableTables = column.getStoredReferenceableTables();

    return referenceableTables.contains(referenceableTable);
  }
}
