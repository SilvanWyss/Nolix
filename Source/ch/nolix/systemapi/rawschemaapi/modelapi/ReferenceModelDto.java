package ch.nolix.systemapi.rawschemaapi.modelapi;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

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
