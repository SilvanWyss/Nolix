//package declaration
package ch.nolix.system.database.schemadatatype;

import ch.nolix.system.database.entity.PropertyKind;

//class
public final class SchemaIdType extends BaseSchemaControlType<Long> {
	
	//constructor
	public SchemaIdType() {
		super(Long.class);
	}
	
	//method
	@Override
	public PropertyKind getPropertyKind() {
		return PropertyKind.ID;
	}
}
