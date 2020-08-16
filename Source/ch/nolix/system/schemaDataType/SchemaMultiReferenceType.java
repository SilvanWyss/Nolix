//package declaration
package ch.nolix.system.schemaDataType;

//own import
import ch.nolix.system.entity.PropertyKind;

//class
public final class SchemaMultiReferenceType extends BaseSchemaReferenceType {
	
	//constructor
	public SchemaMultiReferenceType(final IEntitySet referencedEntitySet) {
		super(referencedEntitySet);
	}
	
	//method
	@Override
	public PropertyKind getPropertyKind() {
		return PropertyKind.MULTI_REFERENCE;
	}
}
