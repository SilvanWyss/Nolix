package ch.nolix.system.objectdata.modelsearcher;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.system.objectdata.modelexaminer.ColumnExaminer;
import ch.nolix.systemapi.objectdata.model.IColumn;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.modelexaminer.IColumnExaminer;
import ch.nolix.systemapi.objectdata.modelsearcher.ITableSearcher;

/**
 * @author Silvan Wyss
 * @version 2024-12-29
 */
public final class TableSearcher extends DatabaseObjectExaminer implements ITableSearcher {
  private static final IColumnExaminer COLUMN_EXAMINER = new ColumnExaminer();

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<String> getLocallyDeletedEntityIds(final ITable<?> table) {
    if (table == null) {
      return ImmutableList.createEmpty();
    }

    return table.internalGetStoredEntitiesInLocalData().getViewOfStoredSelected(IEntity::isDeleted).to(IEntity::getId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <E extends IEntity> IContainer<IColumn> getStoredColumsThatReferencesTable(
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
