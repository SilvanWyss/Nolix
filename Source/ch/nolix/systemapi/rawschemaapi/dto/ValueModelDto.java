package ch.nolix.systemapi.rawschemaapi.dto;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public record ValueModelDto(DataType dataType) implements IContentModelDto {

  @Override
  public ContentType getContentType() {
    return ContentType.VALUE;
  }

  @Override
  public DataType getDataType() {
    return dataType;
  }
}
