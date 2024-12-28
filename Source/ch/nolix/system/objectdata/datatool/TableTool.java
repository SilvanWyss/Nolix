package ch.nolix.system.objectdata.datatool;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.systemapi.objectdataapi.datatoolapi.ITableTool;
import ch.nolix.systemapi.objectdataapi.modelapi.IColumn;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;

public final class TableTool extends DatabaseObjectExaminer implements ITableTool {

  private static final EntityTool ENTITY_TOOL = new EntityTool();

  @Override
  public boolean allNewAndEditedMandatoryFieldsAreSet(final ITable<?> table) {
    return table
      .internalGetStoredEntitiesInLocalData()
      .containsOnly(
        e -> ENTITY_TOOL.allNewAndEditedMandatoryFieldsAreSet(e) //NOSONAR: A method reference will rise a BootstrapMethodError.
      );
  }

  @Override
  public boolean canInsertEntity(final ITable<?> table) {
    return table.isOpen();
  }

  @Override
  public boolean canInsertGivenEntity(ITable<?> table, IEntity entity) {
    return canInsertEntity(table)
    && ENTITY_TOOL.canBeInsertedIntoTable(entity)
    && !hasInsertedGivenEntityInLocalData(table, entity);
  }

  @Override
  public boolean containsEntityWithGivenIdInLocalData(final ITable<?> table, final String id) {
    return table.internalGetStoredEntitiesInLocalData().containsAny(e -> e.hasId(id));
  }

  @Override
  public <E extends IEntity> IContainer<IColumn> getColumsThatReferenceGivenTable(
    final ITable<E> table) {

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

  @Override
  public IContainer<String> getLocallyDeletedEntities(final ITable<?> table) {
    return table.internalGetStoredEntitiesInLocalData().getStoredSelected(IEntity::isDeleted).to(IEntity::getId);
  }

  @Override
  public boolean hasChanges(final ITable<?> table) {
    return table.internalGetStoredEntitiesInLocalData().containsAny(e -> !e.isLoaded());
  }

  @Override
  public boolean hasInsertedGivenEntityInLocalData(final ITable<?> table, final IEntity entity) {
    return containsEntityWithGivenIdInLocalData(table, entity.getId());
  }
}
