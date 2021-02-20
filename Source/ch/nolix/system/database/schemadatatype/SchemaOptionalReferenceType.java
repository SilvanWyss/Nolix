//package declaration
package ch.nolix.system.database.schemadatatype;

import ch.nolix.system.database.entity.PropertyKind;

//class
public final class SchemaOptionalReferenceType extends BaseSchemaReferenceType {
	
	//constructor
	public SchemaOptionalReferenceType(final IEntitySet referencedEntitySet) {
		super(referencedEntitySet);
	}
	
	//method
	@Override
	public PropertyKind getPropertyKind() {
		return PropertyKind.OPTIONAL_REFERENCE;
	}
}
