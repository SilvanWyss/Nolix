//package declaration
package ch.nolix.system.databaseschema.parametrizedpropertytype;

//own imports
import ch.nolix.techapi.databaseschemaapi.propertytypeapi.PropertyType;
import ch.nolix.techapi.databaseschemaapi.schemaapi.ITable;

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
