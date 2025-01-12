package ch.nolix.systemapi.rawschemaapi.modelapi;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public record OptionalValueModelDto(DataType dataType) implements IContentModelDto {

  @Override
  public ContentType getContentType() {
    return ContentType.OPTIONAL_VALUE;
  }

  @Override
  public DataType getDataType() {
    return dataType;
  }
}
