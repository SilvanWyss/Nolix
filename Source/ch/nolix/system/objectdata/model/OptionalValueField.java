package ch.nolix.system.objectdata.model;

import ch.nolix.core.datamodel.fliedvalue.ValueMapper;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.coreapi.datamodel.fieldvalue.IValueMapper;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.system.objectdata.fieldtool.OptionalValueFieldTool;
import ch.nolix.system.objectdata.fieldvalidator.OptionalValueValidator;
import ch.nolix.systemapi.midschema.fieldproperty.ContentType;
import ch.nolix.systemapi.objectdata.fieldtool.IOptionalValueFieldTool;
import ch.nolix.systemapi.objectdata.fieldvalidator.IOptionalValueValidator;
import ch.nolix.systemapi.objectdata.model.IOptionalValueField;

public final class OptionalValueField<V> extends AbstractValueField<V> implements IOptionalValueField<V> {

  private static final IOptionalValueValidator OPTIONAL_VALUE_VALIDATOR = new OptionalValueValidator();

  private static final IOptionalValueFieldTool OPTIONAL_VALUE_TOOL = new OptionalValueFieldTool();

  private static final IValueMapper VALUE_MAPPER = new ValueMapper();

  private V internalValue;

  private OptionalValueField(final Class<V> valueType) {
    super(valueType);
  }

  public static <V2> OptionalValueField<V2> withInitialValue(final V2 initialValue) {

    @SuppressWarnings("unchecked")
    final var optionalValue = (OptionalValueField<V2>) OptionalValueField.withValueType(initialValue.getClass());

    optionalValue.setValue(initialValue);

    return optionalValue;
  }

  public static <V2> OptionalValueField<V2> withValueType(final Class<V2> valueType) {
    return new OptionalValueField<>(valueType);
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

    OPTIONAL_VALUE_VALIDATOR.assertContainsValue(this);

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

    OPTIONAL_VALUE_VALIDATOR.assertCanSetValue(this, value);

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
