//package declaration
package ch.nolix.core.testing.validation;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerBooleanGetter;

//class
/**
 * A value mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2017-01-01
 * @param <V> is the type of the value of a value mediator.
 */
public class ValueMediator<V> extends Mediator {

	//attributes
	private final V value;
	
	//constructor
	/**
	 * Creates a new value mediator that belongs to the given test and is for the given value.
	 * 
	 * @param expectationErrorTaker
	 * @param value
	 * @throws ArgumentIsNullException if the given test is null.
	 */
	public ValueMediator(final IElementTaker<String> expectationErrorTaker, final V value) {
		
		//Calls constructor of the base class.
		super(expectationErrorTaker);
		
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
		
		if (getOriValue() == null && value != null) {
			addCurrentTestCaseError("A value that equals '" + value + "' was expected, but null was received.");
		}
		
		if (getOriValue() != null && !getOriValue().equals(value)) {
			addCurrentTestCaseError(
				"A value that equals '" + value + "' was expected, but '" + getOriValue() + "' was received."
			);
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this value mediator equals the given value.
	 * 
	 * @param value
	 */
	public final void isNotEqualTo(final Object value) {

		if (getOriValue() == null && value == null) {
			addCurrentTestCaseError("A value that is not null was expected, but null was received.");
		}
		
		if (getOriValue() != null && getOriValue().equals(value)) {
			addCurrentTestCaseError(
				"A value that does not equal '" + value + "' was expected, but " + getOriValue() + " was received."
			);
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
		
		//Asserts that the given condition is not null.
		if (condition == null) {
			throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.CONDITION);
		}
		
		if (!condition.getOutput(getOriValue())) {
			addCurrentTestCaseError(
				"A value that fulfils the given condition was expected, but '" + getOriValue() + "' was received."
			);
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
	
		//Asserts that the given condition is not null.
		if (condition == null) {
			throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.CONDITION);
		}
		
		if (condition.getOutput(getOriValue())) {
			addCurrentTestCaseError(
				"A value that does not fulfil the given condition was expected, but '"
				+ getOriValue()
				+ "' was received."
			);
		}
	}
	
	//method
	/**
	 * Generates an error if the value of the current {@link ValueMediator} does not have the given stringRepresentation.
	 * 
	 * @param stringRepresentation
	 */
	public final void hasStringRepresentation(final String stringRepresentation) {
		
		isNotNull();
		
		final var actualStringRepresentation = getOriValue().toString();
		
		if (actualStringRepresentation == null && stringRepresentation == null) {
			return;
		}
		
		if (actualStringRepresentation == null || !actualStringRepresentation.equals(stringRepresentation)) {
			addCurrentTestCaseError(
				"A value with the String representation '"
				+ stringRepresentation
				+ "' was expected, but a value with the String representation '"
				+ actualStringRepresentation
				+ "' was received."
			);
		}
	}
	
	//method
	/**
	 * Generates an error if the value of the current {@link ValueMediator} is not of the given type.
	 * 
	 * @param type
	 * @throws ArgumentIsNullException if the given type is null.
	 */
	public final void isOfType(final Class<?> type) {
		
		if (type == null) {
			throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.TYPE);
		}
		
		isNotNull();
		
		if (!type.isAssignableFrom(getOriValue().getClass())) {
			addCurrentTestCaseError(
				"A "
				+ type.getName()
				+ " was expected, but a "
				+ getOriValue().getClass().getName()
				+ " was received."
			);
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this value mediator is not the given value.
	 * 
	 * @param value
	 */
	public final void is(final Object value) {
		if (getOriValue() != value) {
			addCurrentTestCaseError("'" + value + "' was expected, but '" + getOriValue() + "' was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this value mediator is null.
	 */
	public final void isNotNull() {
		if (getOriValue() == null) {
			addCurrentTestCaseError("An object was expected, but null was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this value mediator is the given value.
	 * 
	 * @param value
	 */
	public final void isNot(final Object value) {
		if (getOriValue() == value) {
			addCurrentTestCaseError(
				"An other value than '" + getOriValue() + "' was expected, but the same value was received."
			);
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this value mediator is not null.
	 */
	public final void isNull() {
		if (getOriValue() != null) {
			addCurrentTestCaseError("Null was expected, but '" + getOriValue() + "' was received.");
		}
	}
	
	//method
	/**
	 * @return the value of this value mediator.
	 */
	protected final V getOriValue() {
		return value;
	}
}
