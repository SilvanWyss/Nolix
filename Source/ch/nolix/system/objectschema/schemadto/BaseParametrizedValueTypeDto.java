//package declaration
package ch.nolix.system.objectschema.schemadto;

//own imports
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParametrizedValueTypeDto;

//class
public final class BaseParametrizedValueTypeDto extends ParametrizedPropertyTypeDto
implements IBaseParametrizedValueTypeDto {
	
	//constructor
	public BaseParametrizedValueTypeDto(final PropertyType propertyType, final DataType dataType) {
		super(propertyType, dataType);
	}
}
