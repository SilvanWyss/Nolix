package ch.nolix.system.objectdata.model;

import java.util.Optional;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.system.objectdata.entitytool.TableNameExtractor;
import ch.nolix.system.objectdata.fieldtool.OptionalReferenceTool;
import ch.nolix.system.objectdata.fieldvalidator.OptionalReferenceValidator;
import ch.nolix.system.objectdata.modelsearcher.DatabaseSearcher;
import ch.nolix.system.objectdata.modelsearcher.EntitySearcher;
import ch.nolix.systemapi.databaseobject.property.DatabaseObjectState;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectdata.entitytool.ITableNameExtractor;
import ch.nolix.systemapi.objectdata.fieldtool.IOptionalReferenceTool;
import ch.nolix.systemapi.objectdata.fieldvalidator.IOptionalReferenceValidator;
import ch.nolix.systemapi.objectdata.model.IBaseBackReference;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IField;
import ch.nolix.systemapi.objectdata.model.IOptionalReference;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.modelsearcher.IDatabaseSearcher;
import ch.nolix.systemapi.objectdata.modelsearcher.IEntitySearcher;
import ch.nolix.systemapi.objectdata.structure.EntityCache;

public final class OptionalReference<E extends IEntity>
extends AbstractBaseReference<E>
implements IOptionalReference<E> {
  private static final IDatabaseSearcher DATABASE_SEARCHER = new DatabaseSearcher();

  private static final ITableNameExtractor TABLE_NAME_EXTRACTOR = new TableNameExtractor();

  private static final IEntitySearcher ENTITY_SEARCHER = new EntitySearcher();

  private static final IOptionalReferenceValidator OPTIONAL_REFERENCE_VALIDATOR = new OptionalReferenceValidator();

  private static final IOptionalReferenceTool OPTIONAL_REFERENCE_TOOL = new OptionalReferenceTool();

  private EntityCache<E> nullableReferencedEntityCache;

  private OptionalReference(final IContainer<String> referenceableTableNames) {
    super(referenceableTableNames);
  }

  @SafeVarargs
  public static <E2 extends IEntity> OptionalReference<E2> forEntityType(
    final Class<? extends E2> entity,
    final Class<? extends E2>... entityTypes) {
    final var allEntityTypes = ContainerView.forElementAndArray(entity, entityTypes);
    final var referenceableTableNames = allEntityTypes.to(TABLE_NAME_EXTRACTOR::getTableNameOfEntityType);

    return new OptionalReference<>(referenceableTableNames);
  }

  public static <E2 extends IEntity> OptionalReference<E2> forReferenceableTableName(
    final String referenceableTableName,
    final String... referenceableTableNames) {
    final var allReferenceableTableNames = //
    ContainerView.forElementAndArray(referenceableTableName, referenceableTableNames);

    return new OptionalReference<>(allReferenceableTableNames);
  }

  public static <E2 extends IEntity> OptionalReference<E2> forReferenceableTableNames(
    final IContainer<String> referenceableTableNames) {
    return new OptionalReference<>(referenceableTableNames);
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

    return nullableReferencedEntityCache.id();
  }

  @Override
  public String getReferencedTableId() {
    retrieveReferencedTableId();

    return nullableReferencedEntityCache.nullableTableId();
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
    retrieveReferencedEntity();

    return nullableReferencedEntityCache.nullableEntity();
  }

  @Override
  @SuppressWarnings("unchecked")
  public ITable<E> getStoredReferencedTable() {
    //This part is not mandatory, but provides a better performance.
    final var referencedEntity = nullableReferencedEntityCache.nullableEntity();
    if (referencedEntity != null && referencedEntity.belongsToTable()) {
      return (ITable<E>) referencedEntity.getStoredParentTable();
    }

    if (belongsToDatabase()) {
      return (ITable<E>) DATABASE_SEARCHER.getStoredTableById(getStoredParentDatabase(), getReferencedTableId());
    }

    final var database = nullableReferencedEntityCache.nullableEntity().getStoredParentDatabase();

    return (ITable<E>) DATABASE_SEARCHER.getStoredTableById(database, getReferencedTableId());
  }

  @Override
  public FieldType getType() {
    return FieldType.OPTIONAL_REFERENCE;
  }

  @Override
  public void internalSetNullableValue(final Object nullableValue, final String nullableAdditionalValue) {

    final var id = (String) nullableValue;

    if (id == null) {
      nullableReferencedEntityCache = null;
    } else {

      final var tableId = nullableAdditionalValue;

      if (tableId == null) {
        throw ArgumentIsNullException.forArgumentName("table id");
      }

      nullableReferencedEntityCache = new EntityCache<>(id, tableId, null);
    }
  }

  @Override
  public boolean isEmpty() {
    return (nullableReferencedEntityCache == null);
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
  protected void noteInsertIntoDatabase() {
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
      .getStoredFieldsWhoAreBackReferencedFromEntity(entity)
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

  private void retrieveReferencedEntity() {
    OPTIONAL_REFERENCE_VALIDATOR.assertIsNotEmpty(this);

    var referencedEntity = nullableReferencedEntityCache.nullableEntity();

    if (referencedEntity == null) {
      final var referencedEntityId = nullableReferencedEntityCache.id();
      final var referencedTableId = nullableReferencedEntityCache.nullableTableId();
      referencedEntity = getStoredReferencedTable().getStoredEntityById(referencedEntityId);

      nullableReferencedEntityCache = new EntityCache<>(referencedEntityId, referencedTableId, referencedEntity);
    }
  }

  private void retrieveReferencedTableId() {
    OPTIONAL_REFERENCE_VALIDATOR.assertIsNotEmpty(this);

    var referencedTableId = nullableReferencedEntityCache.nullableTableId();

    if (referencedTableId == null) {
      final var referencedEntityId = nullableReferencedEntityCache.id();
      final var referencedEntity = nullableReferencedEntityCache.nullableEntity();
      final var referencedTable = referencedEntity.getStoredParentTable();
      referencedTableId = referencedTable.getId();

      nullableReferencedEntityCache = new EntityCache<>(referencedEntityId, referencedTableId, referencedEntity);
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

  private void updatePotentialBaseBackReferenceOfEntityForSetEntity(final E entity) {
    BaseReferenceUpdater.ofBaseReferenceUpdatePotentialBaseBackReferenceForAddOrSetEntity(this, entity);
  }

  private void updateProbableBackReferencingFieldForClear() {
    if (containsAny()) {
      updateProbableBackReferencingFieldForClearWhenIsNotEmpty();
    }
  }

  private void updateProbableBackReferencingFieldForClearWhenIsNotEmpty() {
    final var backReferencingField = OPTIONAL_REFERENCE_TOOL.getOptionalStoredBaseBackReference(this);

    if (backReferencingField.isPresent()) {
      BaseBackReferenceUpdater.updateBaseBackReferenceForClearBaseReference(
        backReferencingField.get());
    }
  }

  private void updatePropbableBackReferencingFieldOfEntityForClear(final E entity) {
    final var pendantReferencingField = getOptionalPendantReferencingFieldToEntity(entity);

    if (pendantReferencingField.isPresent()) {
      final var reference = (OptionalReference<?>) pendantReferencingField.get();
      reference.clear();
    }
  }

  private void updateStateForSetEntity(final E entity) {
    final var id = entity.getId();

    nullableReferencedEntityCache = new EntityCache<>(id, null, entity);
  }

  private void updateStateForClear() {
    nullableReferencedEntityCache = null;
  }
}
