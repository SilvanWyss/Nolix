package ch.nolix.system.objectdata.modelsearcher;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.modelsearcher.ITableSearcher;
import ch.nolix.systemapi.objectdata.schemaview.IColumnView;

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
  @SuppressWarnings("unchecked")
  public <E extends IEntity> IContainer<IColumnView<ITable<IEntity>>> getStoredColumsThatReferencesTable(
    final ITable<E> table) {

    if (table == null) {
      return ImmutableList.createEmpty();
    }

    final ILinkedList<IColumnView<ITable<IEntity>>> columnViews = LinkedList.createEmpty();

    for (final var t : table.getStoredParentDatabase().getStoredTables()) {
      for (final var c : t.getStoredColumns()) {
        if (c.getContentModel().referencesTable((ITable<IEntity>) table)) {
          columnViews.addAtEnd(c);
        }
      }
    }

    return columnViews;
  }
}
