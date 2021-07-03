//package declaration
package ch.nolix.system.databaseschema.parametrizedpropertytype;

//own imports
import ch.nolix.techapi.databaseschemaapi.propertytypeapi.PropertyType;
import ch.nolix.techapi.databaseschemaapi.schemaapi.ITable;

//class
public final class ParametrizedMultiReferenceType extends BaseParametrizedReferenceType {
	
	//constructor
	public ParametrizedMultiReferenceType(final ITable<?, ?, ?> referencedTable) {
		super(referencedTable);
	}
	
	//method
	@Override
	public PropertyType getPropertyType() {
		return PropertyType.MULTI_REFERENCE;
	}
}
