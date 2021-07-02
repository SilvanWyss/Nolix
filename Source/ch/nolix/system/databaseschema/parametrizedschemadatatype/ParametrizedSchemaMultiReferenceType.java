//package declaration
package ch.nolix.system.databaseschema.parametrizedschemadatatype;

import ch.nolix.techapi.databaseschemaapi.propertytypeapi.PropertyType;

//class
public final class ParametrizedSchemaMultiReferenceType extends BaseParametrizedSchemaReferenceType {
	
	//constructor
	public ParametrizedSchemaMultiReferenceType(final IEntitySet referencedEntitySet) {
		super(referencedEntitySet);
	}
	
	//method
	@Override
	public PropertyType getPropertyType() {
		return PropertyType.MULTI_REFERENCE;
	}
}
