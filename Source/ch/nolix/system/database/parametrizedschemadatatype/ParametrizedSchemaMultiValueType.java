//package declaration
package ch.nolix.system.database.parametrizedschemadatatype;

import ch.nolix.businessapi.databaseapi.datatypeapi.DataType;

//class
public final class ParametrizedSchemaMultiValueType<V> extends BaseParametrizedSchemaValueType<V> {
	
	//constructor
	public ParametrizedSchemaMultiValueType(final Class<V> valueClass) {
		super(valueClass);
	}
	
	//method
	@Override
	public DataType getPropertyKind() {
		return DataType.MULTI_VALUE;
	}
}
