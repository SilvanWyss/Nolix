package ch.nolix.system.objectdata.model;

import java.util.Optional;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.system.objectdata.entitytool.TableNameExtractor;
import ch.nolix.system.objectdata.fieldexaminer.FieldExaminer;
import ch.nolix.system.objectdata.fieldvalidator.ReferenceValidator;
import ch.nolix.system.objectdata.modelsearcher.DatabaseSearcher;
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
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.modelsearcher.IDatabaseSearcher;
import ch.nolix.systemapi.objectdata.modelsearcher.IEntitySearcher;
import ch.nolix.systemapi.objectdata.structure.EntityCache;

public final class Reference<E extends IEntity> extends AbstractBaseReference<E> implements IReference<E> {
  private static final IDatabaseSearcher DATABASE_SEARCHER = new DatabaseSearcher();

  private static final ITableNameExtractor TABLE_NAME_EXTRACTOR = new TableNameExtractor();

  private static final IEntitySearcher ENTITY_SEARCHER = new EntitySearcher();

  private static final IFieldExaminer FIELD_EXAMINER = new FieldExaminer();

  private static final IReferenceValidator REFERENCE_VALIDATOR = new ReferenceValidator();

  private EntityCache<E> nullableReferencedEntityCache;

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

    return nullableReferencedEntityCache.id();
  }

  @Override
  public String getReferencedTableId() {
    retrieveReferencedTableId();

    return nullableReferencedEntityCache.nullableTableId();
  }

  @Override
  public String getReferencedTableName() {
    REFERENCE_VALIDATOR.assertIsNotEmpty(this);

    final var referencedEntity = nullableReferencedEntityCache.nullableEntity();

    if (referencedEntity != null) {
      return referencedEntity.getParentTableName();
    }

    return getStoredReferencedTable().getName();
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
    return FieldType.REFERENCE;
  }

  //For a better performance, this implementation does not use all available comfort methods.
  @Override
  public void internalSetNullableValue(final Object nullableValue, final String nullableAdditionalValue) {
    final var id = (String) nullableValue;

    if (id == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalog.ID);
    }

    final var tableId = nullableAdditionalValue;

    if (tableId == null) {
      throw ArgumentIsNullException.forArgumentName("table id");
    }

    nullableReferencedEntityCache = new EntityCache<>(id, tableId, null);
  }

  @Override
  public boolean isEmpty() {
    return (nullableReferencedEntityCache == null);
  }

  @Override
  public boolean isMandatory() {
    return true;
  }

  @Override
  public boolean referencesEntity(final IEntity entity) {
    return //
    containsAny()
    && entity != null
    && getReferencedEntityId().equals(entity.getId());
  }

  @Override
  public boolean referencesUninsertedEntity() {
    return //
    containsAny()
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
      final var id = nullableReferencedEntityCache.id();
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

  private void retrieveReferencedEntity() {
    REFERENCE_VALIDATOR.assertIsNotEmpty(this);

    var referencedEntity = nullableReferencedEntityCache.nullableEntity();

    if (referencedEntity == null) {
      final var referencedEntityId = nullableReferencedEntityCache.id();
      final var referencedTableId = nullableReferencedEntityCache.nullableTableId();
      referencedEntity = getStoredReferencedTable().getStoredEntityById(referencedEntityId);

      nullableReferencedEntityCache = new EntityCache<>(referencedEntityId, referencedTableId, referencedEntity);
    }
  }

  private void retrieveReferencedTableId() {
    REFERENCE_VALIDATOR.assertIsNotEmpty(this);

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
    REFERENCE_VALIDATOR.assertCanSetEntity(this, entity);

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
    nullableReferencedEntityCache = null;
  }

  private void updateStateForSetEntity(final E entity) {
    final var id = entity.getId();

    nullableReferencedEntityCache = new EntityCache<>(id, null, entity);
  }
}
