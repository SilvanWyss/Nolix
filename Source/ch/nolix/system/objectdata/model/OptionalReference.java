package ch.nolix.system.objectdata.model;

import java.util.Optional;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.fieldtool.OptionalReferenceTool;
import ch.nolix.system.objectdata.fieldvalidator.OptionalReferenceValidator;
import ch.nolix.system.objectdata.modelsearcher.EntitySearcher;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectproperty.DatabaseObjectState;
import ch.nolix.systemapi.objectdataapi.fieldapi.IOptionalReference;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IOptionalReferenceTool;
import ch.nolix.systemapi.objectdataapi.fieldvalidatorapi.IOptionalReferenceValidator;
import ch.nolix.systemapi.objectdataapi.modelapi.IAbstractBackReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IField;
import ch.nolix.systemapi.objectdataapi.modelsearcherapi.IEntitySearcher;

public final class OptionalReference<E extends IEntity> extends AbstractReference<E> implements IOptionalReference<E> {

  private static final IEntitySearcher ENTITY_SEARCHER = new EntitySearcher();

  private static final BaseReferenceUpdater BASE_BACK_REFERENCE_UPDATER = new BaseReferenceUpdater();

  private static final IOptionalReferenceValidator OPTIONAL_REFERENCE_VALIDATOR = new OptionalReferenceValidator();

  private static final IOptionalReferenceTool OPTIONAL_REFERENCE_TOOL = new OptionalReferenceTool();

  private String referencedEntityId;

  private OptionalReference(final String referencedTableName) {
    super(referencedTableName);
  }

  public static <E2 extends Entity> OptionalReference<E2> forEntity(final Class<E2> referencedEntityType) {

    final var referencedTableName = referencedEntityType.getSimpleName();

    return new OptionalReference<>(referencedTableName);
  }

  public static OptionalReference<AbstractEntity> forTable(final String referencedTableName) {
    return new OptionalReference<>(referencedTableName);
  }

  @Override
  public void clear() {
    if (containsAny()) {
      clearWhenContainsAny();
    }
  }

  @Override
  public String getReferencedEntityId() {

    OPTIONAL_REFERENCE_VALIDATOR.assertIsNotEmpty(this);

    return referencedEntityId;
  }

  @Override
  @SuppressWarnings("unchecked")
  public IContainer<IAbstractBackReference<IEntity>> getStoredAbstractBackReferencesThatReferencesBackThis() {

    if (isEmpty()) {
      return ImmutableList.createEmpty();
    }

    final var fields = getStoredReferencedEntity().internalGetStoredFields();
    final var abstractBackReferenceContainer = fields.getOptionalStoredFirst(f -> f.referencesBackField(this));

    if (abstractBackReferenceContainer.isPresent()) {

      final var abstractBackReference = (IAbstractBackReference<IEntity>) abstractBackReferenceContainer.get();

      return ImmutableList.withElement(abstractBackReference);
    }

    return ImmutableList.createEmpty();
  }

  @Override
  public E getStoredReferencedEntity() {
    return getStoredReferencedTable().getStoredEntityById(getReferencedEntityId());
  }

  @Override
  public ContentType getType() {
    return ContentType.OPTIONAL_REFERENCE;
  }

  @Override
  public void internalSetOptionalContent(final Object content) {
    if (content == null) {
      referencedEntityId = null;
    } else {
      referencedEntityId = (String) content;
    }
  }

  @Override
  public boolean isEmpty() {
    return (referencedEntityId == null);
  }

  @Override
  public boolean isMandatory() {
    return false;
  }

  @Override
  public boolean referencesEntity(IEntity entity) {
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

    final var entity = getStoredReferencedTable().getStoredEntityById(id);

    setEntity(entity);
  }

  @Override
  void updateBackReferencingFieldsWhenIsInsertedIntoDatabase() {
    if (containsAny()) {
      updateProbableBackReferenceForSetOrAddedEntity(getStoredReferencedEntity());
    }
  }

  private void assertCanClear() {
    OPTIONAL_REFERENCE_VALIDATOR.assertCanClear(this);
  }

  private void assertCanSetEntity(final E entity) {
    OPTIONAL_REFERENCE_VALIDATOR.assertCanSetGivenEntity(this, entity);
  }

  private void clearWhenContainsAny() {

    assertCanClear();

    updateProbableBackReferencingFieldForClear();

    updateStateForClear();

    setAsEditedAndRunPotentialUpdateAction();
  }

  private Optional<? extends IField> getOptionalPendantReferencingFieldToEntity(final E entity) {
    return //
    ENTITY_SEARCHER
      .getStoredFieldsThatAreBackReferencedFrom(entity)
      .getOptionalStoredFirst(f -> f.hasName(getName()));
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

    updatePropbableBackReferencingFieldOfEntityForClear(entity);

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
        break;
      case OPTIONAL_BACK_REFERENCE:
        final var optionalBackReference = (OptionalBackReference<?>) backReferencingField;
        optionalBackReference.internalClear();
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
    if (containsAny()) {
      updateProbableBackReferencingFieldForClearWhenIsNotEmpty();
    }
  }

  private void updateProbableBackReferencingFieldForClearWhenIsNotEmpty() {

    final var backReferencingField = OPTIONAL_REFERENCE_TOOL.getOptionalStoredBackReferencingField(this);

    backReferencingField.ifPresent(this::updateBackReferencingFieldForClear);
  }

  private void updatePropbableBackReferencingFieldOfEntityForClear(final E entity) {

    final var pendantReferencingField = getOptionalPendantReferencingFieldToEntity(entity);

    if (pendantReferencingField.isPresent()) {
      final var reference = (OptionalReference<?>) pendantReferencingField.get();
      reference.clear();
    }
  }

  private void updateStateForSetEntity(final E entity) {
    referencedEntityId = entity.getId();
  }

  private void updateStateForClear() {
    referencedEntityId = null;
  }
}
