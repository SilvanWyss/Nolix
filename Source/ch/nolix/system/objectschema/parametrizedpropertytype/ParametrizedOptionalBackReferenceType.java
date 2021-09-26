//package declaration
package ch.nolix.system.objectschema.parametrizedpropertytype;

import ch.nolix.techapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.techapi.objectschemaapi.schemaapi.IColumn;

//class
public final class ParametrizedOptionalBackReferenceType extends BaseParametrizedBackReferenceType {
	
	//constructor
	public ParametrizedOptionalBackReferenceType(final IColumn<?, ?> backReferencedColumn) {
		super(backReferencedColumn);
	}
	
	//method
	@Override
	public PropertyType getPropertyType() {
		return PropertyType.OPTIONAL_BACK_REFERENCE;
	}
}
