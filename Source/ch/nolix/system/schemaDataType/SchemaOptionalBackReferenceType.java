//package declaration
package ch.nolix.system.schemaDataType;

//own import
import ch.nolix.system.entity.PropertyKind;

//class
public final class SchemaOptionalBackReferenceType extends BaseSchemaBackReferenceType {
	
	//constructor
	public SchemaOptionalBackReferenceType(final IEntitySet referencedEntitySet) {
		super(referencedEntitySet);
	}
	
	//method
	@Override
	public PropertyKind getPropertyKind() {
		return PropertyKind.OPTIONAL_BACK_REFERENCE;
	}
}
