package ch.nolix.system.objectdata.datatool;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.systemapi.objectdataapi.datatoolapi.IDatabaseTool;
import ch.nolix.systemapi.objectdataapi.modelapi.IDatabase;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;

public final class DatabaseTool extends DatabaseObjectExaminer implements IDatabaseTool {

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
}
