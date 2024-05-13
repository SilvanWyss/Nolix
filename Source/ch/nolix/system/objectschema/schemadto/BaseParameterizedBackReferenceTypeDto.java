//package declaration
package ch.nolix.system.objectschema.schemadto;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectdataapi.fieldproperty.BaseContentType;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedBackReferenceTypeDto;

//class
public final class BaseParameterizedBackReferenceTypeDto extends ParameterizedFieldTypeDto
implements IBaseParameterizedBackReferenceTypeDto {

  //attribute
  private final String backReferencedColumnId;

  //constructor
  //For a better performance, this implementation does not use all comfortable methods.
  public BaseParameterizedBackReferenceTypeDto(
    final ContentType contentType,
    final DataType dataType,
    final String backReferencedColumnId) {

    super(contentType, dataType);

    if (contentType.getBaseType() != BaseContentType.BASE_BACK_REFERENCE) {
      throw InvalidArgumentException.forArgument(contentType);
    }

    if (backReferencedColumnId == null) {
      throw ArgumentIsNullException.forArgumentName("back referenced column id");
    }

    this.backReferencedColumnId = backReferencedColumnId;
  }

  //method
  @Override
  public String getBackReferencedColumnId() {
    return backReferencedColumnId;
  }
}
