//package declaration
package ch.nolix.system.objectschema.schemadto;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedReferenceTypeDto;

//class
public final class BaseParameterizedReferenceTypeDto extends ParameterizedFieldTypeDto
implements IBaseParameterizedReferenceTypeDto {

  //attribute
  private final String referencedTableId;

  //constructor
  public BaseParameterizedReferenceTypeDto(
    final ContentType contentType,
    final DataType dataType,
    final String referencedTableId) {

    super(contentType, dataType);

    if (referencedTableId == null) {
      throw ArgumentIsNullException.forArgumentName("referenced table id");
    }

    this.referencedTableId = referencedTableId;
  }

  //method
  @Override
  public String getReferencedTableId() {
    return referencedTableId;
  }
}
