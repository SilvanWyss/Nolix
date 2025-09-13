package ch.nolix.system.objectdata.modelsearcher;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.systemapi.objectdata.model.IColumn;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.modelsearcher.ITableSearcher;

/**
 * @author Silvan Wyss
 * @version 2024-12-29
 */
public final class TableSearcher extends DatabaseObjectExaminer implements ITableSearcher {
  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<String> getLocallyDeletedEntityIds(final ITable<?> table) {
    if (table == null) {
      return ImmutableList.createEmpty();
    }

    return table.internalGetStoredEntitiesInLocalData().getStoredSelected(IEntity::isDeleted).to(IEntity::getId);
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
        if (columnContainsReferenceableTable(c, t)) {
          columnViews.addAtEnd(c);
        }
      }
    }

    return columnViews;
  }

  //TODO: Create ColumnExaminer
  private boolean columnContainsReferenceableTable(final IColumn column, final ITable<IEntity> table) {
    return column.getStoredReferenceableTables().contains(table);
  }
}
