//package declaration
package ch.nolix.system.databaseschema.schemadto;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.techapi.databasecommonapi.propertytypeapi.PropertyType;
import ch.nolix.techapi.databaseschemaapi.schemadtoapi.IBaseParametrizedPropertyTypeDTO;

//class
public final class BaseParametrizedPropertyTypeDTO implements IBaseParametrizedPropertyTypeDTO {
	
	//attributes
	private final PropertyType propertyType;
	private final String dataTypeFullClassName;
	
	//constructor
	//For a better performance, this implementation does not use all comfortable methods.
	public BaseParametrizedPropertyTypeDTO(final PropertyType propertyType,  final String dataTypeFullClassName) {
		
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
	public String getDataTypeFullClassName() {
		return dataTypeFullClassName;
	}
	
	//method
	@Override
	public PropertyType getPropertyType() {
		return propertyType;
	}
}
