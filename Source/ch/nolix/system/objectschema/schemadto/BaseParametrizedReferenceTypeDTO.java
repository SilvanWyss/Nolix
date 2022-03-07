//package declaration
package ch.nolix.system.objectschema.schemadto;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParametrizedReferenceTypeDTO;

//class
public final class BaseParametrizedReferenceTypeDTO extends ParametrizedPropertyTypeDTO
implements IBaseParametrizedReferenceTypeDTO {
	
	//attribute
	private final String referencedTableId;
	
	//constructor
	public BaseParametrizedReferenceTypeDTO(
		final PropertyType propertyType,
		final DataType dataType,
		final String referencedTableId
	) {
		
		super(propertyType, dataType);
		
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
