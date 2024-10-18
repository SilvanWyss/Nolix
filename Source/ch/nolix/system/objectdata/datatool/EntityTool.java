package ch.nolix.system.objectdata.datatool;

import java.util.Optional;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.databaseobject.databaseobjecttool.DatabaseObjectTool;
import ch.nolix.system.objectdata.fieldtool.FieldTool;
import ch.nolix.system.sqlrawdata.datadto.EntityHeadDto;
import ch.nolix.system.sqlrawdata.datadto.EntityUpdateDto;
import ch.nolix.system.sqlrawdata.datadto.NewEntityDto;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.datatoolapi.IEntityTool;
import ch.nolix.systemapi.objectdataapi.fieldproperty.BaseContentType;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityHeadDto;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityUpdateDto;
import ch.nolix.systemapi.rawdataapi.datadtoapi.INewEntityDto;

public final class EntityTool extends DatabaseObjectTool implements IEntityTool {

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
  public boolean containsMandatoryAndEmptyBaseValuesOrBaseReferences(final IEntity entity) {
    return entity.internalGetStoredFields().containsAny(this::isMandatoryAndEmptyBaseValueOrBaseReference);
  }

  @Override
  public IEntityUpdateDto createEntityUpdateDtoForEntity(final IEntity entity) {
    return new EntityUpdateDto(
      entity.getId(),
      entity.getSaveStamp(),
      getStoredEditedFields(entity).to(IField::internalToContentField));
  }

  @Override
  public IEntityHeadDto createEntityHeadDtoForEntity(IEntity entity) {
    return new EntityHeadDto(entity.getId(), entity.getSaveStamp());
  }

  @Override
  public INewEntityDto createNewEntityDtoForEntity(final IEntity entity) {
    return //
    new NewEntityDto(entity.getId(), entity.internalGetStoredFields().to(IField::internalToContentField));
  }

  @Override
  public Optional<? extends IBaseBackReference<?>> //
  getOptionalStoredBaseBackReferenceOfEntityThatWouldBackReferenceBaseReference(
    final IEntity entity,
    final IBaseReference<? extends IEntity> baseReference) {

    for (final var p : entity.internalGetStoredFields()) {
      if (p instanceof final IBaseBackReference<?> baseBackReference
      && baseBackReferenceWouldReferenceBackBaseReference(baseBackReference, baseReference)) {
        return Optional.of(baseBackReference);
      }
    }

    return Optional.empty();
  }

  @Override
  public IContainer<IBaseBackReference<IEntity>> getStoredBaseBackReferences(final IEntity entity) {
    return entity.internalGetStoredFields().toFromGroups(IField::getStoredBaseBackReferences);
  }

  @Override
  public IContainer<? extends IField> getStoredEditedFields(final IEntity entity) {
    return entity.internalGetStoredFields().getStoredSelected(IField::isEdited);
  }

  @Override
  public IContainer<? extends IField> getStoredReferencingFields(final IEntity entity) {
    return entity.internalGetStoredFields().toFromGroups(IField::getStoredReferencingFields);
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

  @Override
  public boolean referencesUninsertedEntity(final IEntity entity) {
    return entity.internalGetStoredFields().containsAny(IField::referencesUninsertedEntity);
  }

  private boolean baseBackReferenceWouldReferenceBackBaseReference(
    final IBaseBackReference<?> baseBackReference,
    final IBaseReference<? extends IEntity> baseReference) {
    return //
    baseBackReference.getBackReferencedTableName().equals(baseReference.getStoredParentEntity().getParentTableName())
    && baseBackReference.getBackReferencedFieldName().equals(baseReference.getName());
  }

  private boolean isBaseValueOrBaseReference(final IField field) {

    final var baseType = field.getType().getBaseType();

    return baseType == BaseContentType.BASE_VALUE
    || baseType == BaseContentType.BASE_REFERENCE;
  }

  private boolean isMandatoryAndEmptyBaseValueOrBaseReference(final IField field) {
    return isBaseValueOrBaseReference(field)
    && FIELD_TOOL.isMandatoryAndEmptyBoth(field);
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
      .toFromGroups(ITable::internalGetStoredEntitiesInLocalData).getStoredSelected(IEntity::isDeleted)
      .to(IEntity::getId);
  }
}
