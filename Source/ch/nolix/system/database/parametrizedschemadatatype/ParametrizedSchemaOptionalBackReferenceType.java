//package declaration
package ch.nolix.system.database.parametrizedschemadatatype;

import ch.nolix.businessapi.databaseapi.propertytypeapi.PropertyType;

//class
public final class ParametrizedSchemaOptionalBackReferenceType extends BaseParametrizedSchemaBackReferenceType {
	
	//constructor
	public ParametrizedSchemaOptionalBackReferenceType(final IEntitySet referencedEntitySet) {
		super(referencedEntitySet);
	}
	
	//method
	@Override
	public PropertyType getPropertyKind() {
		return PropertyType.OPTIONAL_BACK_REFERENCE;
	}
}
