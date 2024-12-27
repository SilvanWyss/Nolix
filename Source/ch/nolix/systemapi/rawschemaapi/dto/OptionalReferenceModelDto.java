package ch.nolix.systemapi.rawschemaapi.dto;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public record OptionalReferenceModelDto(DataType dataType, IContainer<String> referencedTableIds)
implements IContentModelDto {

  @Override
  public ContentType getContentType() {
    return ContentType.OPTIONAL_REFERENCE;
  }

  @Override
  public DataType getDataType() {
    return dataType;
  }
}
