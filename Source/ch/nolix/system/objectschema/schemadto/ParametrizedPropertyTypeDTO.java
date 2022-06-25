//package declaration
package ch.nolix.system.objectschema.schemadto;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;

//class
public abstract class ParametrizedPropertyTypeDTO implements IParametrizedPropertyTypeDTO {
	
	//attributes
	private final PropertyType propertyType;
	private final DataType dataType;
	
	//constructor
	//For a better performance, this implementation does not use all comfortable methods.
	protected ParametrizedPropertyTypeDTO(final PropertyType propertyType, final DataType dataType) {
		
		if (propertyType == null) {
			throw ArgumentIsNullException.forArgumentType(PropertyType.class);
		}
		
		if (dataType == null) {
			throw ArgumentIsNullException.forArgumentType(DataType.class);
		}
		
		this.propertyType = propertyType;
		this.dataType = dataType;
	}
	
	//method
	@Override
	public final DataType getDataType() {
		return dataType;
	}
	
	//method
	@Override
	public final PropertyType getPropertyType() {
		return propertyType;
	}
}
