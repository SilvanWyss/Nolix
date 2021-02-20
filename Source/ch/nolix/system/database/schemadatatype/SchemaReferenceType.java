//package declaration
package ch.nolix.system.database.schemadatatype;

import ch.nolix.system.database.entity.PropertyKind;

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
