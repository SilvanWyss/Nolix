//package declaration
package ch.nolix.system.databaseschema.schemadto;

//own imports
import ch.nolix.techapi.databasecommonapi.propertytypeapi.PropertyType;

//class
public final class BaseParametrizedValueTypeDTO extends BaseParametrizedPropertyTypeDTO {
	
	//constructor
	public BaseParametrizedValueTypeDTO(final PropertyType propertyType, final String dataTypeFullClassName) {
		super(propertyType, dataTypeFullClassName);
	}
}
