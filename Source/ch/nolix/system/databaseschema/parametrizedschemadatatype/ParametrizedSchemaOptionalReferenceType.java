//package declaration
package ch.nolix.system.databaseschema.parametrizedschemadatatype;

import ch.nolix.techapi.databaseschemaapi.propertytypeapi.PropertyType;

//class
public final class ParametrizedSchemaOptionalReferenceType extends BaseParametrizedSchemaReferenceType {
	
	//constructor
	public ParametrizedSchemaOptionalReferenceType(final IEntitySet referencedEntitySet) {
		super(referencedEntitySet);
	}
	
	//method
	@Override
	public PropertyType getPropertyType() {
		return PropertyType.OPTIONAL_REFERENCE;
	}
}
