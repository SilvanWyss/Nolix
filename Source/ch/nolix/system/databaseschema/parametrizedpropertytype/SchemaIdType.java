//package declaration
package ch.nolix.system.databaseschema.parametrizedpropertytype;

import ch.nolix.techapi.databasecommonapi.propertytypeapi.PropertyType;

//class
public final class SchemaIdType extends BaseParametrizedControlType<String> {
	
	//constructor
	public SchemaIdType() {
		super(String.class);
	}
	
	//method
	@Override
	public PropertyType getPropertyType() {
		return PropertyType.ID;
	}
}
