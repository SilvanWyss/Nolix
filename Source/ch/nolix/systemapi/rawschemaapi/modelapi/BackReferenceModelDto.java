package ch.nolix.systemapi.rawschemaapi.modelapi;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectschemaapi.fieldproperty.ContentType;

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
