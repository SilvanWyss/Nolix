//package declaration
package ch.nolix.system.objectschema.schemadto;

//own imports
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParametrizedValueTypeDTO;

//class
public final class BaseParametrizedValueTypeDTO extends ParametrizedPropertyTypeDTO
implements IBaseParametrizedValueTypeDTO {
	
	//constructor
	public BaseParametrizedValueTypeDTO(final PropertyType propertyType, final DataType dataType) {
		super(propertyType, dataType);
	}
}
