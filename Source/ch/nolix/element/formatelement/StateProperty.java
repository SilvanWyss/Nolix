//package declaration
package ch.nolix.element.formatelement;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;

//class
final class StateProperty<V> {
	
	//attribute
	private boolean defined;
	
	//optional attribute
	private V value;
	
	//method
	public AssignmentType getAssignmentType() {
		
		if (!isDefined()) {
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
			throw new ArgumentDoesNotHaveAttributeException(this, LowerCaseCatalogue.VALUE);
		}
		
		return value;
	}
	
	//method
	public boolean hasValue() {
		return (value != null);
	}
	
	//method
	public boolean isDefined() {
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
