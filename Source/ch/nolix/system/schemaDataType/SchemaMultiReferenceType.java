//package declaration
package ch.nolix.system.schemaDataType;

//own imports
import ch.nolix.system.databaseSchemaAdapter.EntitySet;
import ch.nolix.system.entity.PropertyKind;

//class
public final class SchemaMultiReferenceType extends BaseSchemaReferenceType {
	
	//constructor
	public SchemaMultiReferenceType(final EntitySet referencedEntitySet) {
		super(referencedEntitySet);
	}
	
	//method
	@Override
	public PropertyKind getPropertyKind() {
		return PropertyKind.MULTI_REFERENCE;
	}
}
