//package declaration
package ch.nolix.system.databaseschema.parametrizedpropertytype;

import ch.nolix.techapi.databasecommonapi.propertytypeapi.PropertyType;
import ch.nolix.techapi.databaseschemaapi.schemaapi.IColumn;

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
