//package declaration
package ch.nolix.system.formatelement;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
final class StateProperty<V> {
	
	//attribute
	private boolean defined;
	
	//optional attribute
	private V value;
	
	//method
	public AssignmentType getAssignmentType() {
		
		if (!hasValueOrIsEmpty()) {
			return AssignmentType.UNDEFINED;
		}
		
		if (!hasValue()) {
			return AssignmentType.NO_VALUE;
		}
		
		return AssignmentType.VALUE;
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	public V getValue() {
		
		if (!defined || value == null) {
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseCatalogue.VALUE);
		}
		
		return value;
	}
	
	//method
	public boolean hasValue() {
		return (value != null);
	}
	
	//method
	public boolean hasValueOrIsEmpty() {
		return defined;
	}
	
	//method
	public void setEmpty() {
		defined = true;
		value = null;
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	public void setValue(final V value) {
		
		if (value == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.VALUE);
		}
		
		defined = true;
		this.value = value;
	}
	
	//method
	public void setUndefined() {
		defined = false;
		value = null;
	}
}
