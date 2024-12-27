package ch.nolix.systemapi.objectdataapi.datatoolapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseobjectapi.databaseobjecttoolapi.IDatabaseObjectExaminer;
import ch.nolix.systemapi.objectdataapi.modelapi.IDatabase;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;

public interface IDatabaseTool extends IDatabaseObjectExaminer {

  boolean allNewAndEditedMandatoryFieldsAreSet(IDatabase database);

  boolean canSaveChanges(IDatabase database);

  IContainer<IEntity> getStoredEntitiesInLocalData(IDatabase database);

  <E extends IEntity> ITable<E> getStoredTableForGivenEntity(IDatabase database, E entity);

  boolean hasChanges(IDatabase database);
}
