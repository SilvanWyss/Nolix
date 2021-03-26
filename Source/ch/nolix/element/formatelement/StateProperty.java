//package declaration
package ch.nolix.element.formatelement;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.validator.Validator;

//visibility-reduced class
final class StateProperty<V> {
	
	//attribute
	private boolean specifiesItself;
	
	//optional attribute
	private V value;
	
	//method
	public void clear() {
		specifiesItself = true;
		value = null;
	}
	
	//method
	public AssignmentType getAssignmentType() {
		
		if (!specifiesItself()) {
			return AssignmentType.UNSPECIFIED;
		}
		
		if (!hasValue()) {
			return AssignmentType.NO_VALUE;
		}
		
		return AssignmentType.VALUE;
	}
	
	//For a better performance, this implementation does not use all comfortable methods.
	//method
	public V getValue() {
		
		if (!specifiesItself) {
			throw new ArgumentDoesNotHaveAttributeException(this, LowerCaseCatalogue.VALUE);
		}
		
		return value;
	}
	
	//method
	public boolean hasValue() {
		return (value != null);
	}
	
	//method
	public void setValue(final V value) {
		
		Validator.assertThat(value).thatIsNamed(LowerCaseCatalogue.VALUE).isNotNull();
		
		specifiesItself = true;
		this.value = value;
	}
	
	//method
	public void setUnspecified() {
		specifiesItself = false;
		value = null;
	}
	
	//method
	public boolean specifiesItself() {
		return specifiesItself;
	}
}
