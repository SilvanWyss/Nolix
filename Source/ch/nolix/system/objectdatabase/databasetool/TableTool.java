//package declaration
package ch.nolix.system.objectdatabase.databasetool;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.databaseobject.databaseobjecttool.DatabaseObjectTool;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IColumn;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;
import ch.nolix.systemapi.objectdatabaseapi.databasetoolapi.ITableTool;

//class
public final class TableTool extends DatabaseObjectTool implements ITableTool {

  //constant
  private static final EntityTool ENTITY_TOOL = new EntityTool();

  //method
  @Override
  public boolean allNewAndEditedMandatoryPropertiesAreSet(final ITable<?> table) {
    return table
      .technicalGetRefEntitiesInLocalData()
      .containsOnly(
        e -> ENTITY_TOOL.allNewAndEditedMandatoryPropertiesAreSet(e) //NOSONAR: A method reference will rise a BootstrapMethodError.
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
    return table.technicalGetRefEntitiesInLocalData().containsAny(e -> e.hasId(id));
  }

  //method
  @Override
  public <E extends IEntity> IContainer<IColumn> getColumsThatReferenceGivenTable(
    final ITable<E> table) {

    final var columns = new LinkedList<IColumn>();
    for (final var t : table.getStoredParentDatabase().getStoredTables()) {
      for (final var c : t.getStoredColumns()) {
        if (c.getParameterizedPropertyType().referencesTable(table)) {
          columns.addAtEnd(c);
        }
      }
    }

    return columns;
  }

  //method
  @Override
  public boolean hasChanges(final ITable<?> table) {
    return table.technicalGetRefEntitiesInLocalData().containsAny(e -> !ENTITY_TOOL.isLoaded(e));
  }

  //method
  @Override
  public boolean hasInsertedGivenEntityInLocalData(final ITable<?> table, final IEntity entity) {
    return containsEntityWithGivenIdInLocalData(table, entity.getId());
  }
}
