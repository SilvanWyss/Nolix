package ch.nolix.systemapi.midschema.model;

import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

public record ValueModelDto(DataType dataType) implements IContentModelDto {
  @Override
  public FieldType getFieldType() {
    return FieldType.VALUE_FIELD;
  }

  @Override
  public DataType getDataType() {
    return dataType;
  }
}
