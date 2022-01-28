//package declaration
package ch.nolix.system.objectschema.schemadto;

import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.rawobjectschemaapi.schemadtoapi.IBaseParametrizedValueTypeDTO;

//class
public final class BaseParametrizedValueTypeDTO extends ParametrizedPropertyTypeDTO
implements IBaseParametrizedValueTypeDTO {
	
	//constructor
	public BaseParametrizedValueTypeDTO(final PropertyType propertyType, final String dataTypeFullClassName) {
		super(propertyType, dataTypeFullClassName);
	}
}
