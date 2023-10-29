//package declaration
package ch.nolix.tech.relationaldoc.datamodel;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.constraintapi.IConstraint;
import ch.nolix.system.objectdatabase.database.BackReference;
import ch.nolix.system.objectdatabase.database.MultiValue;
import ch.nolix.system.objectdatabase.database.OptionalValue;
import ch.nolix.tech.relationaldoc.datavalidator.ConcreteValueContentValidator;
import ch.nolix.techapi.relationaldocapi.baseapi.DataType;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractValueContent;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableField;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IConcreteValueContent;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IValueContent;

//class
public final class ConcreteValueContent extends ValueContent implements IConcreteValueContent {

  //constant
  private static final ConcreteValueContentValidator CONCRETE_VALUE_CONTENT_VALIDATOR = //
  new ConcreteValueContentValidator();

  //attribute
  private final BackReference<AbstractableField> parentField = BackReference
    .forEntityAndBackReferencedPropertyName(AbstractableField.class, "concreteValueContent");

  //attribute
  private final OptionalValue<String> dataType = new OptionalValue<>();

  //attribute
  private final MultiValue<String> values = new MultiValue<>();

  //method
  @Override
  public IConcreteValueContent addValue(final String value) {

    CONCRETE_VALUE_CONTENT_VALIDATOR.assertCanAddValue(this, value);

    values.addValue(value);

    return this;
  }

  //method
  @Override
  public IContainer<IConstraint<String>> getConstraints() {

    if (getStoredParentField().inheritsFromBaseField()) {
      return getConstraintsWhenInheritsFromBaseField();
    }

    return new ImmutableList<>();
  }

  //method
  @Override
  public DataType getDataType() {

    if (dataType.containsAny()) {
      return DataType.valueOf(dataType.getStoredValue());
    }

    final var baseField = getStoredParentField().getStoredBaseField();
    final var valueContent = (IValueContent) baseField.getStoredContent();
    return valueContent.getDataType();
  }

  //method
  @Override
  public IAbstractableField getStoredParentField() {
    return parentField.getBackReferencedEntity();
  }

  //method
  @Override
  public IContainer<String> getStoredValues() {
    return values.getStoredValues();
  }

  //method
  @Override
  public boolean isAbstract() {
    return false;
  }

  //method
  @Override
  public boolean isEmpty() {
    return values.isEmpty();
  }

  //method
  @Override
  public void removeValue(final String value) {

    CONCRETE_VALUE_CONTENT_VALIDATOR.assertCanRemoveValue(this);

    values.removeValue(value);
    final var equalingValue = values.getStoredValues().getStoredFirstOrNull(v -> v.equals(value));
    if (equalingValue != null) {
      values.removeValue(equalingValue);
    }
  }

  //method
  @Override
  public void removeValues() {

    CONCRETE_VALUE_CONTENT_VALIDATOR.assertCanRemoveValues(this);

    values.clear();
  }

  //method
  @Override
  public IConcreteValueContent setDataType(final DataType dataType) {

    CONCRETE_VALUE_CONTENT_VALIDATOR.assertCanSetDataType(this, dataType);

    setDataTypeIfWillChange(dataType);

    return this;
  }

  //method
  private IContainer<IConstraint<String>> getConstraintsWhenInheritsFromBaseField() {

    final var baseField = getStoredParentField().getStoredBaseField();

    final var abstractValueContent = (IAbstractValueContent) baseField.getStoredContent();

    return abstractValueContent.getConstraints();
  }

  //method
  private void setDataTypeIfWillChange(final DataType dataType) {
    if (getDataType() != dataType) {
      setDataTypeWhenWillChange(dataType);
    }
  }

  //method
  private void setDataTypeWhenWillChange(final DataType dataType) {
    this.dataType.setValue(dataType.toString());
  }
}
