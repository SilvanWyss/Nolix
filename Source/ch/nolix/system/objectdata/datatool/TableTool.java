//package declaration
package ch.nolix.system.objectdata.datatool;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.databaseobject.databaseobjecttool.DatabaseObjectTool;
import ch.nolix.systemapi.objectdataapi.dataapi.IColumn;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.datatoolapi.ITableTool;

//class
public final class TableTool extends DatabaseObjectTool implements ITableTool {

  //constant
  private static final EntityTool ENTITY_TOOL = new EntityTool();

  //method
  @Override
  public boolean allNewAndEditedMandatoryPropertiesAreSet(final ITable<?> table) {
    return table
      .internalGetStoredEntitiesInLocalData()
      .containsOnly(
        e -> ENTITY_TOOL.allNewAndEditedMandatoryFieldsAreSet(e) //NOSONAR: A method reference will rise a BootstrapMethodError.
      );
  }

  //method
  @Override
  public boolean canInsertEntity(final ITable<?> table) {
    return table.isOpen();
  }

  //method
  @Override
  public boolean canInsertGivenEntity(ITable<?> table, IEntity entity) {
    return canInsertEntity(table)
    && ENTITY_TOOL.canBeInsertedIntoTable(entity)
    && !hasInsertedGivenEntityInLocalData(table, entity);
  }

  //method
  @Override
  public boolean containsEntityWithGivenIdInLocalData(final ITable<?> table, final String id) {
    return table.internalGetStoredEntitiesInLocalData().containsAny(e -> e.hasId(id));
  }

  //method
  @Override
  public <E extends IEntity> IContainer<IColumn> getColumsThatReferenceGivenTable(
    final ITable<E> table) {

    final var columns = new LinkedList<IColumn>();
    for (final var t : table.getStoredParentDatabase().getStoredTables()) {
      for (final var c : t.getStoredColumns()) {
        if (c.getParameterizedFieldType().referencesTable(table)) {
          columns.addAtEnd(c);
        }
      }
    }

    return columns;
  }

  //method
  @Override
  public boolean hasChanges(final ITable<?> table) {
    return table.internalGetStoredEntitiesInLocalData().containsAny(e -> !e.isLoaded());
  }

  //method
  @Override
  public boolean hasInsertedGivenEntityInLocalData(final ITable<?> table, final IEntity entity) {
    return containsEntityWithGivenIdInLocalData(table, entity.getId());
  }
}
