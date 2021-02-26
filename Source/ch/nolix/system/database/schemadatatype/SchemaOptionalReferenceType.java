//package declaration
package ch.nolix.system.database.schemadatatype;

import ch.nolix.businessapi.databaseapi.datatypeapi.DataType;

//class
public final class SchemaOptionalReferenceType extends BaseSchemaReferenceType {
	
	//constructor
	public SchemaOptionalReferenceType(final IEntitySet referencedEntitySet) {
		super(referencedEntitySet);
	}
	
	//method
	@Override
	public DataType getPropertyKind() {
		return DataType.OPTIONAL_REFERENCE;
	}
}
