//class
package ch.nolix.system.datatype;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.system.entity.PropertyKind;
import ch.nolix.system.schemadatatype.SchemaIdType;

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
		final IContainer<ch.nolix.system.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		return new SchemaIdType();
	}
}
