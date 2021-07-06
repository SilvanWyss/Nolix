//package declaration
package ch.nolix.system.databaseschema.schemadto;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.techapi.databasecommonapi.propertytypeapi.BasePropertyType;
import ch.nolix.techapi.databasecommonapi.propertytypeapi.PropertyType;
import ch.nolix.techapi.databaseschemaapi.schemadtoapi.IBaseParametrizedBackReferenceTypeDTO;

//class
public final class BaseParametrizedBackReferenceTypeDTO extends BaseParametrizedPropertyTypeDTO
implements IBaseParametrizedBackReferenceTypeDTO {
	
	//attributes
	private final String backReferencedTableName;
	private final String backReferencedColumnHeader;
	
	//constructor
	//For a better performance, this implementation does not use all comfortable methods.
	public BaseParametrizedBackReferenceTypeDTO(
		final PropertyType propertyType,
		final String dataTypeFullClassName,
		final String backReferencedTableName,
		final String backReferencedColumnHeader
	) {
		
		super(propertyType, dataTypeFullClassName);
		
		if (propertyType.getBaseType() != BasePropertyType.BASE_REFERENCE) {
			throw new InvalidArgumentException(propertyType);
		}
		
		if (backReferencedTableName == null) {
			throw new ArgumentIsNullException("back referenced table name");
		}
		
		if (backReferencedColumnHeader == null) {
			throw new ArgumentIsNullException("back referenced column header");
		}
		
		this.backReferencedTableName = backReferencedTableName;
		this.backReferencedColumnHeader = backReferencedColumnHeader;
	}
	
	//method
	@Override
	public String getBackReferencedColumnHeader() {
		return backReferencedColumnHeader;
	}
	
	//method
	@Override
	public String getBackReferencedTableName() {
		return backReferencedTableName;
	}
}
