package ch.nolix.system.objectdata.datatool;

import java.util.Optional;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.system.objectdata.fieldtool.FieldTool;
import ch.nolix.systemapi.objectdataapi.datatoolapi.IEntityTool;
import ch.nolix.systemapi.objectdataapi.modelapi.IAbstractBackReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IAbstractReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IField;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;

public final class EntityTool extends DatabaseObjectExaminer implements IEntityTool {

  private static final FieldTool FIELD_TOOL = new FieldTool();

  @Override
  public boolean allNewAndEditedMandatoryFieldsAreSet(final IEntity entity) {

    if (isNewOrEdited(entity)) {
      return entity.internalGetStoredFields().containsOnly(FIELD_TOOL::isSetForCaseIsNewOrEditedAndMandatory);
    }

    return true;
  }

  @Override
  public boolean canBeDeleted(final IEntity entity) {
    return //
    entity != null
    && entity.isLoaded()
    && !isReferencedIgnoringLocallyDeletedEntities(entity);
  }

  @Override
  public boolean canBeInsertedIntoTable(final IEntity entity) {
    return //
    entity != null
    && entity.isNew()
    && entity.belongsToTable();
  }

  @Override
  public Optional<? extends IAbstractBackReference<?>> //
  getOptionalStoredBaseBackReferenceOfEntityThatWouldBackReferenceBaseReference(
    final IEntity entity,
    final IAbstractReference<? extends IEntity> baseReference) {

    for (final var p : entity.internalGetStoredFields()) {
      if (p instanceof final IAbstractBackReference<?> baseBackReference
      && baseBackReferenceWouldReferenceBackBaseReference(baseBackReference, baseReference)) {
        return Optional.of(baseBackReference);
      }
    }

    return Optional.empty();
  }

  @Override
  public IContainer<IAbstractBackReference<IEntity>> getStoredBaseBackReferences(final IEntity entity) {
    return entity.internalGetStoredFields().toMultiple(IField::getStoredBaseBackReferences);
  }

  @Override
  public IContainer<? extends IField> getStoredEditedFields(final IEntity entity) {
    return entity.internalGetStoredFields().getStoredSelected(IField::isEdited);
  }

  @Override
  public IContainer<? extends IField> getStoredReferencingFields(final IEntity entity) {
    return entity.internalGetStoredFields().toMultiple(IField::getStoredReferencingFields);
  }

  @Override
  public boolean isReferenced(final IEntity entity) {
    return //
    isReferencedInLocalData(entity)
    || entity.isReferencedInPersistedData();
  }

  @Override
  public boolean isReferencedIgnoringLocallyDeletedEntities(IEntity entity) {
    return //
    isReferencedInLocalData(entity)
    || isReferencedInPersistedDataIgnoringLocallyDeletedEntities(entity);
  }

  @Override
  public boolean isReferencedInLocalData(final IEntity entity) {

    if (!entity.belongsToTable()) {
      return false;
    }

    for (final var t : entity.getStoredParentTable().getStoredParentDatabase().getStoredTables()) {
      if (t.internalGetStoredEntitiesInLocalData().containsAny(e -> referencesGivenEntity(e, entity))) {
        return true;
      }
    }

    return false;
  }

  @Override
  public boolean referencesGivenEntity(final IEntity sourceEntity, final IEntity entity) {
    return sourceEntity.internalGetStoredFields().containsAny(p -> p.referencesEntity(entity));
  }

  private boolean baseBackReferenceWouldReferenceBackBaseReference(
    final IAbstractBackReference<?> baseBackReference,
    final IAbstractReference<? extends IEntity> baseReference) {
    return //
    baseBackReference.getBackReferencedTableName().equals(baseReference.getStoredParentEntity().getParentTableName())
    && baseBackReference.getBackReferencedFieldName().equals(baseReference.getName());
  }

  private boolean isReferencedInPersistedDataIgnoringLocallyDeletedEntities(final IEntity entity) {

    if (entity.isReferencedInPersistedData()) {

      final var locallyDeletedEntities = getLocallyDeletedEntities(entity);

      return entity.isReferencedInPersistedDataIgnoringGivenEntities(locallyDeletedEntities);
    }

    return false;
  }

  private IContainer<String> getLocallyDeletedEntities(final IEntity entity) {
    return //
    entity
      .getStoredParentTable()
      .getStoredParentDatabase()
      .getStoredTables()
      .toMultiple(ITable::internalGetStoredEntitiesInLocalData).getStoredSelected(IEntity::isDeleted)
      .to(IEntity::getId);
  }
}
