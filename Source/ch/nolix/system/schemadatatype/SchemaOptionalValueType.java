//package declaration
package ch.nolix.system.schemadatatype;

//own import
import ch.nolix.system.entity.PropertyKind;

//class
public final class SchemaOptionalValueType<V> extends BaseSchemaValueType<V> {
	
	//constructor
	public SchemaOptionalValueType(final Class<V> valueClass) {
		super(valueClass);
	}
	
	//method
	@Override
	public PropertyKind getPropertyKind() {
		return PropertyKind.OPTIONAL_VALUE;
	}
}
