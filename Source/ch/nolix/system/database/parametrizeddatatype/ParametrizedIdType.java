//class
package ch.nolix.system.database.parametrizeddatatype;

import ch.nolix.businessapi.databaseapi.datatypeapi.DataType;
//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.system.database.schemadatatype.SchemaIdType;

//class
public final class ParametrizedIdType extends BaseParametrizedTechnicalType<Long> {
	
	//constructor
	public ParametrizedIdType() {
		super(Long.class);
	}
	
	//method
	@Override
	public DataType getPropertyKind() {
		return DataType.ID;
	}
	
	//method
	@Override
	public SchemaIdType toSchemaDataType(
		final IContainer<ch.nolix.system.database.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		return new SchemaIdType();
	}
}
