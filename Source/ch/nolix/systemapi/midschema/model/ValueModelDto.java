package ch.nolix.systemapi.midschema.model;

import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.ContentType;

public record ValueModelDto(DataType dataType) implements IContentModelDto {

  @Override
  public ContentType getContentType() {
    return ContentType.VALUE_FIELD;
  }

  @Override
  public DataType getDataType() {
    return dataType;
  }
}
