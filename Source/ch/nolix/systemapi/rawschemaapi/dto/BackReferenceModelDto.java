package ch.nolix.systemapi.rawschemaapi.dto;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public record BackReferenceModelDto(DataType dataType, String backReferencedColumnId) implements IContentModelDto {

  @Override
  public ContentType getContentType() {
    return ContentType.BACK_REFERENCE;
  }

  @Override
  public DataType getDataType() {
    return dataType;
  }
}
