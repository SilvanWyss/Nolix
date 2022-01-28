//package declaration
package ch.nolix.system.objectschema.parametrizedpropertytype;

import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;

//class
public final class ParametrizedBackReferenceType extends BaseParametrizedBackReferenceType {
	
	//constructor
	public ParametrizedBackReferenceType(final IColumn<SchemaImplementation> backReferencedColumn) {
		super(backReferencedColumn);
	}
	
	//method
	@Override
	public PropertyType getPropertyType() {
		return PropertyType.BACK_REFERENCE;
	}
}
