//package declaration
package ch.nolix.system.objectschema.schemadto;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.systemapi.entitypropertyapi.datatypeapi.DataType;
import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedPropertyTypeDto;

//class
public abstract class ParameterizedPropertyTypeDto implements IParameterizedPropertyTypeDto {

  //attribute
  private final PropertyType propertyType;

  //attribute
  private final DataType dataType;

  //constructor
  //For a better performance, this implementation does not use all comfortable
  //methods.
  protected ParameterizedPropertyTypeDto(final PropertyType propertyType, final DataType dataType) {

    if (propertyType == null) {
      throw ArgumentIsNullException.forArgumentType(PropertyType.class);
    }

    if (dataType == null) {
      throw ArgumentIsNullException.forArgumentType(DataType.class);
    }

    this.propertyType = propertyType;
    this.dataType = dataType;
  }

  //method
  @Override
  public final DataType getDataType() {
    return dataType;
  }

  //method
  @Override
  public final PropertyType getPropertyType() {
    return propertyType;
  }
}
