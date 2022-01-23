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
	private final String referencedTableId;
	
	//constructor
	public BaseParametrizedReferenceTypeDTO(
		final PropertyType propertyType,
		final String dataTypeFullClassName,
		final String referencedTableId
	) {
		
		super(propertyType, dataTypeFullClassName);
		
		if (referencedTableId == null) {
			throw new ArgumentIsNullException("referenced table id");
		}
		
		this.referencedTableId = referencedTableId;
	}
	
	//method
	@Override
	public String getReferencedTableId() {
		return referencedTableId;
	}
}
