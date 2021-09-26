//package declaration
package ch.nolix.system.objectschema.parametrizedpropertytype;

import ch.nolix.techapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.techapi.objectschemaapi.schemaapi.ITable;

//class
public final class ParametrizedReferenceType extends BaseParametrizedReferenceType {
	
	//constructor
	public ParametrizedReferenceType(final ITable<?, ?, ?> referencedTable) {
		super(referencedTable);
	}
	
	//method
	@Override
	public PropertyType getPropertyType() {
		return PropertyType.REFERENCE;
	}
}
