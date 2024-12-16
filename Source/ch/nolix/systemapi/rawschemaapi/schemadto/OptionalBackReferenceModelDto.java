package ch.nolix.systemapi.rawschemaapi.schemadto;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public record OptionalBackReferenceModelDto(DataType dataType, String backReferencedColumnId)
implements IContentModelDto {

  @Override
  public ContentType getContentType() {
    return ContentType.OPTIONAL_BACK_REFERENCE;
  }

  @Override
  public DataType getDataType() {
    return dataType;
  }
}
