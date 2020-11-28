//package declaration
package ch.nolix.system.datatype;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.system.entity.PropertyKind;
import ch.nolix.system.schemadatatype.SchemaOptionalValueType;

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
		final IContainer<ch.nolix.system.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		return new SchemaOptionalValueType<>(getRefContentClass());
	}
}
