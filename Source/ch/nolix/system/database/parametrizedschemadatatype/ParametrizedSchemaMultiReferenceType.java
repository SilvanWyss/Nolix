//package declaration
package ch.nolix.system.database.parametrizedschemadatatype;

import ch.nolix.businessapi.databaseapi.datatypeapi.DataType;

//class
public final class ParametrizedSchemaMultiReferenceType extends BaseParametrizedSchemaReferenceType {
	
	//constructor
	public ParametrizedSchemaMultiReferenceType(final IEntitySet referencedEntitySet) {
		super(referencedEntitySet);
	}
	
	//method
	@Override
	public DataType getPropertyKind() {
		return DataType.MULTI_REFERENCE;
	}
}
