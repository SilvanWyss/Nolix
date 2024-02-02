//package declaration
package ch.nolix.systemapi.objectdataapi.datatoolapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseobjectapi.databaseobjecttoolapi.IDatabaseObjectTool;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IProperty;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityHeadDto;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDto;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.INewEntityDto;

//interface
public interface IEntityTool extends IDatabaseObjectTool {

  //method declaration
  boolean allNewAndEditedMandatoryPropertiesAreSet(IEntity entity);

  //method declaration
  boolean canBeDeleted(IEntity entity);

  //method declaration
  boolean canBeInsertedIntoTable(IEntity entity);

  //method declaration
  boolean containsMandatoryAndEmptyBaseValuesOrBaseReferences(IEntity entity);

  //method declaration
  IEntityUpdateDto createEntityUpdateDtoForEntity(IEntity entity);

  //method declaration
  IEntityHeadDto createEntityHeadDtoForEntity(IEntity entity);

  //method declaration
  INewEntityDto createNewEntityDtoForEntity(IEntity entity);

  //method declaration
  IContainer<IProperty> getStoredBackReferencingProperties(IEntity entity);

  //method declaration
  IContainer<? extends IProperty> getStoredEditedProperties(IEntity entity);

  //method declaration
  IContainer<? extends IProperty> getStoredReferencingProperties(IEntity entity);

  //method declaration
  boolean isReferenced(IEntity entity);

  //method declaration
  boolean isReferencedInLocalData(IEntity entity);

  //method declaration
  boolean referencesGivenEntity(IEntity sourceEntity, IEntity entity);

  //method declaration
  boolean referencesUninsertedEntity(IEntity entity);
}
