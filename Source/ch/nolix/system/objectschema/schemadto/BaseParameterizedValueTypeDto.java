//package declaration
package ch.nolix.system.objectschema.schemadto;

import ch.nolix.systemapi.entitypropertyapi.datatypeapi.DataType;
import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedValueTypeDto;

//class
public final class BaseParameterizedValueTypeDto extends ParameterizedPropertyTypeDto
implements IBaseParameterizedValueTypeDto {

  //constructor
  public BaseParameterizedValueTypeDto(final PropertyType propertyType, final DataType dataType) {
    super(propertyType, dataType);
  }
}
