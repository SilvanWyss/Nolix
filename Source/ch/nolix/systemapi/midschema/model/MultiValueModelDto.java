package ch.nolix.systemapi.midschema.model;

import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

public record MultiValueModelDto(DataType dataType) implements IContentModelDto {

  @Override
  public FieldType getContentType() {
    return FieldType.MULTI_VALUE_FIELD;
  }

  @Override
  public DataType getDataType() {
    return dataType;
  }
}
