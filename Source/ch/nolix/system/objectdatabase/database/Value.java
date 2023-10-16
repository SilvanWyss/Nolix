//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.system.objectdatabase.propertyhelper.ValueHelper;
import ch.nolix.system.objectdatabase.propertyvalidator.ValueValidator;
import ch.nolix.system.sqldatabaserawdata.databasedto.ContentFieldDto;
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IValue;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IValueHelper;
import ch.nolix.systemapi.objectdatabaseapi.propertyvalidatorapi.IValueValidator;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IContentFieldDto;

//class
public final class Value<V> extends BaseValue<V> implements IValue<V> {

  //constant
  private static final ValueCreator VALUE_CREATOR = new ValueCreator();

  //constant
  private static final IValueHelper VALUE_HELPER = new ValueHelper();

  //constant
  private static final IValueValidator VALUE_VALIDATOR = new ValueValidator();

  //static method
  public static <V2> Value<V2> withInitialValue(final V2 initialValue) {

    final var value = new Value<V2>();

    value.setValue(initialValue);

    return value;
  }

  //optional attribute
  private V internalValue;

  //method
  @Override
  public V getStoredValue() {

    VALUE_VALIDATOR.assertIsNotEmpty(this);

    return internalValue;
  }

  //method
  @Override
  public PropertyType getType() {
    return PropertyType.VALUE;
  }

  //method
  @Override
  public boolean isEmpty() {
    return (internalValue == null);
  }

  //method
  @Override
  public boolean isMandatory() {
    return true;
  }

  //method
  @Override
  public void setValue(final V value) {

    VALUE_VALIDATOR.assertCanSetGivenValue(this, value);

    updateStateForSetValue(value);

    setAsEditedAndRunProbableUpdateAction();
  }

  //method
  @Override
  public void setValueFromString(final String string) {

    @SuppressWarnings("unchecked")
    final var value = (V) VALUE_CREATOR.createValueOfDataTypeFromString(
        DataType.forType(VALUE_HELPER.getDataType(this)),
        string);

    setValue(value);
  }

  //method
  @Override
  public IContentFieldDto technicalToContentField() {
    return new ContentFieldDto(getName(), getStoredValue().toString());
  }

  //method
  @Override
  @SuppressWarnings("unchecked")
  void internalSetOrClearDirectlyFromContent(final Object content) {
    internalValue = (V) content;
  }

  //method
  private void updateStateForSetValue(final V value) {
    internalValue = value;
  }
}
