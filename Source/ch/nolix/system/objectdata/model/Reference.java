/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.model;

import java.util.Optional;

import ch.nolix.base.datastructure.extendediterableview.ExtendedIterableView;
import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.system.objectdata.entitytool.TableNameExtractor;
import ch.nolix.system.objectdata.fieldexaminer.FieldExaminer;
import ch.nolix.system.objectdata.fieldvalidator.ReferenceValidator;
import ch.nolix.system.objectdata.modelsearcher.DatabaseSearcher;
import ch.nolix.system.objectdata.modelsearcher.EntitySearcher;
import ch.nolix.systemapi.database.databaseobject.DatabaseObjectState;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectdata.model.BaseBackReference;
import ch.nolix.systemapi.objectdata.model.Field;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IReference;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.structure.EntityCache;

/**
 * @author Silvan Wyss
 * @param <E> the type of the {@link IEntity} a {@link Reference} references
 */
public final class Reference<E extends IEntity> extends AbstractBaseReference<E> implements IReference<E> {
  private static final DatabaseSearcher DATABASE_SEARCHER = new DatabaseSearcher();

  private static final TableNameExtractor TABLE_NAME_EXTRACTOR = new TableNameExtractor();

  private static final EntitySearcher ENTITY_SEARCHER = new EntitySearcher();

  private static final FieldExaminer FIELD_EXAMINER = new FieldExaminer();

  private static final ReferenceValidator REFERENCE_VALIDATOR = new ReferenceValidator();

  private EntityCache<E> nullableReferencedEntityCache;

  private Reference(final ExtendedIterable<String> referenceableTableNames) {
    super(referenceableTableNames);
  }

  @SafeVarargs
  public static <T extends IEntity> Reference<T> forEntityTypes(
    final Class<? extends T>... entityTypes) {
    final var entityTypesView = ExtendedIterableView.forArray(entityTypes);
    final var referenceableTableNamesView = entityTypesView.getViewOf(TABLE_NAME_EXTRACTOR::getTableNameOfEntityType);

    return new Reference<>(referenceableTableNamesView);
  }

  public static <T extends IEntity> Reference<T> forEntityTypes(
    final ExtendedIterable<Class<? extends T>> entityTypes) {
    final var referenceableTableNamesView = entityTypes.getViewOf(TABLE_NAME_EXTRACTOR::getTableNameOfEntityType);

    return new Reference<>(referenceableTableNamesView);
  }

  public static <T extends IEntity> Reference<T> forReferenceableTableNames(
    final ExtendedIterable<String> referenceableTableNames) {
    return new Reference<>(referenceableTableNames);
  }

  public static <T extends IEntity> Reference<T> forReferenceableTableNames(final String... referenceableTableNames) {
    final var referenceableTableNamesView = ExtendedIterableView.forArray(referenceableTableNames);

    return new Reference<>(referenceableTableNamesView);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getReferencedEntityId() {
    REFERENCE_VALIDATOR.assertIsNotEmpty(this);

    return nullableReferencedEntityCache.entityId();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getReferencedTableId() {
    retrieveReferencedTableId();

    return nullableReferencedEntityCache.nullableTableId();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getReferencedTableName() {
    REFERENCE_VALIDATOR.assertIsNotEmpty(this);

    final var referencedEntity = nullableReferencedEntityCache.nullableEntity();

    if (referencedEntity != null) {
      return referencedEntity.getParentTableName();
    }

    return getStoredReferencedTable().getName();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<BaseBackReference> getStoredBaseBackReferencesWhoReferencesBackThis() {
    if (isEmpty()) {
      return ImmutableList.createEmpty();
    }

    final var fields = getStoredReferencedEntity().internalGetStoredFields();
    final var abstractBackReferenceContainer = fields.getOptionalStoredFirst(f -> f.referencesBackField(this));

    if (abstractBackReferenceContainer.isPresent()) {
      final var abstractBackReference = (BaseBackReference) abstractBackReferenceContainer.get();

      return ImmutableList.withElements(abstractBackReference);
    }

    return ImmutableList.createEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E getStoredReferencedEntity() {
    retrieveReferencedEntity();

    return nullableReferencedEntityCache.nullableEntity();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public ITable<E> getStoredReferencedTable() {
    // This part is not mandatory, but provides a better performance.
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

  /**
   * {@inheritDoc}
   */
  @Override
  public FieldType getType() {
    return FieldType.REFERENCE;
  }

  // For a better performance, this implementation does not use all available comfort methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public void internalSetNullableValue(final Object nullableValue, final String nullableAdditionalValue) {
    nullableReferencedEntityCache = //
    ReferenceHelper.createEntityCacheFromIdAndTableId(nullableValue, nullableAdditionalValue);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEmpty() {
    return (nullableReferencedEntityCache == null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMandatory() {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean referencesEntity(final IEntity entity) {
    return //
    containsAny()
    && entity != null
    && getReferencedEntityId().equals(entity.getId());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean referencesUninsertedEntity() {
    return //
    containsAny()
    && !getStoredReferencedEntity().belongsToTable();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public void setEntity(final Object entity) {
    setCastedEntity((E) entity);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEntityById(final String id) {
    final var entity = getStoredReferencedTable().getStoredEntityById(id);

    setEntity(entity);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteInsertIntoDatabase() {
    if (containsAny()) {
      final var id = nullableReferencedEntityCache.entityId();
      final var entity = nullableReferencedEntityCache.nullableEntity();
      final var tableName = entity.getParentTableName();

      final var tableId = //
      getStoredParentEntity()
        .getStoredParentDatabase()
        .getStoredTableByName(tableName)
        .getId();

      nullableReferencedEntityCache = new EntityCache<>(id, tableId, entity);
      updateProbableBackReferenceForSetOrAddedEntity(getStoredReferencedEntity());
    }
  }

  private void clear() {
    if (containsAny()) {
      clearWhenContainsAny();
    }
  }

  private void clearWhenContainsAny() {
    updateProbableBackReferencingFieldForClear();

    updateStateForClear();

    setAsEditedAndRunPossibleUpdateAction();
  }

  private Optional<? extends Field> getOptionalPendantReferencingFieldToEntity(final E entity) {
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

  private void retrieveReferencedEntity() {
    REFERENCE_VALIDATOR.assertIsNotEmpty(this);

    var referencedEntity = nullableReferencedEntityCache.nullableEntity();

    if (referencedEntity == null) {
      final var referencedEntityId = nullableReferencedEntityCache.entityId();
      final var referencedTableId = nullableReferencedEntityCache.nullableTableId();
      referencedEntity = getStoredReferencedTable().getStoredEntityById(referencedEntityId);

      nullableReferencedEntityCache = new EntityCache<>(referencedEntityId, referencedTableId, referencedEntity);
    }
  }

  private void retrieveReferencedTableId() {
    REFERENCE_VALIDATOR.assertIsNotEmpty(this);

    var referencedTableId = nullableReferencedEntityCache.nullableTableId();

    if (referencedTableId == null) {
      final var referencedEntityId = nullableReferencedEntityCache.entityId();
      final var referencedEntity = nullableReferencedEntityCache.nullableEntity();
      final var referencedTable = referencedEntity.getStoredParentTable();
      referencedTableId = referencedTable.getId();

      nullableReferencedEntityCache = new EntityCache<>(referencedEntityId, referencedTableId, referencedEntity);
    }
  }

  private void setCastedEntity(final E entity) {
    REFERENCE_VALIDATOR.assertCanSetEntity(this, entity);

    updatePropableBackReferencingFieldOfEntityForClear(entity);

    clear();

    updateStateForSetEntity(entity);

    updatePotentialBaseBackReferenceOfEntityForSetEntity(entity);

    insertEntityIntoDatabaseIfPossible(entity);

    setAsEditedAndRunPossibleUpdateAction();
  }

  private void updatePotentialBaseBackReferenceOfEntityForSetEntity(final E entity) {
    BaseReferenceUpdater.ofBaseReferenceUpdatePotentialBaseBackReferenceForAddOrSetEntity(this, entity);
  }

  private void updateProbableBackReferencingFieldForClear() {
    final var backReferencedEntityId = getStoredParentEntity().getId();

    for (final var b : getStoredBaseBackReferencesWhoReferencesBackThis()) {
      BaseBackReferenceUpdater.updateBaseBackReferenceForClearBaseReference(b, backReferencedEntityId);
    }
  }

  private void updatePropableBackReferencingFieldOfEntityForClear(final E entity) {
    for (final var b : getStoredBaseBackReferencesWhoReferencesBackThis()) {
      if (FIELD_EXAMINER.isForSingleContent(b)) {
        final var pendantReferencingField = getOptionalPendantReferencingFieldToEntity(entity);

        if (pendantReferencingField.isPresent()) {
          final var reference = (Reference<?>) pendantReferencingField.get();
          reference.clear();
        }
      }
    }
  }

  private void updateStateForClear() {
    nullableReferencedEntityCache = null;
  }

  private void updateStateForSetEntity(final E entity) {
    final var id = entity.getId();

    nullableReferencedEntityCache = new EntityCache<>(id, null, entity);
  }
}
