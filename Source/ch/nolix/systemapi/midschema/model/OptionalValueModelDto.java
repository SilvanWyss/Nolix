package ch.nolix.systemapi.midschema.model;

import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

public record OptionalValueModelDto(DataType dataType) implements IContentModelDto {

  @Override
  public FieldType getContentType() {
    return FieldType.OPTIONAL_VALUE_FIELD;
  }

  @Override
  public DataType getDataType() {
    return dataType;
  }
}
