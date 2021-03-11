//package declaration
package ch.nolix.system.database.parametrizeddatatype;

import ch.nolix.businessapi.databaseapi.datatypeapi.DataType;
//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.system.database.schemadatatype.SchemaValueType;

//class
public final class ParametrizedValueType<C> extends BaseParametrizedValueType<C> {
	
	//constructor
	public ParametrizedValueType(final Class<C> contentClass) {
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
