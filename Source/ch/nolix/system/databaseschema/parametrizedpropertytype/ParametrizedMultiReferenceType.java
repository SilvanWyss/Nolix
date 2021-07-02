//package declaration
package ch.nolix.system.databaseschema.parametrizedpropertytype;

import ch.nolix.techapi.databaseschemaapi.propertytypeapi.PropertyType;

//class
public final class ParametrizedMultiReferenceType extends BaseParametrizedReferenceType {
	
	//constructor
	public ParametrizedMultiReferenceType(final IEntitySet referencedEntitySet) {
		super(referencedEntitySet);
	}
	
	//method
	@Override
	public PropertyType getPropertyType() {
		return PropertyType.MULTI_REFERENCE;
	}
}
