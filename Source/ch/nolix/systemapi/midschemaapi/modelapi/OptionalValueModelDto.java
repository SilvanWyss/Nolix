package ch.nolix.systemapi.midschemaapi.modelapi;

import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.midschemaapi.fieldproperty.ContentType;

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
