//package declaration
package ch.nolix.system.database.datatype;

import ch.nolix.businessapi.databaseapi.datatypeapi.DataType;
//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.system.database.schemadatatype.SchemaValueType;

//class
public final class ValueType<C> extends BaseValueType<C> {
	
	//constructor
	public ValueType(final Class<C> contentClass) {
		super(contentClass);
	}
	
	//method
	@Override
	public DataType getPropertyKind() {
		return DataType.VALUE;
	}
	
	//method
	@Override
	public SchemaValueType<C> toSchemaDataType(
		final IContainer<ch.nolix.system.database.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		return new SchemaValueType<>(getRefContentClass());
	}
}
