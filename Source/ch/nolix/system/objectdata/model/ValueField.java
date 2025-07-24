package ch.nolix.system.objectdata.model;

import ch.nolix.core.datamodel.fliedvalue.ValueMapper;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.coreapi.datamodelapi.fieldvalueapi.IValueMapper;
import ch.nolix.system.objectdata.fieldtool.ValueFieldTool;
import ch.nolix.system.objectdata.fieldvalidator.ValueValidator;
import ch.nolix.systemapi.midschemaapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IValueFieldTool;
import ch.nolix.systemapi.objectdataapi.fieldvalidatorapi.IValueValidator;
import ch.nolix.systemapi.objectdataapi.modelapi.IValueField;

public final class ValueField<V> extends AbstractValueField<V> implements IValueField<V> {

  private static final IValueFieldTool VALUE_TOOL = new ValueFieldTool();

  private static final IValueValidator VALUE_VALIDATOR = new ValueValidator();

  private static final IValueMapper VALUE_MAPPER = new ValueMapper();

  private V internalValue;

  private ValueField(final Class<V> valueType) {
    super(valueType);
  }

  public static <V2> ValueField<V2> withInitialValue(final V2 initialValue) {

    @SuppressWarnings("unchecked")
    final var value = (ValueField<V2>) ValueField.withValueType(initialValue.getClass());

    value.setValue(initialValue);

    return value;
  }

  public static <V2> ValueField<V2> withValueType(final Class<V2> valueType) {
    return new ValueField<>(valueType);
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
  @SuppressWarnings("unchecked")
  public void internalSetOptionalContent(final Object content) {
    internalValue = (V) content;
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

    final var dataType = DataType.forType(VALUE_TOOL.getDataType(this));

    @SuppressWarnings("unchecked")
    final var value = (V) VALUE_MAPPER.mapStringToValue(string, dataType);

    setValue(value);
  }

  private void updateStateForSetValue(final V value) {
    internalValue = value;
  }
}
