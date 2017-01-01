/*
 * file:	LongMediator.java
 * author:	Silvan Wyss
 * month:	2016-08
 * lines:	340
 */

//package declaration
package ch.nolix.common.zetaTest;

//own import
import ch.nolix.common.functional.IElementTakerBooleanGetter;
import ch.nolix.common.test.Test;

//class
public class LongMediator {

	//attribute
	private final Test test;
	private final long value;
	
	//package-visible constructor
	/**
	 * Creates new long mediator with the given value.
	 * 
	 * @param value
	 */
	LongMediator(final Test test, final long value) {
		this.test = test;
		this.value = value;
	}
	
	//method
	/**
	 * @param condition
	 * @return new long conjunction mediator with the value of this long mediator
	 * @throws Exception if the given condition is null
	 * @throws Error if the value of this long mediator does not fulfill the given condition
	 */
	public LongConjunctionMediator fulfils(IElementTakerBooleanGetter<Long> condition) {
		
		if (condition == null) {
			throw new RuntimeException("The given condition is null.");
		}
		
		if (!condition.getOutput(value)) {
			test.addCurrentTestMethodError("A value that fulfils the given condition was expected, but " + value + " was received.");
		}
		
		return new LongConjunctionMediator(test, value);
	}
	
	//method
	/**
	 * @param min
	 * @param max
	 * @return new long conjunction mediator with the value of this long mediator
	 * @throws Exception if the given min is bigger than the given max
	 * @throws Error if the value of this long mediator is not between the given min and max
	 */
	public LongConjunctionMediator isBetween(long min, long max) {
		
		if (min > max) {
			throw new RuntimeException("A value cannot not be between " + min + " and " + max + ".");
		}
		
		if (value < min || value > max) {
			test.addCurrentTestMethodError("A value that is between " + min + " and " + max + " was expected, but " + value + " was received.");
		}
		
		return new LongConjunctionMediator(test, value);
	}
	
	//method
	/**
	 * @param value
	 * @return new long conjunction mediator with the value of this long mediator
	 * @throws Error if the value of this long mediator is not bigger than the given value
	 */
	public LongConjunctionMediator isBiggerThan(final long value) {
		
		if (this.value <= value) {
			test.addCurrentTestMethodError("A value bigger than " + value + " was expected, but " + this.value + " was received.");
		}
		
		return new LongConjunctionMediator(test, this.value);
	}
	
	//method
	/**
	 * Generates an error if the value of this long mediator is not dividable by the given value.
	 * 
	 * @param value
	 * @return new long conjunction mediator with the value of this long mediator
	 * @throws Error if the given value is not positive
	 */
	public LongConjunctionMediator isDividableBy(final long value) {
		
		//Checks the given value.
		if (value < 1) {
			throw new RuntimeException("The given value is not positive.");
		}
		
		if (this.value % value != 0) {
			test.addCurrentTestMethodError("A value that is dividable by " + value + " was expected, but " + this.value + " was received.");
		}
		
		return new LongConjunctionMediator(test, this.value);
	}
	
	//method
	/**
	 * @param value
	 * @throws Error if the value of this long mediator is not equal to the given value
	 */
	public void equals(final int value) {		
		if (this.value != value) {
			test.addCurrentTestMethodError(value + " was expected, but " + this.value + " was received.");
		}
	}
	
	//method
	/**
	 * @return new long conjunction mediator with the value of this long mediator
	 * @throws Error if the value of this long mediator is not even
	 */
	public LongConjunctionMediator isEven() {
		
		if (value % 2 != 0) {
			test.addCurrentTestMethodError("An even value was expected, but " + value + " was received.");
		}
		
		return new LongConjunctionMediator(test, this.value);
	}
	
	//method
	/**
	 * @return new long conjunction mediator with the value of this long mediator
	 * @throws Error if the value of this long mediator is not negative
	 */	
	public LongConjunctionMediator isNegative() {
		
		if (value > -1) {
			test.addCurrentTestMethodError("A negative value was expected, but " + value + " was received.");
		}
		
		return new LongConjunctionMediator(test, this.value);
	}
	
	//method
	/**
	 * @param min
	 * @param max
	 * @return new long conjunction mediator with the value of this long mediator
	 * @throws Exception if the given min is bigger than the given max
	 * @throws Error if the value of this long mediator is between the given min and max
	 */
	public LongConjunctionMediator isNotBetween(final long min, final long max) {
		
		if (min > max) {
			throw new RuntimeException("A value cannot not be between " + min + " and " + max + ".");
		}
		
		if (value >= min && value <= max) {
			test.addCurrentTestMethodError("A value that is not between " + min + " and " + max + " was expected, but " + value + " was received.");
		}
		
		return new LongConjunctionMediator(test, this.value);
	}
	
	//method
	/**
	 * @param value
	 * @return new long conjunction mediator with the value of this long mediator
	 * @throws Error if the value of this long mediator is equal to the given value
	 */
	public LongConjunctionMediator equalsNot(final int value) {
		
		if (this.value == value) {
			test.addCurrentTestMethodError("A value that is not " + value + " was expected, but " + this.value + " was received.");
		}
		
		return new LongConjunctionMediator(test, this.value);
	}
	
	//method
	/**
	 * @return new long conjunction mediator with the value of this long mediator
	 * @throws Error if the value of this long mediator is negative
	 */
	public LongConjunctionMediator isNotNegative() {
		
		if (value < 0) {
			test.addCurrentTestMethodError("A value that is not negative was expected, but " + value + " was received.");
		}
		
		return new LongConjunctionMediator(test, this.value);
	}
	
	//method
	/**
	 * @return new long conjunction mediator with the value of this long mediator
	 * @throws Error if the value of this long mediator is not 1
	 */
	public LongConjunctionMediator isNotOne() {
		
		equalsNot(1);
		
		return new LongConjunctionMediator(test, this.value);
	}
	
	//method
	/**
	 * @return new long conjunction mediator with the value of this long mediator
	 * @throws Error if the value of this long mediator is positive
	 */
	public LongConjunctionMediator isNotPositive() {
		
		if (value > 0) {
			test.addCurrentTestMethodError("A value that is not positive was expected, but " + value + " was received.");
		}
		
		return new LongConjunctionMediator(test, this.value);
	}
	
	//method
	/**
	 * @return new long conjunction mediator with the value of this long mediator
	 * @throws Error if the value of this long mediator is prime
	 */
	public LongConjunctionMediator isNotPrime() {
		
		if (valueIsPrime()) {
			test.addCurrentTestMethodError("A value that is not prime was expected, but " + value + " was received.");
		}
		
		return new LongConjunctionMediator(test, this.value);
	}
	
	//method
	/**
	 * @returns new long conjunction mediator with the value of this long mediator
	 * @throws Error if the value of this long mediator is not 0
	 */
	public LongConjunctionMediator isNotZero() {
		
		equalsNot(0);
		
		return new LongConjunctionMediator(test, this.value);
	}
	
	//method
	/**
	 * @returns new long conjunction mediator with the value of this long mediator
	 * @throws Error if the value of this long mediator is not odd
	 */
	public LongConjunctionMediator isOdd() {
		
		if (value % 2 == 0) {
			test.addCurrentTestMethodError("An odd value was expected, but " + value + " was received.");
		}
		
		return new LongConjunctionMediator(test, this.value);
	}
	
	//method
	/**
	 * @returns new long conjunction mediator with the value of this long mediator
	 * @throws Error if hte value of this expect that in mediator is not 1
	 */
	public LongConjunctionMediator isOne() {
		
		equals(1);
		
		return new LongConjunctionMediator(test, this.value);
	}
	
	//method
	/**
	 * @returns new long conjunction mediator with the value of this long mediator
	 * @throws Error if the value of this long mediator is not positive
	 */
	public LongConjunctionMediator isPositive() {
		
		if (value < 1) {
			test.addCurrentTestMethodError("A positive value was expected, but " + value + " was received.");
		}
		
		return new LongConjunctionMediator(test, this.value);
	}
	
	//method
	/**
	 * @returns new long conjunction mediator with the value of this long mediator
	 * @throws Error of the value of this long mediator is not prime
	 */
	public LongConjunctionMediator isPrime() {
		
		if (!valueIsPrime()) {
			test.addCurrentTestMethodError("A prime value was expected, but " + value + " was received.");
		}
		
		return new LongConjunctionMediator(test, this.value);
	}
	
	//method
	/**
	 * @param value
	 * @returns new long conjunction mediator with the value of this long mediator
	 * @throws Error if the value of this long mediator is not smaller than the given value
	 */
	public LongConjunctionMediator isSmallerThan(final long value) {
		
		if (this.value >= value) {
			test.addCurrentTestMethodError("A value smaller than " + value + " was expected, but " + this.value + " was received.");
		}
		
		return new LongConjunctionMediator(test, this.value);
	}
	
	//method
	/**
	 * @returns new long conjunction mediator with the value of this long mediator
	 * @throws Error if the value of this long mediator is not 0
	 */
	public LongConjunctionMediator isZero() {
		
		equals(0);
		
		return new LongConjunctionMediator(test, this.value);
	}
	
	//method
	/**
	 * @return true if the value of this long mediator is prime
	 */
	private final boolean valueIsPrime() {
		
		if (value < 2) {
			return false;
		}
		
		for (int d = 3; d < value / 2; d += 2) {
			if (value % d == 0) {
				return true;
			}
		}
		
		return false;
	}
}
