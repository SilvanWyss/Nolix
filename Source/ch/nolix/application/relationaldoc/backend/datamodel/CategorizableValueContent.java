package ch.nolix.application.relationaldoc.backend.datamodel;

import ch.nolix.application.relationaldoc.backend.datavalidator.CategorizableValueContentValidator;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelabasepi.DataType;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ICategorizableValueContent;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ICategorizableField;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IConcreteValueContent;
import ch.nolix.system.objectdata.data.BackReference;
import ch.nolix.system.objectdata.data.Value;

public final class CategorizableValueContent extends ValueContent implements ICategorizableValueContent {

  public static final DataType DEFAULT_DATA_TYPE = DataType.TEXT;

  private static final CategorizableValueContentValidator ABSTRACT_VALUE_CONTENT_VALIDATOR = //
  new CategorizableValueContentValidator();

  private final BackReference<CategorizableField> parentField = BackReference
    .forEntityAndBackReferencedFieldName(CategorizableField.class, "abstractValueContent");

  private final Value<String> dataType = Value.withInitialValue(DEFAULT_DATA_TYPE.toString());

  public CategorizableValueContent() {
    initialize();
  }

  @Override
  public DataType getDataType() {
    return DataType.valueOf(dataType.getStoredValue());
  }

  @Override
  public ICategorizableField getStoredParentField() {
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
  public ICategorizableValueContent setDataType(final DataType dataType) {

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
