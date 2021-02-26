//package declaration
package ch.nolix.system.database.schemadatatype;

import ch.nolix.businessapi.databaseapi.datatypeapi.DataType;

//class
public final class SchemaValueType<V> extends BaseSchemaValueType<V> {
	
	//constructor
	public SchemaValueType(final Class<V> valueClass) {
		super(valueClass);
	}
	
	//method
	@Override
	public DataType getPropertyKind() {
		return DataType.VALUE;
	}
}
