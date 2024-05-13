//package declaration
package ch.nolix.system.objectschema.schemadto;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.fieldapi.mainapi.BaseFieldType;
import ch.nolix.systemapi.fieldapi.mainapi.FieldType;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedBackReferenceTypeDto;

//class
public final class BaseParameterizedBackReferenceTypeDto extends ParameterizedFieldTypeDto
implements IBaseParameterizedBackReferenceTypeDto {

  //attribute
  private final String backReferencedColumnId;

  //constructor
  //For a better performance, this implementation does not use all comfortable methods.
  public BaseParameterizedBackReferenceTypeDto(
    final FieldType fieldType,
    final DataType dataType,
    final String backReferencedColumnId) {

    super(fieldType, dataType);

    if (fieldType.getBaseType() != BaseFieldType.BASE_BACK_REFERENCE) {
      throw InvalidArgumentException.forArgument(fieldType);
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
