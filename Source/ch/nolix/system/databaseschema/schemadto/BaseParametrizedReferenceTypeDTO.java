//package declaration
package ch.nolix.system.databaseschema.schemadto;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.techapi.databasecommonapi.propertytypeapi.PropertyType;
import ch.nolix.techapi.databaseschemaapi.schemadtoapi.IBaseParametrizedReferenceTypeDTO;

//class
public final class BaseParametrizedReferenceTypeDTO extends ParametrizedPropertyTypeDTO
implements IBaseParametrizedReferenceTypeDTO {
	
	//attribute
	private final String referencedTableName;
	
	//constructor
	public BaseParametrizedReferenceTypeDTO(
		final PropertyType propertyType,
		final String dataTypeFullClassName,
		final String referencedTableName
	) {
		
		super(propertyType, dataTypeFullClassName);
		
		if (referencedTableName == null) {
			throw new ArgumentIsNullException("referenced table name");
		}
		
		this.referencedTableName = referencedTableName;
	}
	
	//method
	@Override
	public String getReferencedTableName() {
		return referencedTableName;
	}
}
