package ch.nolix.systemapi.midschema.model;

import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.ContentType;

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
