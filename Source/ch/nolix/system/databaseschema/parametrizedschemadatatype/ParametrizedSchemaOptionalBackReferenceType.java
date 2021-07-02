//package declaration
package ch.nolix.system.databaseschema.parametrizedschemadatatype;

import ch.nolix.techapi.databaseschemaapi.propertytypeapi.PropertyType;

//class
public final class ParametrizedSchemaOptionalBackReferenceType extends BaseParametrizedSchemaBackReferenceType {
	
	//constructor
	public ParametrizedSchemaOptionalBackReferenceType(final IEntitySet referencedEntitySet) {
		super(referencedEntitySet);
	}
	
	//method
	@Override
	public PropertyType getPropertyType() {
		return PropertyType.OPTIONAL_BACK_REFERENCE;
	}
}
