//package declaration
package ch.nolix.system.database.datatype;

import ch.nolix.businessapi.databaseapi.datatypeapi.DataType;
//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.system.database.schemadatatype.SchemaOptionalValueType;

//class
public final class OptionalValueType<C> extends BaseValueType<C> {
	
	//constructor
	public OptionalValueType(final Class<C> contentClass) {
		super(contentClass);
	}
	
	//method
	@Override
	public DataType getPropertyKind() {
		return DataType.OPTIONAL_VALUE;
	}
	
	//method
	@Override
	public SchemaOptionalValueType<C> toSchemaDataType(
		final IContainer<ch.nolix.system.database.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		return new SchemaOptionalValueType<>(getRefContentClass());
	}
}
