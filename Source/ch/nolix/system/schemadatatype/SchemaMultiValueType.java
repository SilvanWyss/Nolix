//package declaration
package ch.nolix.system.schemadatatype;

//own import
import ch.nolix.system.entity.PropertyKind;

//class
public final class SchemaMultiValueType<V> extends BaseSchemaValueType<V> {
	
	//constructor
	public SchemaMultiValueType(final Class<V> valueClass) {
		super(valueClass);
	}
	
	//method
	@Override
	public PropertyKind getPropertyKind() {
		return PropertyKind.MULTI_VALUE;
	}
}
