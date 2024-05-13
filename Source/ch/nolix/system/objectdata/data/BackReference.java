//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.fieldvalidator.FieldValidator;
import ch.nolix.system.sqlrawdata.datadto.ContentFieldDto;
import ch.nolix.systemapi.objectdataapi.dataapi.IBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectdataapi.fieldvalidatorapi.IFieldValidator;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IContentFieldDto;

//class
public final class BackReference<E extends IEntity> extends BaseBackReference<E>
implements IBackReference<E> {

  //constant
  private static final IFieldValidator FIELD_VALIDATOR = new FieldValidator();

  //optional attribute
  private String backReferencedEntityId;

  //constructor
  private BackReference(final String backReferencedTableName, final String backReferencedFieldName) {
    super(backReferencedTableName, backReferencedFieldName);
  }

  //static method
  public static <E2 extends Entity> BackReference<E2> forEntityAndBackReferencedFieldName(
    final Class<E2> type,
    final String backReferencedFieldName) {
    return new BackReference<>(type.getSimpleName(), backReferencedFieldName);
  }

  //static method
  public static BackReference<BaseEntity> forEntityWithTableNameAndBackReferencedFieldName(
    final String tableName,
    final String backReferencedFieldName) {
    return new BackReference<>(tableName, backReferencedFieldName);
  }

  //method
  @Override
  public IContainer<IField> getStoredReferencingFields() {

    if (isEmpty()) {
      return new ImmutableList<>();
    }

    return ImmutableList.withElement(
      getStoredBackReferencedEntity().internalGetStoredFields()
        .getStoredFirst(p -> p.hasName(getBackReferencedFieldName())));
  }

  //method
  @Override
  public ContentType getType() {
    return ContentType.BACK_REFERENCE;
  }

  //method
  @Override
  public String getBackReferencedEntityId() {

    FIELD_VALIDATOR.assertIsNotEmpty(this);

    return backReferencedEntityId;
  }

  //method
  @Override
  public E getStoredBackReferencedEntity() {
    return getStoredBackReferencedTable().getStoredEntityById(getBackReferencedEntityId());
  }

  //method
  @Override
  public boolean isEmpty() {
    return (backReferencedEntityId == null);
  }

  //method
  @Override
  public IContentFieldDto internalToContentField() {

    if (isEmpty()) {
      return new ContentFieldDto(getName());
    }

    return new ContentFieldDto(getName(), getBackReferencedEntityId());
  }

  //method
  @Override
  public boolean isMandatory() {
    return true;
  }

  //method
  @Override
  public boolean referencesBackEntity(final IEntity entity) {
    return containsAny()
    && entity != null
    && getBackReferencedEntityId().equals(entity.getId());
  }

  //method
  @Override
  public boolean referencesBackEntity() {
    return containsAny();
  }

  //method
  @Override
  protected boolean referencesBackEntityWithId(final String id) {
    return (containsAny() && getBackReferencedEntityId().equals(id));
  }

  //method
  void internalClear() {
    backReferencedEntityId = null;
    setAsEditedAndRunPotentialUpdateAction();
  }

  //method
  void internalSetDirectlyBackReferencedEntityId(final String backReferencedEntityId) {
    this.backReferencedEntityId = backReferencedEntityId;
  }

  //method
  @Override
  void internalSetOrClearFromContent(final Object content) {
    backReferencedEntityId = (String) content;
  }
}
