//package declaration
package ch.nolix.system.schemadatatype;

//own import
import ch.nolix.system.entity.PropertyKind;

//class
public final class SchemaMultiBackReferenceType extends BaseSchemaBackReferenceType {
	
	//constructor
	public SchemaMultiBackReferenceType(final IEntitySet referencedEntitySet) {
		super(referencedEntitySet);
	}
	
	//method
	@Override
	public PropertyKind getPropertyKind() {
		return PropertyKind.MULTI_BACK_REFERENCE;
	}
}
