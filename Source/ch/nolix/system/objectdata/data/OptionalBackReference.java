//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.fieldvalidator.FieldValidator;
import ch.nolix.system.sqlrawdata.datadto.ContentFieldDto;
import ch.nolix.systemapi.fieldapi.mainapi.FieldType;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalBackReference;
import ch.nolix.systemapi.objectdataapi.fieldvalidatorapi.IFieldValidator;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IContentFieldDto;

//class
public final class OptionalBackReference<E extends IEntity> extends BaseBackReference<E>
implements IOptionalBackReference<E> {

  //constant
  private static final IFieldValidator PROPERTY_VALIDATOR = new FieldValidator();

  //optional attribute
  private String backReferencedEntityId;

  //constructor
  private OptionalBackReference(final String backReferencedTableName, final String backReferencedPropertyName) {
    super(backReferencedTableName, backReferencedPropertyName);
  }

  //static method
  public static <E2 extends Entity> OptionalBackReference<E2> forEntityAndBackReferencedPropertyName(
    final Class<E2> type,
    final String backReferencedPropertyName) {
    return new OptionalBackReference<>(type.getSimpleName(), backReferencedPropertyName);
  }

  //static method
  public static OptionalBackReference<BaseEntity> forEntityWithTableNameAndBackReferencedPropertyName(
    final String tableName,
    final String backReferencedPropertyName) {
    return new OptionalBackReference<>(tableName, backReferencedPropertyName);
  }

  //method
  @Override
  public String getBackReferencedEntityId() {

    PROPERTY_VALIDATOR.assertIsNotEmpty(this);

    return backReferencedEntityId;
  }

  //method
  @Override
  public E getBackReferencedEntity() {
    return getStoredBackReferencedTable().getStoredEntityById(getBackReferencedEntityId());
  }

  //method
  @Override
  public IContainer<IField> getStoredReferencingProperties() {

    if (isEmpty()) {
      return new ImmutableList<>();
    }

    return ImmutableList.withElement(
      getBackReferencedEntity().internalGetStoredProperties()
        .getStoredFirst(p -> p.hasName(getBackReferencedPropertyName())));
  }

  //method
  @Override
  public FieldType getType() {
    return FieldType.OPTIONAL_BACK_REFERENCE;
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
  public boolean isEmpty() {
    return (backReferencedEntityId == null);
  }

  //method
  @Override
  public boolean isMandatory() {
    return false;
  }

  //method
  @Override
  public boolean referencesBackEntity(IEntity entity) {
    return containsAny()
    && entity != null
    && getBackReferencedEntityId().equals(entity.getId());
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
