//package declaration
package ch.nolix.primitive.test2;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.functionInterfaces.IElementTakerBooleanGetter;
import ch.nolix.primitive.invalidArgumentException.NullArgumentException;

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
	 * @throws NullArgumentException if the given test is null.
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
			addCurrentTestMethodError("A value that equals '" + value + "' was expected, but null was received.");
		}
		
		if (getRefValue() != null && !getRefValue().equals(value)) {
			addCurrentTestMethodError("A value that equals '" + value + "' was expected, but '" + getRefValue() + "' was received.");
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
			addCurrentTestMethodError("A value that is not null was expected, but null was received.");
		}
		
		if (getRefValue() != null && getRefValue().equals(value)) {
			addCurrentTestMethodError("A value that does not equal '" + value + "' was expected, but " + getRefValue() + " was received.");
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
		if (condition == null) {
			throw new NullArgumentException(VariableNameCatalogue.CONDITION);
		}
		
		if (!condition.getOutput(getRefValue())) {
			addCurrentTestMethodError("A value that fulfils the given condition was expected, but '" + getRefValue() + "' was received.");
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
		if (condition == null) {
			throw new NullArgumentException(VariableNameCatalogue.CONDITION);
		}
		
		if (condition.getOutput(getRefValue())) {
			addCurrentTestMethodError("A value that does not fulfil the given condition was expected, but '" + getRefValue() + "' was received.");
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
			addCurrentTestMethodError("'" + value + "' was expected, but '" + getRefValue() + "' was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this value mediator is null.
	 */
	public final void isNotNull() {
		if (getRefValue() != null) {
			addCurrentTestMethodError("An object was expected, but null was received.");
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
			addCurrentTestMethodError("An other value than '" + getRefValue() + "' was expected, but the same value was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this value mediator is not null.
	 */
	public final void isNull() {
		if (getRefValue() != null) {
			addCurrentTestMethodError("Null was expected, but '" + getRefValue() + "' was received.");
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
