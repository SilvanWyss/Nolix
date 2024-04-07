//package declaration
package ch.nolix.system.objectdata.datatool;

//Java imports
import java.util.Optional;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.databaseobject.databaseobjecttool.DatabaseObjectTool;
import ch.nolix.system.objectdata.fieldtool.FieldTool;
import ch.nolix.system.sqlrawdata.datadto.EntityHeadDto;
import ch.nolix.system.sqlrawdata.datadto.EntityUpdateDto;
import ch.nolix.system.sqlrawdata.datadto.NewEntityDto;
import ch.nolix.systemapi.fieldapi.mainapi.BaseFieldType;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;
import ch.nolix.systemapi.objectdataapi.datatoolapi.IEntityTool;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityHeadDto;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityUpdateDto;
import ch.nolix.systemapi.rawdataapi.datadtoapi.INewEntityDto;

//class
public final class EntityTool extends DatabaseObjectTool implements IEntityTool {

  //constant
  private static final FieldTool FIELD_TOOL = new FieldTool();

  //method
  @Override
  public boolean allNewAndEditedMandatoryPropertiesAreSet(final IEntity entity) {

    if (isNewOrEdited(entity)) {
      return entity.internalGetStoredProperties().containsOnly(FIELD_TOOL::isSetForCaseIsNewOrEditedAndMandatory);
    }

    return true;
  }

  //method
  @Override
  public boolean canBeDeleted(final IEntity entity) {
    return //
    entity != null
    && entity.isLoaded()
    && !isReferenced(entity);
  }

  //method
  @Override
  public boolean canBeInsertedIntoTable(final IEntity entity) {
    return //
    entity != null
    && entity.isNew()
    && entity.belongsToTable();
  }

  //method
  @Override
  public boolean containsMandatoryAndEmptyBaseValuesOrBaseReferences(final IEntity entity) {
    return entity.internalGetStoredProperties().containsAny(this::isMandatoryAndEmptyBaseValueOrBaseReference);
  }

  //method
  @Override
  public IEntityUpdateDto createEntityUpdateDtoForEntity(final IEntity entity) {
    return new EntityUpdateDto(
      entity.getId(),
      entity.getSaveStamp(),
      getStoredEditedProperties(entity).to(IField::internalToContentField));
  }

  //method
  @Override
  public IEntityHeadDto createEntityHeadDtoForEntity(IEntity entity) {
    return new EntityHeadDto(entity.getId(), entity.getSaveStamp());
  }

  //method
  @Override
  public INewEntityDto createNewEntityDtoForEntity(final IEntity entity) {
    return //
    new NewEntityDto(entity.getId(), entity.internalGetStoredProperties().to(IField::internalToContentField));
  }

  //method
  @Override
  public Optional<? extends IBaseBackReference<?>> //
  getOptionalStoredBaseBackReferenceOfEntityThatWouldBackReferenceBaseReference(
    final IEntity entity,
    final IBaseReference<? extends IEntity> baseReference) {

    for (final var p : entity.internalGetStoredProperties()) {
      if (p instanceof final IBaseBackReference<?> baseBackReference
      && baseBackReferenceWouldReferenceBackBaseReference(baseBackReference, baseReference)) {
        return Optional.of(baseBackReference);
      }
    }

    return Optional.empty();
  }

  //method
  @Override
  public IContainer<IBaseBackReference<IEntity>> getStoredBaseBackReferences(final IEntity entity) {
    return entity.internalGetStoredProperties().toFromGroups(IField::getStoredBaseBackReferences);
  }

  //method
  @Override
  public IContainer<? extends IField> getStoredEditedProperties(final IEntity entity) {
    return entity.internalGetStoredProperties().getStoredSelected(IField::isEdited);
  }

  //method
  @Override
  public IContainer<? extends IField> getStoredReferencingProperties(final IEntity entity) {
    return entity.internalGetStoredProperties().toFromGroups(IField::getStoredReferencingProperties);
  }

  //method
  @Override
  public boolean isReferenced(final IEntity entity) {
    return (isReferencedInLocalData(entity) || entity.isReferencedInPersistedData());
  }

  //method
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

  //method
  @Override
  public boolean referencesGivenEntity(final IEntity sourceEntity, final IEntity entity) {
    return sourceEntity.internalGetStoredProperties().containsAny(p -> p.referencesEntity(entity));
  }

  //method
  @Override
  public boolean referencesUninsertedEntity(final IEntity entity) {
    return entity.internalGetStoredProperties().containsAny(IField::referencesUninsertedEntity);
  }

  //method
  private boolean baseBackReferenceWouldReferenceBackBaseReference(
    final IBaseBackReference<?> baseBackReference,
    final IBaseReference<? extends IEntity> baseReference) {
    return //
    baseBackReference.getBackReferencedTableName().equals(baseReference.getStoredParentEntity().getParentTableName())
    && baseBackReference.getBackReferencedPropertyName().equals(baseReference.getName());
  }

  //method
  private boolean isBaseValueOrBaseReference(final IField field) {

    final var baseType = field.getType().getBaseType();

    return baseType == BaseFieldType.BASE_VALUE
    || baseType == BaseFieldType.BASE_REFERENCE;
  }

  //method
  private boolean isMandatoryAndEmptyBaseValueOrBaseReference(final IField field) {
    return isBaseValueOrBaseReference(field)
    && FIELD_TOOL.isMandatoryAndEmptyBoth(field);
  }
}
