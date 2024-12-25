package ch.nolix.system.objectdata.data;

import java.util.Optional;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.datatool.EntityTool;
import ch.nolix.system.objectdata.fieldtool.FieldTool;
import ch.nolix.system.objectdata.fieldvalidator.ReferenceValidator;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectproperty.DatabaseObjectState;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;
import ch.nolix.systemapi.objectdataapi.dataapi.IReference;
import ch.nolix.systemapi.objectdataapi.datatoolapi.IEntityTool;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectdataapi.fieldvalidatorapi.IReferenceValidator;
import ch.nolix.systemapi.rawdataapi.datadto.StringContentFieldDto;

public final class Reference<E extends IEntity> extends BaseReference<E> implements IReference<E> {

  private static final IEntityTool ENTITY_TOOL = new EntityTool();

  private static final BaseReferenceUpdater BASE_BACK_REFERENCE_UPDATER = new BaseReferenceUpdater();

  private static final IReferenceValidator REFERENCE_VALIDATOR = new ReferenceValidator();

  private String referencedEntityId;

  private Reference(final String referencedTableName) {
    super(referencedTableName);
  }

  public static <E2 extends Entity> Reference<E2> forEntity(final Class<? extends E2> type) {
    return new Reference<>(type.getSimpleName());
  }

  public static Reference<BaseEntity> forEntityWithTableName(final String tableName) {
    return new Reference<>(tableName);
  }

  @Override
  public String getReferencedEntityId() {

    REFERENCE_VALIDATOR.assertIsNotEmpty(this);

    return referencedEntityId;
  }

  @Override
  @SuppressWarnings("unchecked")
  public IContainer<IBaseBackReference<IEntity>> getStoredBaseBackReferences() {

    if (isEmpty()) {
      return ImmutableList.createEmpty();
    }

    final var backReferencingField = getStoredReferencedEntity().internalGetStoredFields()
      .getOptionalStoredFirst(p -> p.referencesBackField(this));

    if (backReferencingField.isPresent()) {
      return ImmutableList.withElement((IBaseBackReference<IEntity>) backReferencingField.get());
    }

    return ImmutableList.createEmpty();
  }

  @Override
  public E getStoredReferencedEntity() {
    return getReferencedTable().getStoredEntityById(getReferencedEntityId());
  }

  @Override
  public ContentType getType() {
    return ContentType.REFERENCE;
  }

  @Override
  public void internalSetOrClearContent(final Object content) {
    referencedEntityId = (String) content;
  }

  @Override
  public StringContentFieldDto internalToContentField() {
    return StringContentFieldDto.withColumnNameAndContent(getName(), getReferencedEntityId());
  }

  @Override
  public boolean isEmpty() {
    return (referencedEntityId == null);
  }

  @Override
  public boolean isMandatory() {
    return true;
  }

  @Override
  public boolean referencesEntity(final IEntity entity) {
    return containsAny()
    && entity != null
    && getReferencedEntityId().equals(entity.getId());
  }

  @Override
  public boolean referencesUninsertedEntity() {
    return containsAny()
    && !getStoredReferencedEntity().belongsToTable();
  }

  @Override
  @SuppressWarnings("unchecked")
  public void setEntity(final Object entity) {
    setCastedEntity((E) entity);
  }

  @Override
  public void setEntityById(final String id) {

    final var entity = getReferencedTable().getStoredEntityById(id);

    setEntity(entity);
  }

  @Override
  void internalUpdatePotentialBaseBackReferencesWhenIsInsertedIntoDatabase() {
    if (containsAny()) {
      updateProbableBackReferenceForSetOrAddedEntity(getStoredReferencedEntity());
    }
  }

  private void assertCanSetEntity(final E entity) {
    REFERENCE_VALIDATOR.assertCanSetGivenEntity(this, entity);
  }

  private void clear() {
    if (containsAny()) {
      clearWhenContainsAny();
    }
  }

  private void clearWhenContainsAny() {

    updateProbableBackReferencingFieldForClear();

    updateStateForClear();

    setAsEditedAndRunPotentialUpdateAction();
  }

  private Optional<? extends IField> getOptionalPendantReferencingFieldToEntity(final E entity) {
    return ENTITY_TOOL
      .getStoredReferencingFields(entity)
      .getOptionalStoredFirst(rp -> rp.hasName(getName()));
  }

  private void insertEntityIntoDatabaseIfPossible(final E entity) {
    if (belongsToEntity()
    && getStoredParentEntity().belongsToTable()
    && entity.getState() == DatabaseObjectState.NEW
    && !entity.belongsToTable()) {
      getStoredParentEntity().getStoredParentDatabase().insertEntity(entity);
    }
  }

  private void setCastedEntity(final E entity) {

    assertCanSetEntity(entity);

    updatePropableBackReferencingFieldOfEntityForClear(entity);

    clear();

    updateStateForSetEntity(entity);

    updatePotentialBaseBackReferenceOfEntityForSetEntity(entity);

    insertEntityIntoDatabaseIfPossible(entity);

    setAsEditedAndRunPotentialUpdateAction();
  }

  private void updateBackReferencingFieldForClear(final IField backReferencingField) {
    switch (backReferencingField.getType()) {
      case BACK_REFERENCE:
        final var backReference = (BackReference<?>) backReferencingField;
        backReference.internalClear();
        backReference.setAsEditedAndRunPotentialUpdateAction();
        break;
      case OPTIONAL_BACK_REFERENCE:
        final var optionalBackReference = (OptionalBackReference<?>) backReferencingField;
        optionalBackReference.internalClear();
        optionalBackReference.setAsEditedAndRunPotentialUpdateAction();
        break;
      case MULTI_BACK_REFERENCE:
        /*
         * Does nothing. MultiBackReferences do not need to be updated, because
         * MultiBackReferences do not have redundancies.
         */
        break;
      default:
        //Does nothing.
    }
  }

  private void updatePotentialBaseBackReferenceOfEntityForSetEntity(final E entity) {
    BASE_BACK_REFERENCE_UPDATER.ofBaseReferenceUpdatePotentialBaseBackReferenceForAddOrSetEntity(this, entity);
  }

  private void updateProbableBackReferencingFieldForClear() {
    for (final var brp : getStoredBaseBackReferences()) {
      updateBackReferencingFieldForClear(brp);
    }
  }

  private void updatePropableBackReferencingFieldOfEntityForClear(final E entity) {

    for (final var bbr : getStoredBaseBackReferences()) {
      if (new FieldTool().isForSingleContent(bbr)) {
        final var pendantReferencingField = getOptionalPendantReferencingFieldToEntity(entity);

        if (pendantReferencingField.isPresent()) {
          final var reference = (Reference<?>) pendantReferencingField.get();
          reference.clear();
        }
      }
    }

  }

  private void updateStateForClear() {
    referencedEntityId = null;
  }

  private void updateStateForSetEntity(final E entity) {
    referencedEntityId = entity.getId();
  }
}
