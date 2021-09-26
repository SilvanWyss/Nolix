//package declaration
package ch.nolix.system.objectschema.schemadto;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.techapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;

//class
public abstract class ParametrizedPropertyTypeDTO implements IParametrizedPropertyTypeDTO {
	
	//attributes
	private final PropertyType propertyType;
	private final String dataTypeFullClassName;
	
	//constructor
	//For a better performance, this implementation does not use all comfortable methods.
	public ParametrizedPropertyTypeDTO(final PropertyType propertyType,  final String dataTypeFullClassName) {
		
		if (propertyType == null) {
			throw new ArgumentIsNullException(PropertyType.class);
		}
		
		if (dataTypeFullClassName == null) {
			throw new ArgumentIsNullException("data type full class name");
		}
		
		this.propertyType = propertyType;
		this.dataTypeFullClassName = dataTypeFullClassName;
	}
	
	//method
	@Override
	public final String getDataTypeFullClassName() {
		return dataTypeFullClassName;
	}
	
	//method
	@Override
	public final PropertyType getPropertyType() {
		return propertyType;
	}
}
