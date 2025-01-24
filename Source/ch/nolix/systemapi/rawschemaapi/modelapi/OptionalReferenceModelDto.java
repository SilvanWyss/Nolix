package ch.nolix.systemapi.rawschemaapi.modelapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public record OptionalReferenceModelDto(DataType dataType, IContainer<String> referencedTableIds)
implements IAbstractReferenceModelDto {

  @Override
  public ContentType getContentType() {
    return ContentType.OPTIONAL_REFERENCE;
  }

  @Override
  public DataType getDataType() {
    return dataType;
  }

  @Override
  public IContainer<String> getReferencedTableIds() {
    return referencedTableIds;
  }
}
