//package declaration
package ch.nolix.system.database.schemadatatype;

import ch.nolix.system.database.entity.PropertyKind;

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
