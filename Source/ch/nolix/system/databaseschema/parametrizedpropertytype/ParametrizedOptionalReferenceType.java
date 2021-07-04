//package declaration
package ch.nolix.system.databaseschema.parametrizedpropertytype;

import ch.nolix.techapi.databasecommonapi.propertytypeapi.PropertyType;
import ch.nolix.techapi.databaseschemaapi.schemaapi.ITable;

//class
public final class ParametrizedOptionalReferenceType extends BaseParametrizedReferenceType {
	
	//constructor
	public ParametrizedOptionalReferenceType(final ITable<?, ?, ?> referencedTable) {
		super(referencedTable);
	}
	
	//method
	@Override
	public PropertyType getPropertyType() {
		return PropertyType.OPTIONAL_REFERENCE;
	}
}
