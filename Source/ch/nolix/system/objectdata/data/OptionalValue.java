//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.objectdata.propertytool.OptionalValueTool;
import ch.nolix.system.objectdata.propertyvalidator.OptionalValueValidator;
import ch.nolix.system.sqlrawdatabase.databasedto.ContentFieldDto;
import ch.nolix.systemapi.entitypropertyapi.datatypeapi.DataType;
import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;
import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalValue;
import ch.nolix.systemapi.objectdataapi.propertytoolapi.IOptionalValueTool;
import ch.nolix.systemapi.objectdataapi.propertyvalidatorapi.IOptionalValueValidator;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IContentFieldDto;

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

  //method
  @Override
  public void clear() {

    internalValue = null;

    setAsEditedAndRunProbableUpdateAction();
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

    setAsEditedAndRunProbableUpdateAction();
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
