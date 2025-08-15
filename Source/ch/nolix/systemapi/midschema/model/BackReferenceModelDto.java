package ch.nolix.systemapi.midschema.model;

import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

public record BackReferenceModelDto(DataType dataType, String backReferencedColumnId) implements IContentModelDto {

  @Override
  public FieldType getContentType() {
    return FieldType.BACK_REFERENCE;
  }

  @Override
  public DataType getDataType() {
    return dataType;
  }
}
