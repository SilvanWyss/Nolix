//package declaration
package ch.nolix.system.database.parametrizedschemadatatype;

import ch.nolix.businessapi.databaseapi.propertytypeapi.PropertyType;

//class
public final class ParametrizedSchemaReferenceType extends BaseParametrizedSchemaReferenceType {
	
	//constructor
	public ParametrizedSchemaReferenceType(final IEntitySet referencedEntitySet) {
		super(referencedEntitySet);
	}
	
	//method
	@Override
	public PropertyType getPropertyKind() {
		return PropertyType.REFERENCE;
	}
}
