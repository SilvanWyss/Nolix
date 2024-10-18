package ch.nolix.system.objectdata.data;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.system.objectdata.fieldtool.ValueTool;
import ch.nolix.system.objectdata.fieldvalidator.ValueValidator;
import ch.nolix.system.sqlrawdata.datadto.ContentFieldDto;
import ch.nolix.systemapi.objectdataapi.dataapi.IValue;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IValueTool;
import ch.nolix.systemapi.objectdataapi.fieldvalidatorapi.IValueValidator;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IContentFieldDto;

public final class Value<V> extends BaseValue<V> implements IValue<V> {

  private static final ValueCreator VALUE_CREATOR = new ValueCreator();

  private static final IValueTool VALUE_TOOL = new ValueTool();

  private static final IValueValidator VALUE_VALIDATOR = new ValueValidator();

  private V internalValue;

  private Value(final Class<V> valueType) {
    super(valueType);
  }

  public static <V2> Value<V2> withInitialValue(final V2 initialValue) {

    @SuppressWarnings("unchecked")
    final var value = (Value<V2>) Value.withValueType(initialValue.getClass());

    value.setValue(initialValue);

    return value;
  }

  public static <V2> Value<V2> withValueType(final Class<V2> valueType) {
    return new Value<>(valueType);
  }

  @Override
  public V getStoredValue() {

    VALUE_VALIDATOR.assertIsNotEmpty(this);

    return internalValue;
  }

  @Override
  public ContentType getType() {
    return ContentType.VALUE;
  }

  @Override
  public boolean isEmpty() {
    return (internalValue == null);
  }

  @Override
  public boolean isMandatory() {
    return true;
  }

  @Override
  public void setValue(final V value) {

    VALUE_VALIDATOR.assertCanSetGivenValue(this, value);

    updateStateForSetValue(value);

    setAsEditedAndRunPotentialUpdateAction();
  }

  @Override
  public void setValueFromString(final String string) {

    @SuppressWarnings("unchecked")
    final var value = (V) VALUE_CREATOR.createValueOfDataTypeFromString(
      DataType.forType(VALUE_TOOL.getDataType(this)),
      string);

    setValue(value);
  }

  @Override
  public IContentFieldDto internalToContentField() {
    return new ContentFieldDto(getName(), getStoredValue().toString());
  }

  @Override
  @SuppressWarnings("unchecked")
  void internalSetOrClearFromContent(final Object content) {
    internalValue = (V) content;
  }

  private void updateStateForSetValue(final V value) {
    internalValue = value;
  }
}
