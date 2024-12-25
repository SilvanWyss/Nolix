package ch.nolix.system.objectdata.data;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.fieldvalidator.FieldValidator;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;
import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalBackReference;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectdataapi.fieldvalidatorapi.IFieldValidator;

public final class OptionalBackReference<E extends IEntity> extends AbstractBackReference<E>
implements IOptionalBackReference<E> {

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

  public static OptionalBackReference<BaseEntity> forEntityWithTableNameAndBackReferencedFieldName(
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
  public E getBackReferencedEntity() {
    return getStoredBackReferencedTable().getStoredEntityById(getBackReferencedEntityId());
  }

  @Override
  public IContainer<IField> getStoredReferencingFields() {

    if (isEmpty()) {
      return ImmutableList.createEmpty();
    }

    return ImmutableList.withElement(
      getBackReferencedEntity().internalGetStoredFields()
        .getStoredFirst(p -> p.hasName(getBackReferencedFieldName())));
  }

  @Override
  public ContentType getType() {
    return ContentType.OPTIONAL_BACK_REFERENCE;
  }

  @Override
  public void internalSetOrClearContent(final Object content) {
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
  protected boolean referencesBackEntityWithId(final String id) {
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
