//package declaration
package ch.nolix.system.objectschema.parametrizedpropertytype;

import ch.nolix.techapi.databasecommonapi.propertytypeapi.PropertyType;

//class
public final class IdType extends BaseParametrizedControlType<String> {
	
	//constructor
	public IdType() {
		super(String.class);
	}
	
	//method
	@Override
	public PropertyType getPropertyType() {
		return PropertyType.ID;
	}
}
