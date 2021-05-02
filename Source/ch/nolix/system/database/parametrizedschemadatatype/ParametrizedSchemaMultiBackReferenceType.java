//package declaration
package ch.nolix.system.database.parametrizedschemadatatype;

import ch.nolix.businessapi.databaseapi.propertytypeapi.PropertyType;

//class
public final class ParametrizedSchemaMultiBackReferenceType extends BaseParametrizedSchemaBackReferenceType {
	
	//constructor
	public ParametrizedSchemaMultiBackReferenceType(final IEntitySet referencedEntitySet) {
		super(referencedEntitySet);
	}
	
	//method
	@Override
	public PropertyType getPropertyKind() {
		return PropertyType.MULTI_BACK_REFERENCE;
	}
}
