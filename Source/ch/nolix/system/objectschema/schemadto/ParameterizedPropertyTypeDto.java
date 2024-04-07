//package declaration
package ch.nolix.system.objectschema.schemadto;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.systemapi.fieldapi.datatypeapi.DataType;
import ch.nolix.systemapi.fieldapi.mainapi.FieldType;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedPropertyTypeDto;

//class
public abstract class ParameterizedPropertyTypeDto implements IParameterizedPropertyTypeDto {

  //attribute
  private final FieldType fieldType;

  //attribute
  private final DataType dataType;

  //constructor
  //For a better performance, this implementation does not use all comfortable
  //methods.
  protected ParameterizedPropertyTypeDto(final FieldType fieldType, final DataType dataType) {

    if (fieldType == null) {
      throw ArgumentIsNullException.forArgumentType(FieldType.class);
    }

    if (dataType == null) {
      throw ArgumentIsNullException.forArgumentType(DataType.class);
    }

    this.fieldType = fieldType;
    this.dataType = dataType;
  }

  //method
  @Override
  public final DataType getDataType() {
    return dataType;
  }

  //method
  @Override
  public final FieldType getPropertyType() {
    return fieldType;
  }
}
