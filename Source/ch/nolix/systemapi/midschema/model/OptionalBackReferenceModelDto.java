package ch.nolix.systemapi.midschema.model;

import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

public record OptionalBackReferenceModelDto(DataType dataType, String backReferencedColumnId)
implements IContentModelDto {
  @Override
  public FieldType getFieldType() {
    return FieldType.OPTIONAL_BACK_REFERENCE;
  }

  @Override
  public DataType getDataType() {
    return dataType;
  }
}
