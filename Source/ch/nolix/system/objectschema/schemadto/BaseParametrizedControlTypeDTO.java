//package declaration
package ch.nolix.system.objectschema.schemadto;

import ch.nolix.techapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.IBaseParametrizedControlTypeDTO;

//class
public final class BaseParametrizedControlTypeDTO extends ParametrizedPropertyTypeDTO
implements IBaseParametrizedControlTypeDTO {
	
	//constructor
	public BaseParametrizedControlTypeDTO(final PropertyType propertyType, final String dataTypeFullClassName) {
		super(propertyType, dataTypeFullClassName);
	}
}
