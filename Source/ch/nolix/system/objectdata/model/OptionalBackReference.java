package ch.nolix.system.objectdata.model;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.fieldvalidator.FieldValidator;
import ch.nolix.system.objectdata.modelsearcher.EntitySearcher;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectdataapi.fieldvalidatorapi.IFieldValidator;
import ch.nolix.systemapi.objectdataapi.modelapi.IAbstractReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IField;
import ch.nolix.systemapi.objectdataapi.modelapi.IOptionalBackReference;
import ch.nolix.systemapi.objectdataapi.modelsearcherapi.IEntitySearcher;

public final class OptionalBackReference<E extends IEntity> extends AbstractBackReference<E>
implements IOptionalBackReference<E> {

  private static final IEntitySearcher ENTITY_SEARCHER = new EntitySearcher();

  private static final IFieldValidator FIELD_VALIDATOR = new FieldValidator();

  private String backReferencedEntityId;

  private OptionalBackReference(final String backReferencedTableName, final String backReferencedFieldName) {
    super(backReferencedTableName, backReferencedFieldName);
  }

  public static <E2 extends Entity> OptionalBackReference<E2> forEntityAndBackReferencedFieldName(
    final Class<E2> type,
    final String backReferencedFieldName) {
    return new OptionalBackReference<>(type.getSimpleName(), backReferencedFieldName);
  }

  public static OptionalBackReference<AbstractEntity> forEntityWithTableNameAndBackReferencedFieldName(
    final String tableName,
    final String backReferencedFieldName) {
    return new OptionalBackReference<>(tableName, backReferencedFieldName);
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
  @SuppressWarnings("unchecked")
  public IContainer<IAbstractReference<IEntity>> getStoredAbstractReferencesThatAreBackReferencedFromThis() {

    if (isEmpty()) {
      return ImmutableList.createEmpty();
    }

    final var backReferencedField = //
    (IAbstractReference<IEntity>) ENTITY_SEARCHER.getStoredFieldByName(getStoredBackReferencedEntity(),
      getBackReferencedFieldName());

    return ImmutableList.withElement(backReferencedField);
  }

  @Override
  public ContentType getType() {
    return ContentType.OPTIONAL_BACK_REFERENCE;
  }

  @Override
  public IContainer<IField> internalGetStoredSubFields() {
    return ImmutableList.createEmpty();
  }

  @Override
  public void internalSetOptionalContent(final Object content) {
    backReferencedEntityId = (String) content;
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
