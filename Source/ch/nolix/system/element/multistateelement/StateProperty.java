//package declaration
package ch.nolix.system.element.multistateelement;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.systemapi.elementapi.multistateelementapi.ValueStoringState;

//class
final class StateProperty<V> {
	
	//attribute
	private boolean defined;
	
	//optional attribute
	private V value;
	
	//method
	public ValueStoringState getAssignmentType() {
		
		if (!hasValueOrIsEmpty()) {
			return ValueStoringState.FORWARDING;
		}
		
		if (!hasValue()) {
			return ValueStoringState.DEFINING_EMPTY;
		}
		
		return ValueStoringState.STORING_VALUE;
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
			throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.VALUE);
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
