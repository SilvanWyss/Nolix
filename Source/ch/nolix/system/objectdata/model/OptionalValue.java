package ch.nolix.system.objectdata.model;

import ch.nolix.core.datamodel.fliedvalue.ValueMapper;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.coreapi.datamodelapi.fieldvalueapi.IValueMapper;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.system.objectdata.fieldtool.OptionalValueTool;
import ch.nolix.system.objectdata.fieldvalidator.OptionalValueValidator;
import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IOptionalValueTool;
import ch.nolix.systemapi.objectdataapi.fieldvalidatorapi.IOptionalValueValidator;
import ch.nolix.systemapi.objectdataapi.modelapi.IOptionalValue;
import ch.nolix.systemapi.objectschemaapi.fieldproperty.ContentType;

public final class OptionalValue<V> extends AbstractValue<V> implements IOptionalValue<V> {

  private static final IOptionalValueValidator OPTIONAL_VALUE_VALIDATOR = new OptionalValueValidator();

  private static final IOptionalValueTool OPTIONAL_VALUE_TOOL = new OptionalValueTool();

  private static final IValueMapper VALUE_MAPPER = new ValueMapper();

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
  @SuppressWarnings("unchecked")
  public void internalSetOptionalContent(final Object content) {
    if (content == null) {
      internalValue = null;
    } else {
      internalValue = (V) content;
    }
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

    final var dataType = DataType.forType(OPTIONAL_VALUE_TOOL.getDataType(this));

    @SuppressWarnings("unchecked")
    final var value = (V) VALUE_MAPPER.mapStringToValue(string, dataType);

    setValue(value);
  }

  private void updateStateForSetValue(final V value) {

    Validator.assertThat(value).thatIsNamed(LowerCaseVariableCatalog.VALUE).isNotNull();

    internalValue = value;
  }
}
