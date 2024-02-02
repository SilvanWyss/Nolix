//package declaration
package ch.nolix.systemapi.objectdataapi.datatoolapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseobjectapi.databaseobjecttoolapi.IDatabaseObjectTool;
import ch.nolix.systemapi.objectdataapi.dataapi.IDatabase;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;

//interface
public interface IDatabaseTool extends IDatabaseObjectTool {

  //method declaration
  boolean allNewAndEditedMandatoryPropertiesAreSet(IDatabase database);

  //method declaration
  boolean canSaveChanges(IDatabase database);

  //method declaration
  IContainer<IEntity> getStoredEntitiesInLocalData(IDatabase database);

  //method declaration
  <E extends IEntity> ITable<E> getStoredTableForGivenEntity(IDatabase database, E entity);

  //method declaration
  boolean hasChanges(IDatabase database);
}
