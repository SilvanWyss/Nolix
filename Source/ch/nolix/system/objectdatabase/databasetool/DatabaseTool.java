//package declaration
package ch.nolix.system.objectdatabase.databasetool;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.databaseobject.databaseobjecttool.DatabaseObjectTool;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IDatabase;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;
import ch.nolix.systemapi.objectdatabaseapi.databasetoolapi.IDatabaseTool;
import ch.nolix.systemapi.objectdatabaseapi.databasetoolapi.ITableTool;

//class
public final class DatabaseTool extends DatabaseObjectTool implements IDatabaseTool {

  //constant
  private static final ITableTool TABLE_TOOL = new TableTool();

  //method
  @Override
  public boolean allNewAndEditedMandatoryPropertiesAreSet(final IDatabase database) {
    return database.getStoredTables().containsOnly(TABLE_TOOL::allNewAndEditedMandatoryPropertiesAreSet);
  }

  //method
  @Override
  public boolean canSaveChanges(final IDatabase database) {
    return database.isOpen()
    && database.isLinkedWithRealDatabase()
    && allNewAndEditedMandatoryPropertiesAreSet(database);
  }

  //method
  @Override
  public IContainer<IEntity> getStoredEntitiesInLocalData(final IDatabase database) {
    return database.getStoredTables().toFromGroups(ITable::technicalGetRefEntitiesInLocalData);
  }

  //method
  @Override
  @SuppressWarnings("unchecked")
  public <E extends IEntity> ITable<E> getStoredTableForGivenEntity(
    final IDatabase database,
    final E entity) {
    return database.getStoredTableByEntityType((Class<E>) entity.getClass());
  }

  //method
  @Override
  public boolean hasChanges(final IDatabase database) {
    return database.getStoredTables().containsAny(TABLE_TOOL::hasChanges);
  }
}
