package ch.nolix.system.objectdata.model;

import java.util.Optional;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.system.objectdata.entitytool.TableNameExtractor;
import ch.nolix.system.objectdata.fieldexaminer.FieldExaminer;
import ch.nolix.system.objectdata.fieldvalidator.ReferenceValidator;
import ch.nolix.system.objectdata.modelsearcher.EntitySearcher;
import ch.nolix.systemapi.databaseobject.property.DatabaseObjectState;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectdata.entitytool.ITableNameExtractor;
import ch.nolix.systemapi.objectdata.fieldexaminer.IFieldExaminer;
import ch.nolix.systemapi.objectdata.fieldvalidator.IReferenceValidator;
import ch.nolix.systemapi.objectdata.model.IBaseBackReference;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IField;
import ch.nolix.systemapi.objectdata.model.IReference;
import ch.nolix.systemapi.objectdata.modelsearcher.IEntitySearcher;
import ch.nolix.systemapi.objectdata.structure.CachedEntityDto;

public final class Reference<E extends IEntity> extends AbstractBaseReference<E> implements IReference<E> {

  private static final ITableNameExtractor TABLE_NAME_EXTRACTOR = new TableNameExtractor();

  private static final IEntitySearcher ENTITY_SEARCHER = new EntitySearcher();

  private static final IFieldExaminer FIELD_EXAMINER = new FieldExaminer();

  private static final IReferenceValidator REFERENCE_VALIDATOR = new ReferenceValidator();

  private CachedEntityDto<E> cachedEntityDto;

  private Reference(final IContainer<String> referenceableTableNames) {
    super(referenceableTableNames);
  }

  @SafeVarargs
  public static <E2 extends IEntity> Reference<E2> forEntityType(
    final Class<? extends E2> entity,
    final Class<? extends E2>... entityTypes) {

    final var allEntityTypes = ContainerView.forElementAndArray(entity, entityTypes);
    final var referenceableTableNames = allEntityTypes.to(TABLE_NAME_EXTRACTOR::getTableNameOfEntityType);

    return new Reference<>(referenceableTableNames);
  }

  public static <E2 extends IEntity> Reference<E2> forReferenceableTableName(
    final String referenceableTableName,
    final String... referenceableTableNames) {

    final var allReferenceableTableNames = //
    ContainerView.forElementAndArray(referenceableTableName, referenceableTableNames);

    return new Reference<>(allReferenceableTableNames);
  }

  public static <E2 extends IEntity> Reference<E2> forReferenceableTableNames(
    final IContainer<String> referenceableTableNames) {
    return new Reference<>(referenceableTableNames);
  }

  @Override
  public String getReferencedEntityId() {

    REFERENCE_VALIDATOR.assertIsNotEmpty(this);

    return cachedEntityDto.id();
  }

  @Override
  @SuppressWarnings("unchecked")
  public IContainer<IBaseBackReference<IEntity>> getStoredBaseBackReferencesWhoReferencesBackThis() {

    if (isEmpty()) {
      return ImmutableList.createEmpty();
    }

    final var fields = getStoredReferencedEntity().internalGetStoredFields();
    final var abstractBackReferenceContainer = fields.getOptionalStoredFirst(f -> f.referencesBackField(this));

    if (abstractBackReferenceContainer.isPresent()) {

      final var abstractBackReference = (IBaseBackReference<IEntity>) abstractBackReferenceContainer.get();

      return ImmutableList.withElement(abstractBackReference);
    }

    return ImmutableList.createEmpty();
  }

  @Override
  public E getStoredReferencedEntity() {

    REFERENCE_VALIDATOR.assertIsNotEmpty(this);

    if (cachedEntityDto.nullableEntity() == null) {

      final var id = cachedEntityDto.id();
      final var tableId = cachedEntityDto.nullableTableId();
      final var entity = getStoredReferencedTable().getStoredEntityById(id);

      cachedEntityDto = new CachedEntityDto<>(id, tableId, entity);
    }

    return cachedEntityDto.nullableEntity();
  }

  @Override
  public FieldType getType() {
    return FieldType.REFERENCE;
  }

  @Override
  public void internalSetNullableValue(final Object nullableValue, final String nullableAdditionalValue) {

    final var id = (String) nullableValue;
    final var tableId = nullableAdditionalValue;

    cachedEntityDto = new CachedEntityDto<>(id, tableId, null);
  }

  @Override
  public boolean isEmpty() {
    return (cachedEntityDto == null);
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
  protected void noteInsertIntoDatabase() {
    if (containsAny()) {

      final var id = cachedEntityDto.id();
      final var entity = cachedEntityDto.nullableEntity();
      final var tableName = entity.getParentTableName();

      final var tableId = //
      getStoredParentEntity()
        .getStoredParentDatabase()
        .getStoredTableByName(tableName)
        .getId();

      cachedEntityDto = new CachedEntityDto<>(id, tableId, entity);
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
      .getStoredFieldsWhoAreBackReferencedFromEntity(entity)
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

  private void updatePotentialBaseBackReferenceOfEntityForSetEntity(final E entity) {
    BaseReferenceUpdater.ofBaseReferenceUpdatePotentialBaseBackReferenceForAddOrSetEntity(this, entity);
  }

  private void updateProbableBackReferencingFieldForClear() {
    for (final var brp : getStoredBaseBackReferencesWhoReferencesBackThis()) {
      BaseBackReferenceUpdater.updateBaseBackReferenceForClearBaseReference(brp);
    }
  }

  private void updatePropableBackReferencingFieldOfEntityForClear(final E entity) {

    for (final var bbr : getStoredBaseBackReferencesWhoReferencesBackThis()) {
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
    cachedEntityDto = null;
  }

  private void updateStateForSetEntity(final E entity) {

    final var id = entity.getId();

    if (entity.belongsToTable()) {

      final var tableId = entity.getStoredParentTable().getId();

      cachedEntityDto = new CachedEntityDto<>(id, tableId, entity);
    } else {
      cachedEntityDto = new CachedEntityDto<>(id, null, entity);
    }
  }
}
