//package declaration
package ch.nolix.system.databaseschema.parametrizedschemadatatype;

import ch.nolix.techapi.databaseschemaapi.propertytypeapi.PropertyType;

//class
public final class ParametrizedSchemaValueType<V> extends BaseParametrizedSchemaValueType<V> {
	
	//constructor
	public ParametrizedSchemaValueType(final Class<V> valueClass) {
		super(valueClass);
	}
	
	//method
	@Override
	public PropertyType getPropertyType() {
		return PropertyType.VALUE;
	}
}
