//package declaration
package ch.nolix.core.zetaTest;

//own imports
import ch.nolix.core.functional.IElementTakerBooleanGetter;
import ch.nolix.core.test.Accessor;
import ch.nolix.core.zetaValidator.ZetaValidator;

//abstract package-visible class
/**
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 200
 * @param <E> - The type of the element of an element mediator.
 */
abstract class ElementMediator<E> extends Mediator {

	//attributes
	private final E value;
	
	//constructor
	/**
	 * Creates new element mediator that belongs to the given zeta test and has the given value.
	 * 
	 * @param zetaTest
	 * @param value
	 * @throws NullArgumentException if the given zeta test is null.
	 */
	public ElementMediator(final ZetaTest zetaTest, final E value) {
		
		//Calls constructor of the base class.
		super(zetaTest);
		
		this.value = value;
	}
	
	//method
	/**
	 * Generates an error if the value of this element mediator is not equal to the given value.
	 * 
	 * @param value
	 */
	public final boolean equals(final Object value) {
		
		if (getValue() == null && value != null) {
			new Accessor(getZetaTest()).addCurrentTestMethodError("Null was expected, but '" + getValue() + "' was received.");
		}
		
		if (getValue() != null && value == null) {
			new Accessor(getZetaTest()).addCurrentTestMethodError("A value that is equal to '" + value + "' was expected, but null was received.");
		}
		
		if (!getValue().equals(value)) {
			new Accessor(getZetaTest()).addCurrentTestMethodError("A value that is equal to '" + value + "' was expected, but '" + getValue() + "' was received.");
		}
		
		return (
			(getValue() == null && value == null)
			|| getValue().equals(value)
		);
	}
	
	//method
	/**
	 * Generates an error if the value of this element mediator equals the given value.
	 * 
	 * @param value
	 */
	public final void equalsNot(final Object value) {

		if (getValue() == null && value == null) {
			new Accessor(getZetaTest()).addCurrentTestMethodError("A value was expected, but null was received.");
		}
		
		if (getValue() != null && getValue().equals(value)) {
			new Accessor(getZetaTest()).addCurrentTestMethodError("An other value than" + value + " was expected, but " +getValue() + " was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this element mediator does not fulfil the given condition.
	 * 
	 * @param condition
	 * @throws NullArgumentException if the given condition is null.
	 */
	public final void fulfils(final IElementTakerBooleanGetter<E> condition) {
		
		//Checks if the given condition is not null.
		ZetaValidator.supposeThat(condition).thatIsNamed("condition").isNotNull();
		
		if (!condition.getOutput(getValue())) {
			if (getValue() == null) {
				new Accessor(getZetaTest()).addCurrentTestMethodError("A value that fulfils the given condition was expected, but null was received.");
			}
			else {
				new Accessor(getZetaTest()).addCurrentTestMethodError("A value that fulfils the given condition was expected, but '" + getValue() + "' was received.");
			}
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this element mediator  fulfils the given condition.
	 * 
	 * @param condition
	 * @throws NullArgumentException if the given condition is null.
	 */
	public final void fulfilsNot(final IElementTakerBooleanGetter<E> condition) {
	
		//Checks if the given condition is not null.
		ZetaValidator.supposeThat(condition).thatIsNamed("condition").isNotNull();
		
		if (condition.getOutput(getValue())) {
			if (getValue() == null) {
				new Accessor(getZetaTest()).addCurrentTestMethodError("A value that does not fulfil the given condition was expected, but null was received.");
			}
			else {
				new Accessor(getZetaTest()).addCurrentTestMethodError("A value that does not fulfil the given condition was expected, but '" + getValue() + "' was received.");
			}
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this element is not the given value.
	 * 
	 * @param value
	 */
	public final void isSameAs(final Object value) {		
		if (getValue() != value) {
			
			//Handles the case if the value of this element mediator is null.
			if (getValue() == null) { //->The given value is not null.
				new Accessor(getZetaTest()).addCurrentTestMethodError("'" + value + "' was expected, but null was received.");
			}
			
			//Handles the case if the value of this element mediator is not null.
			else {
				
				//Handles the case if the given value is null.
				if (value == null) {
					new Accessor(getZetaTest()).addCurrentTestMethodError("Null was expected, but '" + getValue() + "' was received.");
				}
				
				//Handles the case if the given value is not null.
				else {
					new Accessor(getZetaTest()).addCurrentTestMethodError("'" + value + "' was expected, but '" + getValue() + "' was received.");
				}
			}
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this element is the given value.
	 * 
	 * @param value
	 */
	public final void isNotSameAs(final Object value) {
		if (getValue() == value) {
			
			//Handles the case if the value of this element mediator is null.
			if (getValue() == null) { //->The given value is null.
				new Accessor(getZetaTest()).addCurrentTestMethodError("Not null was expected, but null was received.");
			}
			
			//Handles the case if the value of this element mediator is not null.
			else { //->The given value is not null.
				new Accessor(getZetaTest()).addCurrentTestMethodError("An other value than " + getValue() + " was expected, but the same value was received.");
			}
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this element mediator is null.
	 */
	public final void isNotNull() {
		if (getValue() != null) {
			new Accessor(getZetaTest()).addCurrentTestMethodError("An object was expected, but null was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this element is not null.
	 */
	public final void isNull() {
		if (getValue() != null) {
			new Accessor(getZetaTest()).addCurrentTestMethodError("Null was expected, but '" + value + "' was received.");
		}
	}
	
	//method
	/**
	 * @return the value of this element mediator.
	 */
	protected E getValue() {
		return value;
	}
}
