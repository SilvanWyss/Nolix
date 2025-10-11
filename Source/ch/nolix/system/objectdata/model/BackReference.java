package ch.nolix.system.objectdata.model;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.system.objectdata.entitytool.TableNameExtractor;
import ch.nolix.system.objectdata.fieldvalidator.FieldValidator;
import ch.nolix.system.objectdata.modelsearcher.DatabaseSearcher;
import ch.nolix.system.objectdata.modelsearcher.EntitySearcher;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectdata.entitytool.ITableNameExtractor;
import ch.nolix.systemapi.objectdata.fieldvalidator.IFieldValidator;
import ch.nolix.systemapi.objectdata.model.IBackReference;
import ch.nolix.systemapi.objectdata.model.IBaseReference;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IField;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.modelsearcher.IDatabaseSearcher;
import ch.nolix.systemapi.objectdata.modelsearcher.IEntitySearcher;
import ch.nolix.systemapi.objectdata.structure.EntityCache;

public final class BackReference<E extends IEntity> extends AbstractBaseBackReference<E> implements IBackReference<E> {
  private static final IDatabaseSearcher DATABASE_SEARCHER = new DatabaseSearcher();

  private static final ITableNameExtractor TABLE_NAME_EXTRACTOR = new TableNameExtractor();

  private static final IEntitySearcher ENTITY_SEARCHER = new EntitySearcher();

  private static final IFieldValidator FIELD_VALIDATOR = new FieldValidator();

  private EntityCache<E> nullableBackReferencedEntityCache;

  private BackReference(final IContainer<String> backReferenceableTableNames, final String backReferencedFieldName) {
    super(backReferenceableTableNames, backReferencedFieldName);
  }

  public static <E2 extends IEntity> BackReference<E2> forBackReferenceableEntityTypesAndBackReferencedFieldName(
    final IContainer<Class<? extends E2>> backReferenceableEntityTypes,
    final String backReferencedFieldName) {
    final var backReferenceableTableNames = //
    backReferenceableEntityTypes.getViewOf(TABLE_NAME_EXTRACTOR::getTableNameOfEntityType);

    return new BackReference<>(backReferenceableTableNames, backReferencedFieldName);
  }

  public static <E2 extends IEntity> BackReference<E2> forBackReferenceableTableNamesAndBackReferencedFieldName(
    final IContainer<String> backReferenceableTableNames,
    final String backReferencedFieldName) {
    return new BackReference<>(backReferenceableTableNames, backReferencedFieldName);
  }

  public static <E2 extends IEntity> BackReference<E2> forBackReferenceableTablesAndBackReferencedFieldName(
    final IContainer<ITable<IEntity>> backReferenceableTables,
    final String backReferencedFieldName) {
    final var backReferenceableTableNames = backReferenceableTables.getViewOf(ITable::getName);

    return new BackReference<>(backReferenceableTableNames, backReferencedFieldName);
  }

  @Override
  public IContainer<IBaseReference> getStoredBaseReferencesWhoAreBackReferencedFromThis() {
    if (isEmpty()) {
      return ImmutableList.createEmpty();
    }

    final var backReferencedField = //
    (IBaseReference) //
    ENTITY_SEARCHER.getStoredFieldByName(getStoredBackReferencedEntity(), getBackReferencedFieldName());

    return ImmutableList.withElement(backReferencedField);
  }

  @Override
  public FieldType getType() {
    return FieldType.BACK_REFERENCE;
  }

  @Override
  public String getBackReferencedEntityId() {
    FIELD_VALIDATOR.assertIsNotEmpty(this);

    return nullableBackReferencedEntityCache.entityId();
  }

  @Override
  public String getBackReferencedTableId() {
    retrieveBackReferencedTableId();

    return nullableBackReferencedEntityCache.nullableTableId();
  }

  @Override
  public String getBackReferencedTableName() {
    FIELD_VALIDATOR.assertIsNotEmpty(this);

    final var backReferencedEntity = nullableBackReferencedEntityCache.nullableEntity();

    if (backReferencedEntity != null) {
      return backReferencedEntity.getParentTableName();
    }

    return getStoredBackReferencedTable().getName();
  }

  @Override
  public E getStoredBackReferencedEntity() {
    return getStoredBackReferencedTable().getStoredEntityById(getBackReferencedEntityId());
  }

  @Override
  @SuppressWarnings("unchecked")
  public ITable<E> getStoredBackReferencedTable() {
    //This part is not mandatory, but provides a better performance.
    final var backReferencedEntity = nullableBackReferencedEntityCache.nullableEntity();
    if (backReferencedEntity != null && backReferencedEntity.belongsToTable()) {
      return (ITable<E>) backReferencedEntity.getStoredParentTable();
    }

    if (belongsToDatabase()) {
      return (ITable<E>) DATABASE_SEARCHER.getStoredTableById(getStoredParentDatabase(), getBackReferencedTableId());
    }

    final var database = nullableBackReferencedEntityCache.nullableEntity().getStoredParentDatabase();

    return (ITable<E>) DATABASE_SEARCHER.getStoredTableById(database, getBackReferencedTableId());
  }

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

    nullableBackReferencedEntityCache = new EntityCache<>(id, tableId, null);
  }

  @Override
  public boolean isEmpty() {
    return (nullableBackReferencedEntityCache == null);
  }

  @Override
  public boolean isMandatory() {
    return true;
  }

  @Override
  public boolean referencesBackEntity(final IEntity entity) {
    return //
    containsAny()
    && entity != null
    && getBackReferencedEntityId().equals(entity.getId());
  }

  @Override
  public boolean referencesBackEntity() {
    return containsAny();
  }

  @Override
  public boolean referencesBackEntityWithId(final String id) {
    return //
    containsAny()
    && getBackReferencedEntityId().equals(id);
  }

  @Override
  public boolean referencesBackField(final IField field) {
    return //
    field != null
    && field.belongsToEntity()
    && containsAny()
    && getBackReferencedTableName().equals(field.getStoredParentEntity().getParentTableName())
    && getBackReferencedFieldName().equals(field.getName())
    && getBackReferencedEntityId().equals(field.getStoredParentEntity().getId());
  }

  void clear() {
    nullableBackReferencedEntityCache = null;
    setAsEditedAndRunPotentialUpdateAction();
  }

  @SuppressWarnings("unchecked")
  void setBackReferencedEntityOnly(final IEntity entity) {
    final var entityId = entity.getId();
    final var castedEntity = (E) entity;
    if (entity.belongsToTable()) {
      final var table = entity.getStoredParentTable();
      final var tableId = table.getId();
      nullableBackReferencedEntityCache = new EntityCache<>(entityId, tableId, castedEntity);
    } else {
      nullableBackReferencedEntityCache = new EntityCache<>(entityId, null, castedEntity);
    }
    setAsEditedAndRunPotentialUpdateAction();
  }

  private void retrieveBackReferencedTableId() {
    FIELD_VALIDATOR.assertIsNotEmpty(this);

    var backReferencedTableId = nullableBackReferencedEntityCache.nullableTableId();

    if (backReferencedTableId == null) {
      final var backReferencedEntityId = nullableBackReferencedEntityCache.entityId();
      final var backReferencedEntity = nullableBackReferencedEntityCache.nullableEntity();
      final var backReferencedTable = backReferencedEntity.getStoredParentTable();
      backReferencedTableId = backReferencedTable.getId();

      nullableBackReferencedEntityCache = //
      new EntityCache<>(backReferencedEntityId, backReferencedTableId, backReferencedEntity);
    }
  }
}
