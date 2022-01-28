//package declaration
package ch.nolix.system.objectschema.parametrizedpropertytype;

import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;

//class
public final class ParametrizedMultiBackReferenceType extends BaseParametrizedBackReferenceType {
	
	//constructor
	public ParametrizedMultiBackReferenceType(final IColumn<SchemaImplementation> backReferencedColumn) {
		super(backReferencedColumn);
	}
	
	//method
	@Override
	public PropertyType getPropertyType() {
		return PropertyType.MULTI_BACK_REFERENCE;
	}
}
