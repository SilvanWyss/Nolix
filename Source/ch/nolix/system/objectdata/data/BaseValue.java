//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseValue;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;

//class
public abstract class BaseValue<V> extends Field implements IBaseValue<V> {

  //attribute
  private final Class<V> valueType;

  //constructor
  protected BaseValue(final Class<V> valueType) {

    GlobalValidator.assertThat(valueType).thatIsNamed("value type").isNotNull();

    this.valueType = valueType;
  }

  //method
  @Override
  public final IContainer<IBaseBackReference<IEntity>> getStoredBaseBackReferences() {
    return new ImmutableList<>();
  }

  //method
  @Override
  public final IContainer<IField> getStoredReferencingFields() {
    return new ImmutableList<>();
  }

  //method
  @Override
  public final Class<V> getValueType() {
    return valueType;
  }

  //method
  @Override
  public final boolean referencesEntity(final IEntity entity) {
    return false;
  }

  //method
  @Override
  public final boolean referencesBackEntity(final IEntity entity) {
    return false;
  }

  //method
  @Override
  public boolean referencesBackField(final IField field) {
    return false;
  }

  //method
  @Override
  public final boolean referencesUninsertedEntity() {
    return false;
  }

  //method
  @Override
  final void internalUpdatePotentialBaseBackReferencesWhenIsInsertedIntoDatabase() {
    //Does nothing.
  }
}
