package ch.nolix.systemapi.rawschemaapi.modelapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public record MultiReferenceModelDto(DataType dataType, IContainer<String> referencedTableIds)
implements IAbstractReferenceModelDto {

  @Override
  public ContentType getContentType() {
    return ContentType.MULTI_REFERENCE;
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
