//package declaration
package ch.nolix.system.schemaDataType;

//own import
import ch.nolix.system.entity.PropertyKind;

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
