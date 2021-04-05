//package declaration
package ch.nolix.element.formatelement;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.validator.Validator;

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
	
	//For a better performance, this implementation does not use all comfortable methods.
	//method
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
	public void setValue(final V value) {
		
		Validator.assertThat(value).thatIsNamed(LowerCaseCatalogue.VALUE).isNotNull();
		
		defined = true;
		this.value = value;
	}
	
	//method
	public void setUndefined() {
		defined = false;
		value = null;
	}
}
