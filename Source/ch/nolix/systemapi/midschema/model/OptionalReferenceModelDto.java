package ch.nolix.systemapi.midschema.model;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

public record OptionalReferenceModelDto(DataType dataType, IContainer<String> referenceableTableIds)
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
  public IContainer<String> getReferenceableTableIds() {
    return referenceableTableIds;
  }
}
