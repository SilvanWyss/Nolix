//class
package ch.nolix.system.database.parametrizeddatatype;

import ch.nolix.businessapi.databaseapi.propertytypeapi.PropertyType;
//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.system.databaseschema.parametrizedschemadatatype.SchemaIdType;

//class
public final class ParametrizedIdType extends BaseParametrizedControlType<Long> {
	
	//constructor
	public ParametrizedIdType() {
		super(Long.class);
	}
	
	//method
	@Override
	public PropertyType getPropertyKind() {
		return PropertyType.ID;
	}
	
	//method
	@Override
	public SchemaIdType toSchemaDataType(
		final IContainer<ch.nolix.system.databaseschema.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		return new SchemaIdType();
	}
}
