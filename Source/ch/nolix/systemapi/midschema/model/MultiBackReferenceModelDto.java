package ch.nolix.systemapi.midschema.model;

import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

public record MultiBackReferenceModelDto(DataType dataType, String backReferencedColumnId) implements IContentModelDto {
  @Override
  public FieldType getFieldType() {
    return FieldType.MULTI_BACK_REFERENCE;
  }

  @Override
  public DataType getDataType() {
    return dataType;
  }
}
