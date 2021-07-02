//package declaration
package ch.nolix.system.databaseschema.parametrizedpropertytype;

import ch.nolix.techapi.databaseschemaapi.propertytypeapi.PropertyType;

//class
public final class SchemaIdType extends BaseParametrizedControlType<Long> {
	
	//constructor
	public SchemaIdType() {
		super(Long.class);
	}
	
	//method
	@Override
	public PropertyType getPropertyType() {
		return PropertyType.ID;
	}
}
