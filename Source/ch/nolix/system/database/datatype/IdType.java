//class
package ch.nolix.system.database.datatype;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.system.database.entity.PropertyKind;
import ch.nolix.system.database.schemadatatype.SchemaIdType;

//class
public final class IdType extends BaseTechnicalType<Long> {
	
	//constructor
	public IdType() {
		super(Long.class);
	}
	
	//method
	@Override
	public PropertyKind getPropertyKind() {
		return PropertyKind.ID;
	}
	
	//method
	@Override
	public SchemaIdType toSchemaDataType(
		final IContainer<ch.nolix.system.database.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		return new SchemaIdType();
	}
}
