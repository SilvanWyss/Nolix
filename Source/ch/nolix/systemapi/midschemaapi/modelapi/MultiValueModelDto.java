package ch.nolix.systemapi.midschemaapi.modelapi;

import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.midschemaapi.fieldproperty.ContentType;

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
