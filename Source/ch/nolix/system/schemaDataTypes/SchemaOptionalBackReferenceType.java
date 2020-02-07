//package declaration
package ch.nolix.system.schemaDataTypes;

//own imports
import ch.nolix.system.databaseSchemaAdapter.EntitySet;
import ch.nolix.system.entity.PropertyKind;

//class
public final class SchemaOptionalBackReferenceType extends BaseSchemaBackReferenceType {
	
	//constructor
	public SchemaOptionalBackReferenceType(final EntitySet referencedEntitySet) {
		super(referencedEntitySet);
	}
	
	//method
	@Override
	public PropertyKind getPropertyKind() {
		return PropertyKind.OPTIONAL_BACK_REFERENCE;
	}
}
