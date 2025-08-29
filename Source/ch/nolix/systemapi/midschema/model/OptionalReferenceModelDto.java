package ch.nolix.systemapi.midschema.model;

import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

public record OptionalReferenceModelDto(DataType dataType, String referencedTableId)
implements IBaseReferenceModelDto {

  @Override
  public FieldType getFieldType() {
    return FieldType.OPTIONAL_REFERENCE;
  }

  @Override
  public DataType getDataType() {
    return dataType;
  }

  @Override
  public String getReferencedTableId() {
    return referencedTableId;
  }
}
