//package declaration
package ch.nolix.system.database.datatype;

import ch.nolix.businessapi.databaseapi.datatypeapi.DataType;
//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.system.database.schemadatatype.SchemaMultiValueType;

//class
public final class MultiValueType<C> extends BaseValueType<C> {
	
	//constructor
	public MultiValueType(final Class<C> contentClass) {
		super(contentClass);
	}
	
	//method
	@Override
	public DataType getPropertyKind() {
		return DataType.MULTI_VALUE;
	}
	
	//method
	@Override
	public SchemaMultiValueType<C> toSchemaDataType(
		final IContainer<ch.nolix.system.database.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		return new SchemaMultiValueType<>(getRefContentClass());
	}
}
