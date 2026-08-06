/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.modelsearcher;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.system.objectdata.modelexaminer.ColumnExaminer;
import ch.nolix.systemapi.objectdata.model.IColumn;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.modelsearcher.ITableSearcher;

/**
 * @author Silvan Wyss
 */
public final class TableSearcher extends DatabaseObjectExaminer implements ITableSearcher {
  private static final ColumnExaminer COLUMN_EXAMINER = new ColumnExaminer();

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<String> getLocallyDeletedEntityIds(final ITable<?> table) {
    if (table == null) {
      return ImmutableList.createEmpty();
    }

    return table.internalGetStoredEntitiesInLocalData().getViewOfStoredSelected(IEntity::isDeleted).to(IEntity::getId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <E extends IEntity> ExtendedIterable<IColumn> getStoredColumsThatReferencesTable(
    final ITable<E> table) {
    if (table == null) {
      return ImmutableList.createEmpty();
    }

    final ILinkedList<IColumn> columnViews = LinkedList.createEmpty();

    for (final var t : table.getStoredParentDatabase().getStoredTables()) {
      for (final var c : t.getStoredColumns()) {
        if (COLUMN_EXAMINER.containsReferenceableTable(c, t)) {
          columnViews.addAtEnd(c);
        }
      }
    }

    return columnViews;
  }
}
