//package declaration
package ch.nolix.system.database.parametrizedschemadatatype;

import ch.nolix.businessapi.databaseapi.propertytypeapi.PropertyType;

//class
public final class ParametrizedSchemaMultiValueType<V> extends BaseParametrizedSchemaValueType<V> {
	
	//constructor
	public ParametrizedSchemaMultiValueType(final Class<V> valueClass) {
		super(valueClass);
	}
	
	//method
	@Override
	public PropertyType getPropertyKind() {
		return PropertyType.MULTI_VALUE;
	}
}
