package ch.nolix.systemapi.objectdataapi.datatoolapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseobjectapi.databaseobjecttoolapi.IDatabaseObjectExaminer;
import ch.nolix.systemapi.objectdataapi.dataapi.IDatabase;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;

public interface IDatabaseTool extends IDatabaseObjectExaminer {

  boolean allNewAndEditedMandatoryFieldsAreSet(IDatabase database);

  boolean canSaveChanges(IDatabase database);

  IContainer<IEntity> getStoredEntitiesInLocalData(IDatabase database);

  <E extends IEntity> ITable<E> getStoredTableForGivenEntity(IDatabase database, E entity);

  boolean hasChanges(IDatabase database);
}
