package ch.nolix.system.objectdata.model;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.system.objectdata.entitytool.TableNameExtractor;
import ch.nolix.system.objectdata.fieldvalidator.FieldValidator;
import ch.nolix.system.objectdata.modelsearcher.EntitySearcher;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectdata.entitytool.ITableNameExtractor;
import ch.nolix.systemapi.objectdata.fieldvalidator.IFieldValidator;
import ch.nolix.systemapi.objectdata.model.IBackReference;
import ch.nolix.systemapi.objectdata.model.IBaseReference;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.modelsearcher.IEntitySearcher;

public final class BackReference<E extends IEntity> extends AbstractBaseBackReference<E> implements IBackReference<E> {
  private static final ITableNameExtractor TABLE_NAME_EXTRACTOR = new TableNameExtractor();

  private static final IEntitySearcher ENTITY_SEARCHER = new EntitySearcher();

  private static final IFieldValidator FIELD_VALIDATOR = new FieldValidator();

  private String backReferencedEntityId;

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

    return backReferencedEntityId;
  }

  @Override
  public E getStoredBackReferencedEntity() {
    return getStoredBackReferencedTable().getStoredEntityById(getBackReferencedEntityId());
  }

  @Override
  public void internalSetNullableValue(final Object nullableValue, final String nullableAdditionalValue) {
    backReferencedEntityId = (String) nullableValue;
  }

  @Override
  public boolean isEmpty() {
    return (backReferencedEntityId == null);
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
    return (containsAny() && getBackReferencedEntityId().equals(id));
  }

  void internalClear() {
    backReferencedEntityId = null;
    setAsEditedAndRunPotentialUpdateAction();
  }

  void internalSetDirectlyBackReferencedEntityId(final String backReferencedEntityId) {
    this.backReferencedEntityId = backReferencedEntityId;
  }
}
