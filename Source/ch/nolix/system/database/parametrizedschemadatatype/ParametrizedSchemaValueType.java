//package declaration
package ch.nolix.system.database.parametrizedschemadatatype;

import ch.nolix.businessapi.databaseapi.datatypeapi.DataType;

//class
public final class ParametrizedSchemaValueType<V> extends BaseParametrizedSchemaValueType<V> {
	
	//constructor
	public ParametrizedSchemaValueType(final Class<V> valueClass) {
		super(valueClass);
	}
	
	//method
	@Override
	public DataType getPropertyKind() {
		return DataType.VALUE;
	}
}
