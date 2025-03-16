package ch.nolix.systemapi.midschemaapi.modelapi;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectschemaapi.fieldproperty.ContentType;

public record ReferenceModelDto(DataType dataType, String referencedTableId) implements IAbstractReferenceModelDto {

  @Override
  public ContentType getContentType() {
    return ContentType.REFERENCE;
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
