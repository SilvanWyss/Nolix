//package declaration
package ch.nolix.system.objectschema.parametrizedpropertytype;

import ch.nolix.techapi.databaseapi.propertytypeapi.PropertyType;

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
