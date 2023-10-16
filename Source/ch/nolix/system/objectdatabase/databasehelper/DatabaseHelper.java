//package declaration
package ch.nolix.system.objectdatabase.databasehelper;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.database.databaseobjecthelper.DatabaseObjectHelper;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IDatabase;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;
import ch.nolix.systemapi.objectdatabaseapi.databasehelperapi.IDatabaseHelper;
import ch.nolix.systemapi.objectdatabaseapi.databasehelperapi.ITableHelper;

//class
public final class DatabaseHelper extends DatabaseObjectHelper implements IDatabaseHelper {

  //constant
  private static final ITableHelper TABLE_HELPER = new TableHelper();

  //method
  @Override
  public boolean allNewAndEditedMandatoryPropertiesAreSet(final IDatabase database) {
    return database.getStoredTables().containsOnly(TABLE_HELPER::allNewAndEditedMandatoryPropertiesAreSet);
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
    return database.getStoredTables().containsAny(TABLE_HELPER::hasChanges);
  }
}
