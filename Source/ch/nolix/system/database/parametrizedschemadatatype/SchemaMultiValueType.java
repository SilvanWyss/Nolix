//package declaration
package ch.nolix.system.database.parametrizedschemadatatype;

import ch.nolix.businessapi.databaseapi.datatypeapi.DataType;

//class
public final class SchemaMultiValueType<V> extends BaseSchemaValueType<V> {
	
	//constructor
	public SchemaMultiValueType(final Class<V> valueClass) {
		super(valueClass);
	}
	
	//method
	@Override
	public DataType getPropertyKind() {
		return DataType.MULTI_VALUE;
	}
}
