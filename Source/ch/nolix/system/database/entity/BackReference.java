//package declaration
package ch.nolix.system.database.entity;

import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.techapi.databasecommonapi.propertytypeapi.PropertyType;

//class
public final class BackReference<E extends Entity> extends SingleBackReference<E> {
	
	//constructor
	public BackReference(final String referencingFieldName) {
		
		//Calls constructor of the base class.
		super(referencingFieldName);
	}
	
	//method
	@Override
	public PropertyType getPropertyKind() {
		return PropertyType.BACK_REFERENCE;
	}
	
	//method
	@Override
	public boolean isOptional() {
		return false;
	}
	
	//method
	@Override
	public void assertCanBeSaved() {
		if (!referencesBackEntity()) {
			throw new InvalidArgumentException(this, "does not reference back a " + getValueClass().getSimpleName());
		}
	}
}
