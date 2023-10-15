//package declaration
package ch.nolix.system.objectschema.schemadto;

//own imports
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedValueTypeDto;

//class
public final class BaseParameterizedValueTypeDto extends ParameterizedPropertyTypeDto
    implements IBaseParameterizedValueTypeDto {

  // constructor
  public BaseParameterizedValueTypeDto(final PropertyType propertyType, final DataType dataType) {
    super(propertyType, dataType);
  }
}
