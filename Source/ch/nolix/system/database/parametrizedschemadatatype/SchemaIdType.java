//package declaration
package ch.nolix.system.database.parametrizedschemadatatype;

import ch.nolix.businessapi.databaseapi.datatypeapi.DataType;

//class
public final class SchemaIdType extends BaseSchemaControlType<Long> {
	
	//constructor
	public SchemaIdType() {
		super(Long.class);
	}
	
	//method
	@Override
	public DataType getPropertyKind() {
		return DataType.ID;
	}
}
