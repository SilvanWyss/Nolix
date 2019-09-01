//package declaration
package ch.nolix.common.test;

import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.functionAPI.IElementTakerBooleanGetter;
import ch.nolix.common.invalidArgumentExceptions.ArgumentIsNullException;

//class
/**
 * A value mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 160
 * @param <V> The type of the value of a value mediator.
 */
public class ValueMediator<V> extends Mediator {

	//attributes
	private final V value;
	
	//constructor
	/**
	 * Creates a new value mediator that belongs to the given test and is for the given value.
	 * 
	 * @param test
	 * @param value
	 * @throws ArgumentIsNullException if the given test is null.
	 */
	public ValueMediator(final Test test, final V value) {
		
		//Calls constructor of the base class.
		super(test);
		
		//Sets the value of this value mediator.
		this.value = value;
	}
	
	//method
	/**
	 * Generates an error if the value of this value mediator does not equal the given value.
	 * 
	 * @param value
	 */
	public final void isEqualTo(final Object value) {
		
		if (getRefValue() == null && value != null) {
			addCurrentTestCaseError("A value that equals '" + value + "' was expected, but null was received.");
		}
		
		if (getRefValue() != null && !getRefValue().equals(value)) {
			addCurrentTestCaseError("A value that equals '" + value + "' was expected, but '" + getRefValue() + "' was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this value mediator equals the given value.
	 * 
	 * @param value
	 */
	public final void isNotEqualTo(final Object value) {

		if (getRefValue() == null && value == null) {
			addCurrentTestCaseError("A value that is not null was expected, but null was received.");
		}
		
		if (getRefValue() != null && getRefValue().equals(value)) {
			addCurrentTestCaseError("A value that does not equal '" + value + "' was expected, but " + getRefValue() + " was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this value mediator does not fulfil the given condition.
	 * 
	 * @param condition
	 * @throws ArgumentIsNullException if the given condition is null.
	 */
	public final void fulfils(final IElementTakerBooleanGetter<V> condition) {
		
		//Checks if the given condition is not null.
		if (condition == null) {
			throw new ArgumentIsNullException(VariableNameCatalogue.CONDITION);
		}
		
		if (!condition.getOutput(getRefValue())) {
			addCurrentTestCaseError("A value that fulfils the given condition was expected, but '" + getRefValue() + "' was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this value mediator fulfils the given condition.
	 * 
	 * @param condition
	 * @throws ArgumentIsNullException if the given condition is null.
	 */
	public final void fulfilsNot(final IElementTakerBooleanGetter<V> condition) {
	
		//Checks if the given condition is not null.
		if (condition == null) {
			throw new ArgumentIsNullException(VariableNameCatalogue.CONDITION);
		}
		
		if (condition.getOutput(getRefValue())) {
			addCurrentTestCaseError("A value that does not fulfil the given condition was expected, but '" + getRefValue() + "' was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this value mediator is not the given value.
	 * 
	 * @param value
	 */
	public final void isSameAs(final Object value) {
		if (getRefValue() != value) {
			addCurrentTestCaseError("'" + value + "' was expected, but '" + getRefValue() + "' was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this value mediator is null.
	 */
	public final void isNotNull() {
		if (getRefValue() != null) {
			addCurrentTestCaseError("An object was expected, but null was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this value mediator is the given value.
	 * 
	 * @param value
	 */
	public final void isNotSameAs(final Object value) {
		if (getRefValue() == value) {
			addCurrentTestCaseError("An other value than '" + getRefValue() + "' was expected, but the same value was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this value mediator is not null.
	 */
	public final void isNull() {
		if (getRefValue() != null) {
			addCurrentTestCaseError("Null was expected, but '" + getRefValue() + "' was received.");
		}
	}
	
	//method
	/**
	 * @return the value of this value mediator.
	 */
	protected final V getRefValue() {
		return value;
	}
}
