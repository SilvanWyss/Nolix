//package declaration
package ch.nolix.core.test2;

//own imports
import ch.nolix.core.functionInterfaces.IElementTakerBooleanGetter;
import ch.nolix.core.testoid.TestAccessor;
import ch.nolix.core.validator2.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 200
 * @param <V> - The type of the value of an value mediator.
 */
public class ValueMediator<V> extends Mediator {

	//attributes
	private final V value;
	
	//constructor
	/**
	 * Creates new value mediator that belongs to the given zeta test and has the given value.
	 * 
	 * @param test
	 * @param value
	 * @throws NullArgumentException if the given zeta test is null.
	 */
	public ValueMediator(final Test test, final V value) {
		
		//Calls constructor of the base class.
		super(test);
		
		this.value = value;
	}
	
	//method
	/**
	 * Generates an error if the value of this value mediator is not equal to the given value.
	 * 
	 * @param value
	 */
	public final void equalsTo(final Object value) {
		
		if (getValue() == null && value != null) {
			new TestAccessor(getTest()).addCurrentTestMethodError("Null was expected, but '" + getValue() + "' was received.");
		}
		
		if (getValue() != null && value == null) {
			new TestAccessor(getTest()).addCurrentTestMethodError("A value that is equal to '" + value + "' was expected, but null was received.");
		}
		
		if (!getValue().equals(value)) {
			new TestAccessor(getTest()).addCurrentTestMethodError("A value that is equal to '" + value + "' was expected, but '" + getValue() + "' was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this value mediator equals the given value.
	 * 
	 * @param value
	 */
	public final void equalsNot(final Object value) {

		if (getValue() == null && value == null) {
			new TestAccessor(getTest()).addCurrentTestMethodError("A value was expected, but null was received.");
		}
		
		if (getValue() != null && getValue().equals(value)) {
			new TestAccessor(getTest()).addCurrentTestMethodError("An other value than" + value + " was expected, but " +getValue() + " was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this value mediator does not fulfil the given condition.
	 * 
	 * @param condition
	 * @throws NullArgumentException if the given condition is null.
	 */
	public final void fulfils(final IElementTakerBooleanGetter<V> condition) {
		
		//Checks if the given condition is not null.
		Validator.suppose(condition).thatIsNamed("condition").isNotNull();
		
		if (!condition.getOutput(getValue())) {
			if (getValue() == null) {
				new TestAccessor(getTest()).addCurrentTestMethodError("A value that fulfils the given condition was expected, but null was received.");
			}
			else {
				new TestAccessor(getTest()).addCurrentTestMethodError("A value that fulfils the given condition was expected, but '" + getValue() + "' was received.");
			}
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this value mediator  fulfils the given condition.
	 * 
	 * @param condition
	 * @throws NullArgumentException if the given condition is null.
	 */
	public final void fulfilsNot(final IElementTakerBooleanGetter<V> condition) {
	
		//Checks if the given condition is not null.
		Validator.suppose(condition).thatIsNamed("condition").isNotNull();
		
		if (condition.getOutput(getValue())) {
			if (getValue() == null) {
				new TestAccessor(getTest()).addCurrentTestMethodError("A value that does not fulfil the given condition was expected, but null was received.");
			}
			else {
				new TestAccessor(getTest()).addCurrentTestMethodError("A value that does not fulfil the given condition was expected, but '" + getValue() + "' was received.");
			}
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this value is not the given value.
	 * 
	 * @param value
	 */
	public final void isSameAs(final Object value) {		
		if (getValue() != value) {
			
			//Handles the case that the value of this value mediator is null.
			if (getValue() == null) { //->The given value is not null.
				new TestAccessor(getTest()).addCurrentTestMethodError("'" + value + "' was expected, but null was received.");
			}
			
			//Handles the case that the value of this value mediator is not null.
			else {
				
				//Handles the case that the given value is null.
				if (value == null) {
					new TestAccessor(getTest()).addCurrentTestMethodError("Null was expected, but '" + getValue() + "' was received.");
				}
				
				//Handles the case that the given value is not null.
				else {
					new TestAccessor(getTest()).addCurrentTestMethodError("'" + value + "' was expected, but '" + getValue() + "' was received.");
				}
			}
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this value is the given value.
	 * 
	 * @param value
	 */
	public final void isNotSameAs(final Object value) {
		if (getValue() == value) {
			
			//Handles the case that the value of this value mediator is null.
			if (getValue() == null) { //->The given value is null.
				new TestAccessor(getTest()).addCurrentTestMethodError("Not null was expected, but null was received.");
			}
			
			//Handles the case that the value of this value mediator is not null.
			else { //->The given value is not null.
				new TestAccessor(getTest()).addCurrentTestMethodError("An other value than " + getValue() + " was expected, but the same value was received.");
			}
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this value mediator is null.
	 */
	public final void isNotNull() {
		if (getValue() != null) {
			new TestAccessor(getTest()).addCurrentTestMethodError("An object was expected, but null was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this value is not null.
	 */
	public final void isNull() {
		if (getValue() != null) {
			new TestAccessor(getTest()).addCurrentTestMethodError("Null was expected, but '" + value + "' was received.");
		}
	}
	
	//method
	/**
	 * @return the value of this value mediator.
	 */
	protected V getValue() {
		return value;
	}
}
