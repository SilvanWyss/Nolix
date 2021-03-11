//package declaration
package ch.nolix.system.database.parametrizedschemadatatype;

import ch.nolix.businessapi.databaseapi.datatypeapi.DataType;

//class
public final class SchemaMultiBackReferenceType extends BaseSchemaBackReferenceType {
	
	//constructor
	public SchemaMultiBackReferenceType(final IEntitySet referencedEntitySet) {
		super(referencedEntitySet);
	}
	
	//method
	@Override
	public DataType getPropertyKind() {
		return DataType.MULTI_BACK_REFERENCE;
	}
}
