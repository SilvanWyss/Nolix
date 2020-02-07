//package declaration
package ch.nolix.system.schemaDataTypes;

//own import
import ch.nolix.system.entity.PropertyKind;

//class
public final class SchemaValueType<V> extends BaseSchemaValueType<V> {
	
	//constructor
	public SchemaValueType(final Class<V> valueClass) {
		super(valueClass);
	}
	
	//method
	@Override
	public PropertyKind getPropertyKind() {
		return PropertyKind.VALUE;
	}
}
