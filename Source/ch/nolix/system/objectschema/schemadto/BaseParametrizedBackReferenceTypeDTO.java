//package declaration
package ch.nolix.system.objectschema.schemadto;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.techapi.databaseapi.propertytypeapi.BasePropertyType;
import ch.nolix.techapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IBaseParametrizedBackReferenceTypeDTO;

//class
public final class BaseParametrizedBackReferenceTypeDTO extends ParametrizedPropertyTypeDTO
implements IBaseParametrizedBackReferenceTypeDTO {
	
	//attributes
	private final String backReferencedColumnId;
	
	//constructor
	//For a better performance, this implementation does not use all comfortable methods.
	public BaseParametrizedBackReferenceTypeDTO(
		final PropertyType propertyType,
		final String dataTypeFullClassName,
		final String backReferencedColumnId
	) {
		
		super(propertyType, dataTypeFullClassName);
		
		if (propertyType.getBaseType() != BasePropertyType.BASE_BACK_REFERENCE) {
			throw new InvalidArgumentException(propertyType);
		}
		
		if (backReferencedColumnId == null) {
			throw new ArgumentIsNullException("back referenced column id");
		}
		
		this.backReferencedColumnId = backReferencedColumnId;
	}
	
	//method
	@Override
	public String getBackReferencedColumnId() {
		return backReferencedColumnId;
	}
}
