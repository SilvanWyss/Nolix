//package declaration
package ch.nolix.system.schemaDataType;

//own import
import ch.nolix.system.entity.PropertyKind;

//class
public final class SchemaBackReferenceType extends BaseSchemaBackReferenceType {
	
	//constructor
	public SchemaBackReferenceType(final IEntitySet referencedEntitySet) {
		super(referencedEntitySet);
	}
	
	//method
	@Override
	public PropertyKind getPropertyKind() {
		return PropertyKind.BACK_REFERENCE;
	}
}
