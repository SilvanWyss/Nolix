//package declaration
package ch.nolix.system.databaseschema.parametrizedpropertytype;

import ch.nolix.techapi.databaseschemaapi.propertytypeapi.PropertyType;

//class
public final class ParametrizedValueType<V> extends BaseParametrizedValueType<V> {
	
	//constructor
	public ParametrizedValueType(final Class<V> valueClass) {
		super(valueClass);
	}
	
	//method
	@Override
	public PropertyType getPropertyType() {
		return PropertyType.VALUE;
	}
}
