/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.modelsearcher;

import ch.nolix.base.container.immutablelist.ImmutableList;
import ch.nolix.base.container.linkedlist.LinkedList;
import ch.nolix.baseapi.container.list.ILinkedList;
import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.system.objectdata.modelexaminer.ColumnExaminer;
import ch.nolix.systemapi.objectdata.model.IColumn;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.modelexaminer.IColumnExaminer;
import ch.nolix.systemapi.objectdata.modelsearcher.ITableSearcher;

/**
 * @author Silvan Wyss
 */
public final class TableSearcher extends DatabaseObjectExaminer implements ITableSearcher {
  private static final IColumnExaminer COLUMN_EXAMINER = new ColumnExaminer();

  /**
   * {@inheritDoc}
   */
  @Override
  public IWellOrderContainer<String> getLocallyDeletedEntityIds(final ITable<?> table) {
    if (table == null) {
      return ImmutableList.createEmpty();
    }

    return table.internalGetStoredEntitiesInLocalData().getViewOfStoredSelected(IEntity::isDeleted).to(IEntity::getId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <E extends IEntity> IWellOrderContainer<IColumn> getStoredColumsThatReferencesTable(
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
