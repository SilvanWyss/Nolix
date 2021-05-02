//package declaration
package ch.nolix.system.database.entity;

import ch.nolix.businessapi.databaseapi.propertytypeapi.PropertyType;

//class
public final class OptionalBackReference<E extends Entity> extends SingleBackReference<E> {
	
	//constructor
	public OptionalBackReference(final String referencingFieldName) {
		
		//Calls constructor of the base class.
		super(referencingFieldName);
	}
	
	//method
	@Override
	public PropertyType getPropertyKind() {
		return PropertyType.OPTIONAL_BACK_REFERENCE;
	}
	
	//method
	@Override
	public boolean isOptional() {
		return true;
	}
	
	//method
	@Override
	public void assertCanBeSaved() {}
}
