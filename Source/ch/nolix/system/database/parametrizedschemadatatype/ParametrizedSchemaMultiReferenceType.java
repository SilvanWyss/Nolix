//package declaration
package ch.nolix.system.database.parametrizedschemadatatype;

import ch.nolix.businessapi.databaseapi.propertytypeapi.PropertyType;

//class
public final class ParametrizedSchemaMultiReferenceType extends BaseParametrizedSchemaReferenceType {
	
	//constructor
	public ParametrizedSchemaMultiReferenceType(final IEntitySet referencedEntitySet) {
		super(referencedEntitySet);
	}
	
	//method
	@Override
	public PropertyType getPropertyKind() {
		return PropertyType.MULTI_REFERENCE;
	}
}
