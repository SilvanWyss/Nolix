//package declaration
package ch.nolix.system.databaseschema.parametrizedpropertytype;

import ch.nolix.techapi.databaseschemaapi.propertytypeapi.PropertyType;

//class
public final class ParametrizedBackReferenceType extends BaseParametrizedBackReferenceType {
	
	//constructor
	public ParametrizedBackReferenceType(final IEntitySet referencedEntitySet) {
		super(referencedEntitySet);
	}
	
	//method
	@Override
	public PropertyType getPropertyType() {
		return PropertyType.BACK_REFERENCE;
	}
}
