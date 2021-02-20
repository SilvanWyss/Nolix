//package declaration
package ch.nolix.system.database.datatype;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.system.database.entity.PropertyKind;
import ch.nolix.system.database.schemadatatype.SchemaOptionalValueType;

//class
public final class OptionalValueType<C> extends BaseValueType<C> {
	
	//constructor
	public OptionalValueType(final Class<C> contentClass) {
		super(contentClass);
	}
	
	//method
	@Override
	public PropertyKind getPropertyKind() {
		return PropertyKind.OPTIONAL_VALUE;
	}
	
	//method
	@Override
	public SchemaOptionalValueType<C> toSchemaDataType(
		final IContainer<ch.nolix.system.database.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		return new SchemaOptionalValueType<>(getRefContentClass());
	}
}
