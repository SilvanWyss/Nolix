//package declaration
package ch.nolix.system.database.schemadatatype;

import ch.nolix.system.database.entity.PropertyKind;

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
