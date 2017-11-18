/*
 * file:	LongMediator.java
 * author:	Silvan Wyss
 * month:	2016-08
 * lines:	340
 */

//package declaration
package ch.nolix.core.test2;

//own import
import ch.nolix.core.functionInterfaces.IElementTakerBooleanGetter;
import ch.nolix.core.testoid.TestAccessor;

//class
public class LongMediator extends Mediator {

	//attribute
	private final long value;
	
	//package-visible constructor
	/**
	 * Creates new long mediator with the given value.
	 * 
	 * @param value
	 */
	LongMediator(final Test test, final long value) {
		
		//Calls constructor of the base class.
		super(test);
		
		this.value = value;
	}
	
	//method
	/**
	 * @param condition
	 * @return new long conjunction mediator with the value of this long mediator
	 * @throws Exception if the given condition is null
	 * @throws Error if the value of this long mediator does not fulfill the given condition
	 */
	public LongMediator fulfils(IElementTakerBooleanGetter<Long> condition) {
		
		if (condition == null) {
			throw new RuntimeException("The given condition is null.");
		}
		
		if (!condition.getOutput(value)) {
			new TestAccessor(getTest()).addCurrentTestMethodError("A value that fulfils the given condition was expected, but " + value + " was received.");
		}
		
		return this;
	}
	
	//method
	/**
	 * @param min
	 * @param max
	 * @return new long conjunction mediator with the value of this long mediator
	 * @throws Exception if the given min is bigger than the given max
	 * @throws Error if the value of this long mediator is not between the given min and max
	 */
	public LongMediator isBetween(long min, long max) {
		
		if (min > max) {
			throw new RuntimeException("A value cannot not be between " + min + " and " + max + ".");
		}
		
		if (value < min || value > max) {
			new TestAccessor(getTest()).addCurrentTestMethodError("A value that is between " + min + " and " + max + " was expected, but " + value + " was received.");
		}
		
		return new LongMediator(getTest(), value);
	}
	
	//method
	/**
	 * @param value
	 * @return new long conjunction mediator with the value of this long mediator
	 * @throws Error if the value of this long mediator is not bigger than the given value
	 */
	public LongMediator isBiggerThan(final long value) {
		
		if (this.value <= value) {
			new TestAccessor(getTest()).addCurrentTestMethodError("A value bigger than " + value + " was expected, but " + this.value + " was received.");
		}
		
		return this;
	}
	
	//method
	/**
	 * Generates an error if the value of this long mediator is not dividable by the given value.
	 * 
	 * @param value
	 * @return new long conjunction mediator with the value of this long mediator
	 * @throws Error if the given value is not positive
	 */
	public LongMediator isDividableBy(final long value) {
		
		//Checks the given value.
		if (value < 1) {
			throw new RuntimeException("The given value is not positive.");
		}
		
		if (this.value % value != 0) {
			new TestAccessor(getTest()).addCurrentTestMethodError("A value that is dividable by " + value + " was expected, but " + this.value + " was received.");
		}
		
		return this;
	}
	
	//method
	/**
	 * @param value
	 * @throws Error if the value of this long mediator is not equal to the given value
	 */
	public void isEqualTo(final long value) {		
		if (this.value != value) {
			new TestAccessor(getTest()).addCurrentTestMethodError(value + " was expected, but " + this.value + " was received.");
		}
	}
	
	//method
	/**
	 * @return new long conjunction mediator with the value of this long mediator
	 * @throws Error if the value of this long mediator is not even
	 */
	public LongMediator isEven() {
		
		if (value % 2 != 0) {
			new TestAccessor(getTest()).addCurrentTestMethodError("An even value was expected, but " + value + " was received.");
		}
		
		return this;
	}
	
	//method
	/**
	 * @return new long conjunction mediator with the value of this long mediator
	 * @throws Error if the value of this long mediator is not negative
	 */	
	public LongMediator isNegative() {
		
		if (value > -1) {
			new TestAccessor(getTest()).addCurrentTestMethodError("A negative value was expected, but " + value + " was received.");
		}
		
		return this;
	}
	
	//method
	/**
	 * @param min
	 * @param max
	 * @return new long conjunction mediator with the value of this long mediator
	 * @throws Exception if the given min is bigger than the given max
	 * @throws Error if the value of this long mediator is between the given min and max
	 */
	public LongMediator isNotBetween(final long min, final long max) {
		
		if (min > max) {
			throw new RuntimeException("A value cannot not be between " + min + " and " + max + ".");
		}
		
		if (value >= min && value <= max) {
			new TestAccessor(getTest()).addCurrentTestMethodError("A value that is not between " + min + " and " + max + " was expected, but " + value + " was received.");
		}
		
		return this;
	}
	
	//method
	/**
	 * @param value
	 * @return new long conjunction mediator with the value of this long mediator
	 * @throws Error if the value of this long mediator is equal to the given value
	 */
	public LongMediator equalsNot(final int value) {
		
		if (this.value == value) {
			new TestAccessor(getTest()).addCurrentTestMethodError("A value that is not " + value + " was expected, but " + this.value + " was received.");
		}
		
		return this;
	}
	
	//method
	/**
	 * @return new long conjunction mediator with the value of this long mediator
	 * @throws Error if the value of this long mediator is negative
	 */
	public LongMediator isNotNegative() {
		
		if (value < 0) {
			new TestAccessor(getTest()).addCurrentTestMethodError("A value that is not negative was expected, but " + value + " was received.");
		}
		
		return this;
	}
	
	//method
	/**
	 * @return new long conjunction mediator with the value of this long mediator
	 * @throws Error if the value of this long mediator is not 1
	 */
	public LongMediator isNotOne() {
		
		equalsNot(1);
		
		return this;
	}
	
	//method
	/**
	 * @return new long conjunction mediator with the value of this long mediator
	 * @throws Error if the value of this long mediator is positive
	 */
	public LongMediator isNotPositive() {
		
		if (value > 0) {
			new TestAccessor(getTest()).addCurrentTestMethodError("A value that is not positive was expected, but " + value + " was received.");
		}
		
		return this;
	}
	
	//method
	/**
	 * @return new long conjunction mediator with the value of this long mediator
	 * @throws Error if the value of this long mediator is prime
	 */
	public LongMediator isNotPrime() {
		
		if (valueIsPrime()) {
			new TestAccessor(getTest()).addCurrentTestMethodError("A value that is not prime was expected, but " + value + " was received.");
		}
		
		return this;
	}
	
	//method
	/**
	 * @returns new long conjunction mediator with the value of this long mediator
	 * @throws Error if the value of this long mediator is not 0
	 */
	public LongMediator isNotZero() {
		
		equalsNot(0);
		
		return this;
	}
	
	//method
	/**
	 * @returns new long conjunction mediator with the value of this long mediator
	 * @throws Error if the value of this long mediator is not odd
	 */
	public LongMediator isOdd() {
		
		if (value % 2 == 0) {
			new TestAccessor(getTest()).addCurrentTestMethodError("An odd value was expected, but " + value + " was received.");
		}
		
		return this;
	}
	
	//method
	/**
	 * @returns new long conjunction mediator with the value of this long mediator
	 * @throws Error if hte value of this expect that in mediator is not 1
	 */
	public LongMediator isOne() {
		
		isEqualTo(1);
		
		return this;
	}
	
	//method
	/**
	 * @returns new long conjunction mediator with the value of this long mediator
	 * @throws Error if the value of this long mediator is not positive
	 */
	public LongMediator isPositive() {
		
		if (value < 1) {
			new TestAccessor(getTest()).addCurrentTestMethodError("A positive value was expected, but " + value + " was received.");
		}
		
		return this;
	}
	
	//method
	/**
	 * @returns new long conjunction mediator with the value of this long mediator
	 * @throws Error of the value of this long mediator is not prime
	 */
	public LongMediator isPrime() {
		
		if (!valueIsPrime()) {
			new TestAccessor(getTest()).addCurrentTestMethodError("A prime value was expected, but " + value + " was received.");
		}
		
		return this;
	}
	
	//method
	/**
	 * @param value
	 * @returns new long conjunction mediator with the value of this long mediator
	 * @throws Error if the value of this long mediator is not smaller than the given value
	 */
	public LongMediator isSmallerThan(final long value) {
		
		if (this.value >= value) {
			new TestAccessor(getTest()).addCurrentTestMethodError("A value smaller than " + value + " was expected, but " + this.value + " was received.");
		}
		
		return this;
	}
	
	//method
	/**
	 * @returns new long conjunction mediator with the value of this long mediator
	 * @throws Error if the value of this long mediator is not 0
	 */
	public LongMediator isZero() {
		
		isEqualTo(0);
		
		return this;
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
