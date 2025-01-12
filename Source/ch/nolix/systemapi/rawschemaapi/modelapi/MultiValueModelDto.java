package ch.nolix.systemapi.rawschemaapi.modelapi;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public record MultiValueModelDto(DataType dataType) implements IContentModelDto {

  @Override
  public ContentType getContentType() {
    return ContentType.MULTI_VALUE;
  }

  @Override
  public DataType getDataType() {
    return dataType;
  }
}
