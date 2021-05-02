//package declaration
package ch.nolix.system.database.parametrizedschemadatatype;

import ch.nolix.businessapi.databaseapi.propertytypeapi.PropertyType;

//class
public final class ParametrizedSchemaOptionalValueType<V> extends BaseParametrizedSchemaValueType<V> {
	
	//constructor
	public ParametrizedSchemaOptionalValueType(final Class<V> valueClass) {
		super(valueClass);
	}
	
	//method
	@Override
	public PropertyType getPropertyKind() {
		return PropertyType.OPTIONAL_VALUE;
	}
}
