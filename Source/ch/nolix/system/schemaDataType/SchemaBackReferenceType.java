//package declaration
package ch.nolix.system.schemaDataType;

//own imports
import ch.nolix.system.databaseSchemaAdapter.EntitySet;
import ch.nolix.system.entity.PropertyKind;

//class
public final class SchemaBackReferenceType extends BaseSchemaBackReferenceType {
	
	//constructor
	public SchemaBackReferenceType(final EntitySet referencedEntitySet) {
		super(referencedEntitySet);
	}
	
	//method
	@Override
	public PropertyKind getPropertyKind() {
		return PropertyKind.BACK_REFERENCE;
	}
}
