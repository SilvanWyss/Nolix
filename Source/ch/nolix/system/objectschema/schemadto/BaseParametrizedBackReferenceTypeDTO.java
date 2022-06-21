//package declaration
package ch.nolix.system.objectschema.schemadto;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.databaseapi.propertytypeapi.BasePropertyType;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParametrizedBackReferenceTypeDTO;

//class
public final class BaseParametrizedBackReferenceTypeDTO extends ParametrizedPropertyTypeDTO
implements IBaseParametrizedBackReferenceTypeDTO {
	
	//attributes
	private final String backReferencedColumnId;
	
	//constructor
	//For a better performance, this implementation does not use all comfortable methods.
	public BaseParametrizedBackReferenceTypeDTO(
		final PropertyType propertyType,
		final DataType dataType,
		final String backReferencedColumnId
	) {
		
		super(propertyType, dataType);
		
		if (propertyType.getBaseType() != BasePropertyType.BASE_BACK_REFERENCE) {
			throw InvalidArgumentException.forArgument(propertyType);
		}
		
		if (backReferencedColumnId == null) {
			throw ArgumentIsNullException.forArgumentName("back referenced column id");
		}
		
		this.backReferencedColumnId = backReferencedColumnId;
	}
	
	//method
	@Override
	public String getBackReferencedColumnId() {
		return backReferencedColumnId;
	}
}
