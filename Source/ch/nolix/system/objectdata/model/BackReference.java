/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.model;

import ch.nolix.core.container.containerview.ContainerView;
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

/**
 * @author Silvan Wyss
 * @param <E> is the type of the {@link IEntity} a {@link BackReference}
 *            reference back.
 */
public final class BackReference<E extends IEntity> extends AbstractBaseBackReference implements IBackReference<E> {
  private static final IDatabaseSearcher DATABASE_SEARCHER = new DatabaseSearcher();

  private static final ITableNameExtractor TABLE_NAME_EXTRACTOR = new TableNameExtractor();

  private static final IEntitySearcher ENTITY_SEARCHER = new EntitySearcher();

  private static final IFieldValidator FIELD_VALIDATOR = new FieldValidator();

  private EntityCache<E> nullableBackReferencedEntityCache;

  private BackReference(final IContainer<String> backReferenceableTableNames, final String backReferencedFieldName) {
    super(backReferenceableTableNames, backReferencedFieldName);
  }

  @SafeVarargs
  public static <T extends IEntity> BackReference<T> forBackReferencedFieldNameAndBackReferenceableEntityTypes(
    final String backReferencedFieldName,
    final Class<T>... backReferenceableEntityTypes) {
    final var backReferenceableEntityTypesContainerView = ContainerView.forArray(backReferenceableEntityTypes);
    final var backReferenceableTableNamesView = //
    backReferenceableEntityTypesContainerView.getViewOf(TABLE_NAME_EXTRACTOR::getTableNameOfEntityType);

    return new BackReference<>(backReferenceableTableNamesView, backReferencedFieldName);
  }

  public static <T extends IEntity> BackReference<T> forBackReferencedFieldNameAndBackReferenceableEntityTypes(
    final String backReferencedFieldName,
    final IContainer<Class<? extends T>> backReferenceableEntityTypes) {
    final var backReferenceableTableNamesView = //
    backReferenceableEntityTypes.getViewOf(TABLE_NAME_EXTRACTOR::getTableNameOfEntityType);

    return new BackReference<>(backReferenceableTableNamesView, backReferencedFieldName);
  }

  public static <T extends IEntity> BackReference<T> forBackReferencedFieldNameAndBackReferenceableTableNames(
    final String backReferencedFieldName,
    final IContainer<String> backReferenceableTableNames) {
    return new BackReference<>(backReferenceableTableNames, backReferencedFieldName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<IBaseReference> getStoredBackReferencedBaseReferences() {
    if (isEmpty()) {
      return ImmutableList.createEmpty();
    }

    final var backReferencedField = //
    (IBaseReference) //
    ENTITY_SEARCHER.getStoredFieldByName(getStoredBackReferencedEntity(), getBackReferencedFieldName());

    return ImmutableList.withElements(backReferencedField);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public FieldType getType() {
    return FieldType.BACK_REFERENCE;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getBackReferencedEntityId() {
    FIELD_VALIDATOR.assertIsNotEmpty(this);

    return nullableBackReferencedEntityCache.entityId();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getBackReferencedTableId() {
    retrieveBackReferencedTableId();

    return nullableBackReferencedEntityCache.nullableTableId();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getBackReferencedTableName() {
    FIELD_VALIDATOR.assertIsNotEmpty(this);

    final var backReferencedEntity = nullableBackReferencedEntityCache.nullableEntity();

    if (backReferencedEntity != null) {
      return backReferencedEntity.getParentTableName();
    }

    return getStoredBackReferencedTable().getName();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E getStoredBackReferencedEntity() {
    return getStoredBackReferencedTable().getStoredEntityById(getBackReferencedEntityId());
  }

  /**
   * {@inheritDoc}
   */
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

  /**
   * {@inheritDoc}
   */
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

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEmpty() {
    return (nullableBackReferencedEntityCache == null);
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
  public boolean referencesBackEntity(final IEntity entity) {
    return //
    containsAny()
    && entity != null
    && getBackReferencedEntityId().equals(entity.getId());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean referencesBackEntity() {
    return containsAny();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean referencesBackEntityWithId(final String id) {
    return //
    containsAny()
    && getBackReferencedEntityId().equals(id);
  }

  /**
   * {@inheritDoc}
   */
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
    setAsEditedAndRunPossibleUpdateAction();
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
    setAsEditedAndRunPossibleUpdateAction();
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
