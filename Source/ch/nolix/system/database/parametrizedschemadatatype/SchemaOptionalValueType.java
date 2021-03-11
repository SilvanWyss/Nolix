//package declaration
package ch.nolix.system.database.parametrizedschemadatatype;

import ch.nolix.businessapi.databaseapi.datatypeapi.DataType;

//class
public final class SchemaOptionalValueType<V> extends BaseSchemaValueType<V> {
	
	//constructor
	public SchemaOptionalValueType(final Class<V> valueClass) {
		super(valueClass);
	}
	
	//method
	@Override
	public DataType getPropertyKind() {
		return DataType.OPTIONAL_VALUE;
	}
}
