//package declaration
package ch.nolix.system.database.parametrizedschemadatatype;

import ch.nolix.businessapi.databaseapi.propertytypeapi.PropertyType;

//class
public final class ParametrizedSchemaBackReferenceType extends BaseParametrizedSchemaBackReferenceType {
	
	//constructor
	public ParametrizedSchemaBackReferenceType(final IEntitySet referencedEntitySet) {
		super(referencedEntitySet);
	}
	
	//method
	@Override
	public PropertyType getPropertyKind() {
		return PropertyType.BACK_REFERENCE;
	}
}
