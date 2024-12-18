package ch.nolix.system.objectdata.data;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.objectdata.fieldtool.OptionalValueTool;
import ch.nolix.system.objectdata.fieldvalidator.OptionalValueValidator;
import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalValue;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IOptionalValueTool;
import ch.nolix.systemapi.objectdataapi.fieldvalidatorapi.IOptionalValueValidator;
import ch.nolix.systemapi.rawdataapi.datadto.ContentFieldWithContentAsStringDto;

public final class OptionalValue<V> extends BaseValue<V> implements IOptionalValue<V> {

  private static final ValueCreator VALUE_CREATOR = new ValueCreator();

  private static final IOptionalValueValidator OPTIONAL_VALUE_VALIDATOR = new OptionalValueValidator();

  private static final IOptionalValueTool OPTIONAL_VALUE_TOOL = new OptionalValueTool();

  private V internalValue;

  private OptionalValue(final Class<V> valueType) {
    super(valueType);
  }

  public static <V2> OptionalValue<V2> withInitialValue(final V2 initialValue) {

    @SuppressWarnings("unchecked")
    final var optionalValue = (OptionalValue<V2>) OptionalValue.withValueType(initialValue.getClass());

    optionalValue.setValue(initialValue);

    return optionalValue;
  }

  public static <V2> OptionalValue<V2> withValueType(final Class<V2> valueType) {
    return new OptionalValue<>(valueType);
  }

  @Override
  public void clear() {

    internalValue = null;

    setAsEditedAndRunPotentialUpdateAction();
  }

  @Override
  public boolean isEmpty() {
    return (internalValue == null);
  }

  @Override
  public V getStoredValue() {

    OPTIONAL_VALUE_VALIDATOR.assertHasValue(this);

    return internalValue;
  }

  @Override
  public ContentType getType() {
    return ContentType.OPTIONAL_VALUE;
  }

  @Override
  public boolean isMandatory() {
    return false;
  }

  @Override
  public void setValue(final V value) {

    OPTIONAL_VALUE_VALIDATOR.assertCanSetGivenValue(this, value);

    updateStateForSetValue(value);

    setAsEditedAndRunPotentialUpdateAction();
  }

  @Override
  public void setValueFromString(final String string) {

    @SuppressWarnings("unchecked")
    final var value = (V) VALUE_CREATOR.createValueOfDataTypeFromString(
      DataType.forType(OPTIONAL_VALUE_TOOL.getDataType(this)),
      string);

    setValue(value);
  }

  @Override
  public ContentFieldWithContentAsStringDto internalToContentField() {

    if (isEmpty()) {
      return ContentFieldWithContentAsStringDto.withColumnName(getName());
    }

    return ContentFieldWithContentAsStringDto.withColumnNameAndContent(getName(), getStoredValue().toString());
  }

  @Override
  @SuppressWarnings("unchecked")
  void internalSetOrClearFromContent(final Object content) {
    if (content == null) {
      internalValue = null;
    } else {
      internalValue = (V) content;
    }
  }

  private void updateStateForSetValue(final V value) {

    GlobalValidator.assertThat(value).thatIsNamed(LowerCaseVariableCatalogue.VALUE).isNotNull();

    internalValue = value;
  }
}
