package ch.nolix.system.objectdata.data;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.fieldvalidator.FieldValidator;
import ch.nolix.systemapi.objectdataapi.dataapi.IBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectdataapi.fieldvalidatorapi.IFieldValidator;
import ch.nolix.systemapi.rawdataapi.datadto.ContentFieldWithContentAsStringDto;

public final class BackReference<E extends IEntity> extends BaseBackReference<E>
implements IBackReference<E> {

  private static final IFieldValidator FIELD_VALIDATOR = new FieldValidator();

  private String backReferencedEntityId;

  private BackReference(final String backReferencedTableName, final String backReferencedFieldName) {
    super(backReferencedTableName, backReferencedFieldName);
  }

  public static <E2 extends Entity> BackReference<E2> forEntityAndBackReferencedFieldName(
    final Class<E2> type,
    final String backReferencedFieldName) {
    return new BackReference<>(type.getSimpleName(), backReferencedFieldName);
  }

  public static BackReference<BaseEntity> forEntityWithTableNameAndBackReferencedFieldName(
    final String tableName,
    final String backReferencedFieldName) {
    return new BackReference<>(tableName, backReferencedFieldName);
  }

  @Override
  public IContainer<IField> getStoredReferencingFields() {

    if (isEmpty()) {
      return ImmutableList.createEmpty();
    }

    return ImmutableList.withElement(
      getStoredBackReferencedEntity().internalGetStoredFields()
        .getStoredFirst(p -> p.hasName(getBackReferencedFieldName())));
  }

  @Override
  public ContentType getType() {
    return ContentType.BACK_REFERENCE;
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
  public boolean isEmpty() {
    return (backReferencedEntityId == null);
  }

  @Override
  public ContentFieldWithContentAsStringDto internalToContentField() {

    if (isEmpty()) {
      return ContentFieldWithContentAsStringDto.withColumnName(getName());
    }

    return ContentFieldWithContentAsStringDto.withColumnNameAndContent(getName(), getBackReferencedEntityId());
  }

  @Override
  public boolean isMandatory() {
    return true;
  }

  @Override
  public boolean referencesBackEntity(final IEntity entity) {
    return containsAny()
    && entity != null
    && getBackReferencedEntityId().equals(entity.getId());
  }

  @Override
  public boolean referencesBackEntity() {
    return containsAny();
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

  @Override
  void internalSetOrClearFromContent(final Object content) {
    backReferencedEntityId = (String) content;
  }
}
