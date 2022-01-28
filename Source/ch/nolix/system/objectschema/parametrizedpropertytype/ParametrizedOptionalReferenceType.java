//package declaration
package ch.nolix.system.objectschema.parametrizedpropertytype;

import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//class
public final class ParametrizedOptionalReferenceType extends BaseParametrizedReferenceType {
	
	//constructor
	public ParametrizedOptionalReferenceType(final ITable<SchemaImplementation> referencedTable) {
		super(referencedTable);
	}
	
	//method
	@Override
	public PropertyType getPropertyType() {
		return PropertyType.OPTIONAL_REFERENCE;
	}
}
