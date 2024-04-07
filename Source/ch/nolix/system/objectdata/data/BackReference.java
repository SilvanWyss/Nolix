//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.propertyvalidator.PropertyValidator;
import ch.nolix.system.sqlrawdata.datadto.ContentFieldDto;
import ch.nolix.systemapi.fieldapi.mainapi.FieldType;
import ch.nolix.systemapi.objectdataapi.dataapi.IBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;
import ch.nolix.systemapi.objectdataapi.propertyvalidatorapi.IPropertyValidator;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IContentFieldDto;

//class
public final class BackReference<E extends IEntity> extends BaseBackReference<E>
implements IBackReference<E> {

  //constant
  private static final IPropertyValidator PROPERTY_VALIDATOR = new PropertyValidator();

  //optional attribute
  private String backReferencedEntityId;

  //constructor
  private BackReference(final String backReferencedTableName, final String backReferencedPropertyName) {
    super(backReferencedTableName, backReferencedPropertyName);
  }

  //static method
  public static <E2 extends Entity> BackReference<E2> forEntityAndBackReferencedPropertyName(
    final Class<E2> type,
    final String backReferencedPropertyName) {
    return new BackReference<>(type.getSimpleName(), backReferencedPropertyName);
  }

  //static method
  public static BackReference<BaseEntity> forEntityWithTableNameAndBackReferencedPropertyName(
    final String tableName,
    final String backReferencedPropertyName) {
    return new BackReference<>(tableName, backReferencedPropertyName);
  }

  //method
  @Override
  public IContainer<IField> getStoredReferencingProperties() {

    if (isEmpty()) {
      return new ImmutableList<>();
    }

    return ImmutableList.withElement(
      getStoredBackReferencedEntity().internalGetStoredProperties()
        .getStoredFirst(p -> p.hasName(getBackReferencedPropertyName())));
  }

  //method
  @Override
  public FieldType getType() {
    return FieldType.BACK_REFERENCE;
  }

  //method
  @Override
  public String getBackReferencedEntityId() {

    PROPERTY_VALIDATOR.assertIsNotEmpty(this);

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
