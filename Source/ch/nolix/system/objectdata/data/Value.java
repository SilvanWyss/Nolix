//package declaration
package ch.nolix.system.objectdata.data;

import ch.nolix.system.objectdata.propertytool.ValueTool;
import ch.nolix.system.objectdata.propertyvalidator.ValueValidator;
import ch.nolix.system.sqlrawdata.datadto.ContentFieldDto;
import ch.nolix.systemapi.entitypropertyapi.datatypeapi.DataType;
import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;
import ch.nolix.systemapi.objectdataapi.dataapi.IValue;
import ch.nolix.systemapi.objectdataapi.propertytoolapi.IValueTool;
import ch.nolix.systemapi.objectdataapi.propertyvalidatorapi.IValueValidator;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IContentFieldDto;

//class
public final class Value<V> extends BaseValue<V> implements IValue<V> {

  //constant
  private static final ValueCreator VALUE_CREATOR = new ValueCreator();

  //constant
  private static final IValueTool VALUE_TOOL = new ValueTool();

  //constant
  private static final IValueValidator VALUE_VALIDATOR = new ValueValidator();

  //optional attribute
  private V internalValue;

  //static method
  public static <V2> Value<V2> withInitialValue(final V2 initialValue) {

    final var value = new Value<V2>();

    value.setValue(initialValue);

    return value;
  }

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
      DataType.forType(VALUE_TOOL.getDataType(this)),
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
