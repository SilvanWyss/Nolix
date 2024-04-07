//package declaration
package ch.nolix.system.objectschema.schemadto;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.systemapi.fieldapi.datatypeapi.DataType;
import ch.nolix.systemapi.fieldapi.mainapi.FieldType;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedReferenceTypeDto;

//class
public final class BaseParameterizedReferenceTypeDto extends ParameterizedPropertyTypeDto
implements IBaseParameterizedReferenceTypeDto {

  //attribute
  private final String referencedTableId;

  //constructor
  public BaseParameterizedReferenceTypeDto(
    final FieldType fieldType,
    final DataType dataType,
    final String referencedTableId) {

    super(fieldType, dataType);

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
