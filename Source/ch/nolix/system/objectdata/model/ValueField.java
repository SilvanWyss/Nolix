package ch.nolix.system.objectdata.model;

import ch.nolix.core.datamodel.fliedvalue.ValueMapper;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.coreapi.datamodel.fieldvalue.IValueMapper;
import ch.nolix.system.objectdata.fieldtool.FieldTool;
import ch.nolix.system.objectdata.fieldvalidator.ValueValidator;
import ch.nolix.systemapi.midschema.fieldproperty.ContentType;
import ch.nolix.systemapi.objectdata.fieldtool.IFieldTool;
import ch.nolix.systemapi.objectdata.fieldvalidator.IValueValidator;
import ch.nolix.systemapi.objectdata.model.IValueField;

public final class ValueField<V> extends AbstractBaseValueField<V> implements IValueField<V> {

  private static final IFieldTool FIELD_TOOL = new FieldTool();

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
    return ContentType.VALUE_FIELD;
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

    final var dataType = DataType.forType(FIELD_TOOL.getDataType(this));

    @SuppressWarnings("unchecked")
    final var value = (V) VALUE_MAPPER.mapStringToValue(string, dataType);

    setValue(value);
  }

  private void updateStateForSetValue(final V value) {
    internalValue = value;
  }
}
