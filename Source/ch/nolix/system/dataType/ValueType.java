//package declaration
package ch.nolix.system.dataType;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.system.entity.PropertyKind;
import ch.nolix.system.schemaDataType.SchemaValueType;

//class
public final class ValueType<C> extends BaseValueType<C> {
	
	//constructor
	public ValueType(final Class<C> contentClass) {
		super(contentClass);
	}
	
	//method
	@Override
	public PropertyKind getPropertyKind() {
		return PropertyKind.VALUE;
	}
	
	//method
	@Override
	public SchemaValueType<C> toSchemaDataType(
		final IContainer<ch.nolix.system.databaseSchemaAdapter.EntitySet> schemaEntitySets
	) {
		return new SchemaValueType<>(getRefContentClass());
	}
}
