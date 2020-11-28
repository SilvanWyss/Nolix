//package declaration
package ch.nolix.system.entity;

import ch.nolix.common.invalidargumentexception.InvalidArgumentException;

//class
public final class BackReference<E extends Entity> extends SingleBackReference<E> {
	
	//constructor
	public BackReference(final String referencingFieldName) {
		
		//Calls constructor of the base class.
		super(referencingFieldName);
	}
	
	//method
	@Override
	public PropertyKind getPropertyKind() {
		return PropertyKind.BACK_REFERENCE;
	}
	
	//method
	@Override
	public boolean isOptional() {
		return false;
	}
	
	//method
	@Override
	public void supposeCanBeSaved() {
		if (!referencesBackEntity()) {
			throw new InvalidArgumentException(this, "does not reference back a " + getValueClass().getSimpleName());
		}
	}
}
