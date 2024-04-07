//package declaration
package ch.nolix.system.objectschema.schemadto;

import ch.nolix.systemapi.fieldapi.datatypeapi.DataType;
import ch.nolix.systemapi.fieldapi.mainapi.FieldType;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedValueTypeDto;

//class
public final class BaseParameterizedValueTypeDto extends ParameterizedPropertyTypeDto
implements IBaseParameterizedValueTypeDto {

  //constructor
  public BaseParameterizedValueTypeDto(final FieldType fieldType, final DataType dataType) {
    super(fieldType, dataType);
  }
}
