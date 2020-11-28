//package declaration
package ch.nolix.system.schemadatatype;

//own import
import ch.nolix.system.entity.PropertyKind;

//class
public final class SchemaReferenceType extends BaseSchemaReferenceType {
	
	//constructor
	public SchemaReferenceType(final IEntitySet referencedEntitySet) {
		super(referencedEntitySet);
	}
	
	//method
	@Override
	public PropertyKind getPropertyKind() {
		return PropertyKind.REFERENCE;
	}
}
