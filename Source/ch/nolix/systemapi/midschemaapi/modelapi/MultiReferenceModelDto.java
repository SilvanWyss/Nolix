package ch.nolix.systemapi.midschemaapi.modelapi;

import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.midschemaapi.fieldproperty.ContentType;

public record MultiReferenceModelDto(DataType dataType, String referencedTableId)
implements IAbstractReferenceModelDto {

  @Override
  public ContentType getContentType() {
    return ContentType.MULTI_REFERENCE;
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
