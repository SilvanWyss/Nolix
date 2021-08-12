//package declaration
package ch.nolix.system.databaseschema.schemadto;

//own imports
import ch.nolix.techapi.databasecommonapi.propertytypeapi.PropertyType;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.IBaseParametrizedControlTypeDTO;

//class
public final class BaseParametrizedControlTypeDTO extends ParametrizedPropertyTypeDTO
implements IBaseParametrizedControlTypeDTO {
	
	//constructor
	public BaseParametrizedControlTypeDTO(final PropertyType propertyType, final String dataTypeFullClassName) {
		super(propertyType, dataTypeFullClassName);
	}
}
