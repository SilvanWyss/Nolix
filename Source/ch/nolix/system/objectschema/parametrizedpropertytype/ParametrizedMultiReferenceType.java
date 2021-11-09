//package declaration
package ch.nolix.system.objectschema.parametrizedpropertytype;

//own imports
import ch.nolix.techapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.techapi.objectschemaapi.extendedschemaapi.IExtendedTable;

//class
public final class ParametrizedMultiReferenceType extends BaseParametrizedReferenceType {
	
	//constructor
	public ParametrizedMultiReferenceType(final IExtendedTable<?, ?, ?> referencedTable) {
		super(referencedTable);
	}
	
	//method
	@Override
	public PropertyType getPropertyType() {
		return PropertyType.MULTI_REFERENCE;
	}
}
