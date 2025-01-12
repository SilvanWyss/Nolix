package ch.nolix.systemapi.rawschemaapi.modelapi;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public record MultiBackReferenceModelDto(DataType dataType, String backReferencedColumnId) implements IContentModelDto {

  @Override
  public ContentType getContentType() {
    return ContentType.MULTI_BACK_REFERENCE;
  }

  @Override
  public DataType getDataType() {
    return dataType;
  }
}
