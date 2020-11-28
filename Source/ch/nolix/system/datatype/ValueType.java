//package declaration
package ch.nolix.system.datatype;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.system.entity.PropertyKind;
import ch.nolix.system.schemadatatype.SchemaValueType;

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
		final IContainer<ch.nolix.system.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		return new SchemaValueType<>(getRefContentClass());
	}
}
