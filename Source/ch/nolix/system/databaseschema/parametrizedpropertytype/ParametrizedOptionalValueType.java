//package declaration
package ch.nolix.system.databaseschema.parametrizedpropertytype;

import ch.nolix.techapi.databaseschemaapi.propertytypeapi.PropertyType;

//class
public final class ParametrizedOptionalValueType<V> extends BaseParametrizedValueType<V> {
	
	//constructor
	public ParametrizedOptionalValueType(final Class<V> valueClass) {
		super(valueClass);
	}
	
	//method
	@Override
	public PropertyType getPropertyType() {
		return PropertyType.OPTIONAL_VALUE;
	}
}
