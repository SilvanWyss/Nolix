//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.objectdata.propertytool.OptionalValueTool;
import ch.nolix.system.objectdata.propertyvalidator.OptionalValueValidator;
import ch.nolix.system.sqlrawdata.datadto.ContentFieldDto;
import ch.nolix.systemapi.entitypropertyapi.datatypeapi.DataType;
import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;
import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalValue;
import ch.nolix.systemapi.objectdataapi.propertytoolapi.IOptionalValueTool;
import ch.nolix.systemapi.objectdataapi.propertyvalidatorapi.IOptionalValueValidator;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IContentFieldDto;

//class
public final class OptionalValue<V> extends BaseValue<V> implements IOptionalValue<V> {

  //constant
  private static final ValueCreator VALUE_CREATOR = new ValueCreator();

  //constant
  private static final IOptionalValueValidator OPTIONAL_VALUE_VALIDATOR = new OptionalValueValidator();

  //constant
  private static final IOptionalValueTool OPTIONAL_VALUE_TOOL = new OptionalValueTool();

  //optional attribute
  private V internalValue;

  //constructor
  private OptionalValue(final Class<V> valueType) {
    super(valueType);
  }

  //static method
  public static <V2> OptionalValue<V2> withInitialValue(final V2 initialValue) {

    @SuppressWarnings("unchecked")
    final var optionalValue = (OptionalValue<V2>) OptionalValue.withValueType(initialValue.getClass());

    optionalValue.setValue(initialValue);

    return optionalValue;
  }

  //static method
  public static <V2> OptionalValue<V2> withValueType(final Class<V2> valueType) {
    return new OptionalValue<>(valueType);
  }

  //method
  @Override
  public void clear() {

    internalValue = null;

    setAsEditedAndRunPotentialUpdateAction();
  }

  //method
  @Override
  public boolean isEmpty() {
    return (internalValue == null);
  }

  //method
  @Override
  public V getStoredValue() {

    OPTIONAL_VALUE_VALIDATOR.assertHasValue(this);

    return internalValue;
  }

  //method
  @Override
  public PropertyType getType() {
    return PropertyType.OPTIONAL_VALUE;
  }

  //method
  @Override
  public boolean isMandatory() {
    return false;
  }

  //method
  @Override
  public void setValue(final V value) {

    OPTIONAL_VALUE_VALIDATOR.assertCanSetGivenValue(this, value);

    updateStateForSetValue(value);

    setAsEditedAndRunPotentialUpdateAction();
  }

  //method
  @Override
  public void setValueFromString(final String string) {

    @SuppressWarnings("unchecked")
    final var value = (V) VALUE_CREATOR.createValueOfDataTypeFromString(
      DataType.forType(OPTIONAL_VALUE_TOOL.getDataType(this)),
      string);

    setValue(value);
  }

  //method
  @Override
  public IContentFieldDto technicalToContentField() {

    if (isEmpty()) {
      return new ContentFieldDto(getName());
    }

    return new ContentFieldDto(getName(), getStoredValue().toString());
  }

  //method
  @Override
  @SuppressWarnings("unchecked")
  void internalSetOrClearDirectlyFromContent(final Object content) {
    if (content == null) {
      internalValue = null;
    } else {
      internalValue = (V) content;
    }
  }

  //method
  private void updateStateForSetValue(final V value) {

    GlobalValidator.assertThat(value).thatIsNamed(LowerCaseVariableCatalogue.VALUE).isNotNull();

    internalValue = value;
  }
}
