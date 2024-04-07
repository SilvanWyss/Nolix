//package declaration
package ch.nolix.systemapi.objectdataapi.datatoolapi;

//Java imports
import java.util.Optional;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseobjectapi.databaseobjecttoolapi.IDatabaseObjectTool;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityHeadDto;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityUpdateDto;
import ch.nolix.systemapi.rawdataapi.datadtoapi.INewEntityDto;

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

  //method
  Optional<? extends IBaseBackReference<?>> //
  getOptionalStoredBaseBackReferenceOfEntityThatWouldBackReferenceBaseReference(
    IEntity entity,
    IBaseReference<? extends IEntity> baseReference);

  //method declaration
  IContainer<IBaseBackReference<IEntity>> getStoredBaseBackReferences(IEntity entity);

  //method declaration
  IContainer<? extends IField> getStoredEditedProperties(IEntity entity);

  //method declaration
  IContainer<? extends IField> getStoredReferencingProperties(IEntity entity);

  //method declaration
  boolean isReferenced(IEntity entity);

  //method declaration
  boolean isReferencedInLocalData(IEntity entity);

  //method declaration
  boolean referencesGivenEntity(IEntity sourceEntity, IEntity entity);

  //method declaration
  boolean referencesUninsertedEntity(IEntity entity);
}
