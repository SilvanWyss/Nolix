//package declaration
package ch.nolix.core.testing.validation;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonBiggerArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.functionuniversalapi.IElementTaker;
import ch.nolix.core.functionuniversalapi.IElementTakerBooleanGetter;
import ch.nolix.core.name.LowerCaseCatalogue;

//class
/**
 * A long mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2016-09-01
 */
public final class LongMediator extends Mediator {

	//attribute
	private final long value;
	
	//constructor
	/**
	 * Creates a new long mediator that belongs to the given test and is for the given value.
	 * 
	 * @param expectationErrorTaker
	 * @param value
	 */
	public LongMediator(final IElementTaker<String> expectationErrorTaker, final long value) {
		
		//Calls constructor of the base class.
		super(expectationErrorTaker);
		
		//Sets the value of this long mediator.
		this.value = value;
	}
	
	//method
	/**
	 * Generates an error if the value of this long mediator does not fulfil the given condition.
	 * 
	 * @param condition
	 * @throws ArgumentIsNullException if the given condition is null.
	 */
	public void fulfils(final IElementTakerBooleanGetter<Long> condition) {
		
		//Asserts that the given condition is not null.
		if (condition == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.CONDITION);
		}
		
		if (!condition.getOutput(value)) {
			addCurrentTestCaseError("A value that fulfils the given condition was expected, but " + value + " was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this long mediator is not between the given min and max.
	 * 
	 * @param min
	 * @param max
	 * @throws NonBiggerArgumentException if the given max is not bigger than the given min.
	 */
	public void isBetween(final long min, final long max) {
		
		//Asserts that the given max is bigger than the given min.
		if (max <= min) {
			throw new NonBiggerArgumentException("max", max, min);
		}
		
		if (value < min || value > max) {
			addCurrentTestCaseError(
				"A value that is between " + min + " and " + max + " was expected, but " + value + " was received."
			);
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this long mediator is not bigger than the given value.
	 * 
	 * @param value
	 */
	public void isBiggerThan(final long value) {
		if (this.value <= value) {
			addCurrentTestCaseError(
				"A value that is bigger than " + value + " was expected, but " + this.value + " was received."
			);
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this long mediator is not bigger or does not equal the given value.
	 * 
	 * @param value
	 */
	public void isBiggerThanOrEquals(final long value) {
		if (this.value < value) {
			addCurrentTestCaseError(
				"A value that is bigger than or equals "
				+ value
				+ " was expected, but "
				+ this.value
				+ " was received."
			);
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this long mediator is not dividable by the given value.
	 * 
	 * @param value
	 * @throws NonPositiveArgumentException if the given value is not positive.
	 */
	public void isDividableBy(final long value) {
		
		//Asserts that the given value is positive.
		if (value < 1) {
			throw new NonPositiveArgumentException(LowerCaseCatalogue.VALUE, value);
		}
		
		if (this.value % value != 0) {
			addCurrentTestCaseError(
				"A value that is dividable by " + value + " was expected, but " + this.value + " was received."
			);
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this long mediator does not equal the given value.
	 * 
	 * @param value
	 */
	public void isEqualTo(final int value) {
		if (this.value != value) {
			addCurrentTestCaseError(value + " was expected, but " + this.value + " was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this long mediator does not equal the given value.
	 * 
	 * @param value
	 */
	public void isEqualTo(final long value) {
		if (this.value != value) {
			addCurrentTestCaseError(value + " was expected, but " + this.value + " was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this long mediator is not even.
	 */
	public void isEven() {
		if (value % 2 != 0) {
			addCurrentTestCaseError("An even value was expected, but " + value + " was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this long mediator is not negative.
	 */
	public void isNegative() {
		if (value >= 0) {
			addCurrentTestCaseError("A negative value was expected, but " + value + " was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this long mediator is between the given min and max.
	 * 
	 * @param min
	 * @param max
	 * @throws NonBiggerArgumentException if the given max is not bigger than the given min.
	 */
	public void isNotBetween(final long min, final long max) {
		
		//Asserts that the given max is bigger than the given min.
		if (max <= min) {
			throw new NonBiggerArgumentException("max", max, min);
		}
		
		if (value >= min && value <= max) {
			addCurrentTestCaseError(
				"A value that is not between " + min + " and " + max + " was expected, but " + value + " was received."
			);
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this long mediator equals the given value.
	 * 
	 * @param value
	 */
	public void isNotEqualTo(final long value) {
		if (this.value == value) {
			addCurrentTestCaseError(
				"A value that does not equal " + value + " was expected, but " + this.value + " was received."
			);
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this long mediator is negative.
	 */
	public void isNotNegative() {
		if (value < 0) {
			addCurrentTestCaseError("A value that is not negative was expected, but " + value + " was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this long mediator is positive.
	 */
	public void isNotPositive() {
		if (value > 0) {
			addCurrentTestCaseError("A value that is not positive was expected, but " + value + " was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this long mediator is 0.0.
	 */
	public void isNotZero() {
		isNotEqualTo(0);
	}
	
	//method
	/**
	 * Generates an error if the value of this long mediator is not odd.
	 */
	public void isOdd() {
		if (value % 2 == 0) {
			addCurrentTestCaseError("An odd value was expected, but " + value + " was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this long mediator is not positive.
	 */
	public void isPositive() {
		if (value <= 0) {
			addCurrentTestCaseError("A positive value was expected, but " + value + " was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this long mediator is not smaller than the given value.
	 * 
	 * @param value
	 */
	public void isSmallerThan(final long value) {
		if (this.value >= value) {
			addCurrentTestCaseError(
				"A value that is smaller than " + value + " was expected, but " + this.value + " was received."
			);
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this long mediator is not smaller than or does not equal the given value.
	 * 
	 * @param value
	 */
	public void isSmallerThanOrEquals(final long value) {
		if (this.value > value) {
			addCurrentTestCaseError(
				"A value that is smaller than or equals "
				+ value
				+ " was expected, but "
				+ this.value
				+ " was received."
			);
		}
	}
}
