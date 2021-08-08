//package declaration
package ch.nolix.system.databaseschema.schemadto;

//own imports
import ch.nolix.techapi.databasecommonapi.propertytypeapi.PropertyType;
import ch.nolix.techapi.databaseschemaapi.schemadtoapi.IBaseParametrizedValueTypeDTO;

//class
public final class BaseParametrizedValueTypeDTO extends ParametrizedPropertyTypeDTO
implements IBaseParametrizedValueTypeDTO {
	
	//constructor
	public BaseParametrizedValueTypeDTO(final PropertyType propertyType, final String dataTypeFullClassName) {
		super(propertyType, dataTypeFullClassName);
	}
}
