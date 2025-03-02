package ch.nolix.system.objectdata.model;

import java.util.Optional;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.fieldvalidator.ReferenceValidator;
import ch.nolix.system.objectdata.modelexaminer.FieldExaminer;
import ch.nolix.system.objectdata.modelsearcher.EntitySearcher;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectproperty.DatabaseObjectState;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectdataapi.fieldvalidatorapi.IReferenceValidator;
import ch.nolix.systemapi.objectdataapi.modelapi.IAbstractBackReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IField;
import ch.nolix.systemapi.objectdataapi.modelapi.IReference;
import ch.nolix.systemapi.objectdataapi.modelexaminerapi.IFieldExaminer;
import ch.nolix.systemapi.objectdataapi.modelsearcherapi.IEntitySearcher;

public final class Reference<E extends IEntity> extends AbstractReference<E> implements IReference<E> {

  private static final IEntitySearcher ENTITY_SEARCHER = new EntitySearcher();

  private static final IFieldExaminer FIELD_EXAMINER = new FieldExaminer();

  private static final BaseReferenceUpdater BASE_BACK_REFERENCE_UPDATER = new BaseReferenceUpdater();

  private static final IReferenceValidator REFERENCE_VALIDATOR = new ReferenceValidator();

  private String referencedEntityId;

  private Reference(final String referencedTableName) {
    super(referencedTableName);
  }

  public static <E2 extends Entity> Reference<E2> forEntity(final Class<? extends E2> referencedEntityType) {

    final var referencedTableName = referencedEntityType.getSimpleName();

    return new Reference<>(referencedTableName);
  }

  public static Reference<AbstractEntity> forTable(final String referencedTableName) {
    return new Reference<>(referencedTableName);
  }

  @Override
  public String getReferencedEntityId() {

    REFERENCE_VALIDATOR.assertIsNotEmpty(this);

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
    return ContentType.REFERENCE;
  }

  @Override
  public void internalSetOptionalContent(final Object content) {
    referencedEntityId = (String) content;
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

    final var entity = getStoredReferencedTable().getStoredEntityById(id);

    setEntity(entity);
  }

  @Override
  void updateBackReferencingFieldsWhenIsInsertedIntoDatabase() {
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
    return //
    ENTITY_SEARCHER
      .getStoredFieldsThatAreBackReferencedFrom(entity)
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
    for (final var brp : getStoredAbstractBackReferencesThatReferencesBackThis()) {
      updateBackReferencingFieldForClear(brp);
    }
  }

  private void updatePropableBackReferencingFieldOfEntityForClear(final E entity) {

    for (final var bbr : getStoredAbstractBackReferencesThatReferencesBackThis()) {
      if (FIELD_EXAMINER.isForSingleContent(bbr)) {
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
