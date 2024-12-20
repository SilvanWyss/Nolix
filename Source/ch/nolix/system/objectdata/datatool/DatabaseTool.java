package ch.nolix.system.objectdata.datatool;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.databaseobject.databaseobjecttool.DatabaseObjectTool;
import ch.nolix.systemapi.objectdataapi.dataapi.IDatabase;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.datatoolapi.IDatabaseTool;
import ch.nolix.systemapi.objectdataapi.datatoolapi.ITableTool;

public final class DatabaseTool extends DatabaseObjectTool implements IDatabaseTool {

  private static final ITableTool TABLE_TOOL = new TableTool();

  @Override
  public boolean allNewAndEditedMandatoryFieldsAreSet(final IDatabase database) {
    return database.getStoredTables().containsOnly(TABLE_TOOL::allNewAndEditedMandatoryFieldsAreSet);
  }

  @Override
  public boolean canSaveChanges(final IDatabase database) {
    return database.isOpen()
    && database.isLinkedWithRealDatabase()
    && allNewAndEditedMandatoryFieldsAreSet(database);
  }

  @Override
  public IContainer<IEntity> getStoredEntitiesInLocalData(final IDatabase database) {
    return database.getStoredTables().toMultiple(ITable::internalGetStoredEntitiesInLocalData);
  }

  @Override
  @SuppressWarnings("unchecked")
  public <E extends IEntity> ITable<E> getStoredTableForGivenEntity(
    final IDatabase database,
    final E entity) {
    return database.getStoredTableByEntityType((Class<E>) entity.getClass());
  }

  @Override
  public boolean hasChanges(final IDatabase database) {
    return database.getStoredTables().containsAny(TABLE_TOOL::hasChanges);
  }
}
