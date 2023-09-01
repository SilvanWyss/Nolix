//package declaration
package ch.nolix.system.objectschema.parameterizedpropertytype;

//own imports
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;

//class
public final class ParameterizedBackReferenceType extends BaseParameterizedBackReferenceType {
	
	//constructor
	public ParameterizedBackReferenceType(final IColumn backReferencedColumn) {
		super(backReferencedColumn);
	}
	
	//method
	@Override
	public PropertyType getPropertyType() {
		return PropertyType.BACK_REFERENCE;
	}
}