package ch.nolix.systemapi.objectdataapi.datatoolapi;

import java.util.Optional;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseobjectapi.databaseobjecttoolapi.IDatabaseObjectTool;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityHeadDto;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityUpdateDto;
import ch.nolix.systemapi.rawdataapi.datadtoapi.INewEntityDto;

public interface IEntityTool extends IDatabaseObjectTool {

  boolean allNewAndEditedMandatoryFieldsAreSet(IEntity entity);

  boolean canBeDeleted(IEntity entity);

  boolean canBeInsertedIntoTable(IEntity entity);

  boolean containsMandatoryAndEmptyBaseValuesOrBaseReferences(IEntity entity);

  IEntityUpdateDto createEntityUpdateDtoForEntity(IEntity entity);

  IEntityHeadDto createEntityHeadDtoForEntity(IEntity entity);

  INewEntityDto createNewEntityDtoForEntity(IEntity entity);

  Optional<? extends IBaseBackReference<?>> //
  getOptionalStoredBaseBackReferenceOfEntityThatWouldBackReferenceBaseReference(
    IEntity entity,
    IBaseReference<? extends IEntity> baseReference);

  IContainer<IBaseBackReference<IEntity>> getStoredBaseBackReferences(IEntity entity);

  IContainer<? extends IField> getStoredEditedFields(IEntity entity);

  IContainer<? extends IField> getStoredReferencingFields(IEntity entity);

  boolean isReferenced(IEntity entity);

  boolean isReferencedIgnoringLocallyDeletedEntities(IEntity entity);

  boolean isReferencedInLocalData(IEntity entity);

  boolean referencesGivenEntity(IEntity sourceEntity, IEntity entity);

  boolean referencesUninsertedEntity(IEntity entity);
}
