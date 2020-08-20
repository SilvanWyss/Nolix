//package declaration
package ch.nolix.system.dataType;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.system.entity.PropertyKind;
import ch.nolix.system.schemaDataType.SchemaOptionalValueType;

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
		final IContainer<ch.nolix.system.databaseSchemaAdapter.EntitySet> schemaEntitySets
	) {
		return new SchemaOptionalValueType<>(getRefContentClass());
	}
}
