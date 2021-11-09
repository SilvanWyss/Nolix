//package declaration
package ch.nolix.system.objectschema.parametrizedpropertytype;

//own imports
import ch.nolix.techapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.techapi.objectschemaapi.extendedschemaapi.IExtendedTable;

//class
public final class ParametrizedOptionalReferenceType extends BaseParametrizedReferenceType {
	
	//constructor
	public ParametrizedOptionalReferenceType(final IExtendedTable<?, ?, ?> referencedTable) {
		super(referencedTable);
	}
	
	//method
	@Override
	public PropertyType getPropertyType() {
		return PropertyType.OPTIONAL_REFERENCE;
	}
}
