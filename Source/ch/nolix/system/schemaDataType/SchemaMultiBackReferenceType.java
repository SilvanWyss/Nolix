//package declaration
package ch.nolix.system.schemaDataType;

//own imports
import ch.nolix.system.databaseSchemaAdapter.EntitySet;
import ch.nolix.system.entity.PropertyKind;

//class
public final class SchemaMultiBackReferenceType extends BaseSchemaBackReferenceType {
	
	//constructor
	public SchemaMultiBackReferenceType(final EntitySet referencedEntitySet) {
		super(referencedEntitySet);
	}
	
	//method
	@Override
	public PropertyKind getPropertyKind() {
		return PropertyKind.MULTI_BACK_REFERENCE;
	}
}
