package ch.nolix.system.objectdata.modelsearcher;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.systemapi.objectdataapi.modelapi.IColumn;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.objectdataapi.modelsearcher.ITableSearcher;

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
  public <E extends IEntity> IContainer<IColumn> getStoredColumsThatReferencesTable(final ITable<E> table) {

    if (table == null) {
      return ImmutableList.createEmpty();
    }

    final ILinkedList<IColumn> columns = LinkedList.createEmpty();

    for (final var t : table.getStoredParentDatabase().getStoredTables()) {
      for (final var c : t.getStoredColumns()) {
        if (c.getContentModel().referencesTable(table)) {
          columns.addAtEnd(c);
        }
      }
    }

    return columns;
  }
}
