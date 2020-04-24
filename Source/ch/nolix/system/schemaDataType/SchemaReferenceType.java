//package declaration
package ch.nolix.system.schemaDataType;

//own imports
import ch.nolix.system.databaseSchemaAdapter.EntitySet;
import ch.nolix.system.entity.PropertyKind;

//class
public final class SchemaReferenceType extends BaseSchemaReferenceType {
	
	//constructor
	public SchemaReferenceType(final EntitySet referencedEntitySet) {
		super(referencedEntitySet);
	}
	
	//method
	@Override
	public PropertyKind getPropertyKind() {
		return PropertyKind.REFERENCE;
	}
}
