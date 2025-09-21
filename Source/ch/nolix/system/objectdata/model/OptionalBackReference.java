package ch.nolix.system.objectdata.model;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.system.objectdata.entitytool.TableNameExtractor;
import ch.nolix.system.objectdata.fieldvalidator.FieldValidator;
import ch.nolix.system.objectdata.modelsearcher.EntitySearcher;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectdata.entitytool.ITableNameExtractor;
import ch.nolix.systemapi.objectdata.fieldvalidator.IFieldValidator;
import ch.nolix.systemapi.objectdata.model.IBaseReference;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IOptionalBackReference;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.modelsearcher.IEntitySearcher;

public final class OptionalBackReference<E extends IEntity>
extends AbstractBaseBackReference<E>
implements IOptionalBackReference<E> {
  private static final ITableNameExtractor TABLE_NAME_EXTRACTOR = new TableNameExtractor();

  private static final IEntitySearcher ENTITY_SEARCHER = new EntitySearcher();

  private static final IFieldValidator FIELD_VALIDATOR = new FieldValidator();

  private String backReferencedEntityId;

  private OptionalBackReference(final IContainer<String> backReferenceableTableNamese,
    final String backReferencedFieldName) {
    super(backReferenceableTableNamese, backReferencedFieldName);
  }

  public static <E2 extends IEntity> OptionalBackReference<E2> forBackReferenceableEntityTypesAndBackReferencedFieldName(
    final IContainer<Class<? extends E2>> backReferenceableEntityTypes,
    final String backReferencedFieldName) {
    final var backReferenceableTableNames = //
    backReferenceableEntityTypes.getViewOf(TABLE_NAME_EXTRACTOR::getTableNameOfEntityType);

    return new OptionalBackReference<>(backReferenceableTableNames, backReferencedFieldName);
  }

  public static <E2 extends IEntity> OptionalBackReference<E2> forBackReferenceableTableNamesAndBackReferencedFieldName(
    final IContainer<String> backReferenceableTableNames,
    final String backReferencedFieldName) {
    return new OptionalBackReference<>(backReferenceableTableNames, backReferencedFieldName);
  }

  public static <E2 extends IEntity> OptionalBackReference<E2> forBackReferenceableTablesAndBackReferencedFieldName(
    final IContainer<ITable<IEntity>> backReferenceableTables,
    final String backReferencedFieldName) {
    final var backReferenceableTableNames = backReferenceableTables.getViewOf(ITable::getName);

    return new OptionalBackReference<>(backReferenceableTableNames, backReferencedFieldName);
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
  public IContainer<IBaseReference> getStoredBaseReferencesWhoAreBackReferencedFromThis() {
    if (isEmpty()) {
      return ImmutableList.createEmpty();
    }

    final var backReferencedField = //
    (IBaseReference) ENTITY_SEARCHER.getStoredFieldByName(getStoredBackReferencedEntity(),
      getBackReferencedFieldName());

    return ImmutableList.withElement(backReferencedField);
  }

  @Override
  public FieldType getType() {
    return FieldType.OPTIONAL_BACK_REFERENCE;
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
    return false;
  }

  @Override
  public boolean referencesBackEntity(IEntity entity) {
    return containsAny()
    && entity != null
    && getBackReferencedEntityId().equals(entity.getId());
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
