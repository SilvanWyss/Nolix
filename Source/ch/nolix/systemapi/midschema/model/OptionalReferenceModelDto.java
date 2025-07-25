package ch.nolix.systemapi.midschema.model;

import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.ContentType;

public record OptionalReferenceModelDto(DataType dataType, String referencedTableId)
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
  public String getReferencedTableId() {
    return referencedTableId;
  }
}
