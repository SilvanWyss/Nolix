package ch.nolix.systemapi.midschema.model;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

public record BackReferenceModelDto(DataType dataType, IContainer<String> backReferenceableColumnIds)
implements IContentModelDto {
  @Override
  public FieldType getFieldType() {
    return FieldType.BACK_REFERENCE;
  }

  @Override
  public DataType getDataType() {
    return dataType;
  }
}
