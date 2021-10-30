//package declaration
package ch.nolix.system.objectschema.schemadto;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.techapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IBaseParametrizedReferenceTypeDTO;

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
