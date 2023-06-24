//package declaration
package ch.nolix.system.objectschema.schemadto;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParametrizedReferenceTypeDto;

//class
public final class BaseParametrizedReferenceTypeDto extends ParametrizedPropertyTypeDto
implements IBaseParametrizedReferenceTypeDto {
	
	//attribute
	private final String referencedTableId;
	
	//constructor
	public BaseParametrizedReferenceTypeDto(
		final PropertyType propertyType,
		final DataType dataType,
		final String referencedTableId
	) {
		
		super(propertyType, dataType);
		
		if (referencedTableId == null) {
			throw ArgumentIsNullException.forArgumentName("referenced table id");
		}
		
		this.referencedTableId = referencedTableId;
	}
	
	//method
	@Override
	public String getReferencedTableId() {
		return referencedTableId;
	}
}
