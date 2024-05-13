//package declaration
package ch.nolix.system.objectschema.schemadto;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedFieldTypeDto;

//class
public abstract class ParameterizedFieldTypeDto implements IParameterizedFieldTypeDto {

  //attribute
  private final ContentType contentType;

  //attribute
  private final DataType dataType;

  //constructor
  //For a better performance, this implementation does not use all comfortable methods.
  protected ParameterizedFieldTypeDto(final ContentType contentType, final DataType dataType) {

    if (contentType == null) {
      throw ArgumentIsNullException.forArgumentType(ContentType.class);
    }

    if (dataType == null) {
      throw ArgumentIsNullException.forArgumentType(DataType.class);
    }

    this.contentType = contentType;
    this.dataType = dataType;
  }

  //method
  @Override
  public final DataType getDataType() {
    return dataType;
  }

  //method
  @Override
  public final ContentType getFieldType() {
    return contentType;
  }
}
