package ch.nolix.system.objectschema.schemadto;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IContentModelDto;

public abstract class AbstractContentModelDto implements IContentModelDto {

  private final ContentType contentType;

  private final DataType dataType;

  //For a better performance, this implementation does not use all comfortable methods.
  protected AbstractContentModelDto(final ContentType contentType, final DataType dataType) {

    if (contentType == null) {
      throw ArgumentIsNullException.forArgumentType(ContentType.class);
    }

    if (dataType == null) {
      throw ArgumentIsNullException.forArgumentType(DataType.class);
    }

    this.contentType = contentType;
    this.dataType = dataType;
  }

  @Override
  public final DataType getDataType() {
    return dataType;
  }

  @Override
  public final ContentType getFieldType() {
    return contentType;
  }
}
