package ch.nolix.systemapi.objectdataapi.datatoolapi;

import java.util.Optional;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseobjectapi.modelexaminerapi.IDatabaseObjectExaminer;
import ch.nolix.systemapi.objectdataapi.modelapi.IAbstractBackReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IAbstractReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IField;

public interface IEntityTool extends IDatabaseObjectExaminer {

  boolean allNewAndEditedMandatoryFieldsAreSet(IEntity entity);

  boolean canBeDeleted(IEntity entity);

  boolean canBeInsertedIntoTable(IEntity entity);

  Optional<? extends IAbstractBackReference<?>> //
  getOptionalStoredBaseBackReferenceOfEntityThatWouldBackReferenceBaseReference(
    IEntity entity,
    IAbstractReference<? extends IEntity> baseReference);

  IContainer<IAbstractBackReference<IEntity>> getStoredBaseBackReferences(IEntity entity);

  IContainer<? extends IField> getStoredEditedFields(IEntity entity);

  IContainer<? extends IField> getStoredReferencingFields(IEntity entity);

  boolean isReferenced(IEntity entity);

  boolean isReferencedIgnoringLocallyDeletedEntities(IEntity entity);

  boolean isReferencedInLocalData(IEntity entity);

  boolean referencesGivenEntity(IEntity sourceEntity, IEntity entity);
}
