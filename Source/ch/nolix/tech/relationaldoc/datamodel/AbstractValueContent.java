package ch.nolix.tech.relationaldoc.datamodel;

import ch.nolix.system.objectdata.data.BackReference;
import ch.nolix.system.objectdata.data.Value;
import ch.nolix.tech.relationaldoc.datavalidator.AbstractValueContentValidator;
import ch.nolix.techapi.relationaldocapi.baseapi.DataType;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractValueContent;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableField;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IConcreteValueContent;

public final class AbstractValueContent extends ValueContent implements IAbstractValueContent {

  public static final DataType DEFAULT_DATA_TYPE = DataType.TEXT;

  private static final AbstractValueContentValidator ABSTRACT_VALUE_CONTENT_VALIDATOR = //
  new AbstractValueContentValidator();

  private final BackReference<AbstractableField> parentField = BackReference
    .forEntityAndBackReferencedFieldName(AbstractableField.class, "abstractValueContent");

  private final Value<String> dataType = Value.withInitialValue(DEFAULT_DATA_TYPE.toString());

  public AbstractValueContent() {
    initialize();
  }

  @Override
  public DataType getDataType() {
    return DataType.valueOf(dataType.getStoredValue());
  }

  @Override
  public IAbstractableField getStoredParentField() {
    return parentField.getStoredBackReferencedEntity();
  }

  @Override
  public boolean isAbstract() {
    return true;
  }

  @Override
  public boolean isEmpty() {
    return true;
  }

  @Override
  public IAbstractValueContent setDataType(final DataType dataType) {

    ABSTRACT_VALUE_CONTENT_VALIDATOR.assertCanSetDataType(this, dataType);

    setDataTypeIfWillChange(dataType);

    return this;
  }

  private void clearRealisingFields() {

    final var localParentField = getStoredParentField();
    final var subTypes = getStoredParentField().getStoredParentObject().getStoredSubTypes();

    for (final var sb : subTypes) {

      final var field = sb.getStoredFields().getOptionalStoredFirst(f -> f.hasSameNameAs(localParentField));

      if (field.isPresent()) {

        final var concreteValueContent = (IConcreteValueContent) field.get().getStoredContent();

        concreteValueContent.removeValues();
      }
    }
  }

  private void setDataTypeIfWillChange(final DataType dataType) {
    if (getDataType() != dataType) {
      setDataTypeWhenWillChange(dataType);
    }
  }

  private void setDataTypeWhenWillChange(final DataType dataType) {

    this.dataType.setValue(dataType.toString());

    clearRealisingFields();
  }
}
