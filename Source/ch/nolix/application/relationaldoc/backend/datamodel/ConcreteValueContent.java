package ch.nolix.application.relationaldoc.backend.datamodel;

import ch.nolix.application.relationaldoc.backend.datavalidator.ConcreteValueContentValidator;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ISmartField;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IConcreteValueContent;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IValueContent;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelbasepi.DataType;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.model.BackReference;
import ch.nolix.system.objectdata.model.MultiValue;
import ch.nolix.system.objectdata.model.OptionalValue;

public final class ConcreteValueContent extends ValueContent implements IConcreteValueContent {

  private static final ConcreteValueContentValidator CONCRETE_VALUE_CONTENT_VALIDATOR = //
  new ConcreteValueContentValidator();

  private final BackReference<SmartField> parentField = BackReference
    .forEntityAndBackReferencedFieldName(SmartField.class, "concreteValueContent");

  private final OptionalValue<String> dataType = OptionalValue.withValueType(String.class);

  private final MultiValue<String> values = MultiValue.withValueType(String.class);

  @Override
  public IConcreteValueContent addValue(final String value) {

    CONCRETE_VALUE_CONTENT_VALIDATOR.assertCanAddValue(this, value);

    values.addValue(value);

    return this;
  }

  @Override
  public DataType getDataType() {

    if (dataType.containsAny()) {
      return DataType.valueOf(dataType.getStoredValue());
    }

    final var baseField = getStoredParentField().getStoredBaseField();
    final var valueContent = (IValueContent) baseField.getStoredContent();
    return valueContent.getDataType();
  }

  @Override
  public ISmartField getStoredParentField() {
    return parentField.getStoredBackReferencedEntity();
  }

  @Override
  public IContainer<String> getStoredValues() {
    return values.getAllStoredValues();
  }

  @Override
  public boolean isAbstract() {
    return false;
  }

  @Override
  public boolean isEmpty() {
    return values.isEmpty();
  }

  @Override
  public void removeValue(final String value) {

    CONCRETE_VALUE_CONTENT_VALIDATOR.assertCanRemoveValue(this);

    values.removeValue(value);
    final var equalingValue = values.getAllStoredValues().getOptionalStoredFirst(v -> v.equals(value));
    if (equalingValue.isPresent()) {
      values.removeValue(equalingValue.get());
    }
  }

  @Override
  public void removeValues() {

    CONCRETE_VALUE_CONTENT_VALIDATOR.assertCanRemoveValues(this);

    values.clear();
  }

  @Override
  public IConcreteValueContent setDataType(final DataType dataType) {

    CONCRETE_VALUE_CONTENT_VALIDATOR.assertCanSetDataType(this, dataType);

    setDataTypeIfWillChange(dataType);

    return this;
  }

  private void setDataTypeIfWillChange(final DataType dataType) {
    if (getDataType() != dataType) {
      setDataTypeWhenWillChange(dataType);
    }
  }

  private void setDataTypeWhenWillChange(final DataType dataType) {
    this.dataType.setValue(dataType.toString());
  }
}
