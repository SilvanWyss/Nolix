/*
 * file:	DoubleMediator.java
 * author:	Silvan Wyss
 * month:	2016-08
 * lines:	220
 */

//package declaration
package ch.nolix.common.zetaTest;

//own import
import ch.nolix.common.functional.IElementTakerBooleanGetter;

//class
public final class DoubleMediator {

	//attribute
	private final double value;
	private final ZetaTest test;
	
	//packave-visible constructor
	/**
	 * Creates new double mediator with the given value.
	 * 
	 * @param value
	 */
	DoubleMediator(final ZetaTest test, final double value) {
		this.test = test;
		this.value = value;
	}
	
	//method
	/**
	 * @param condition
	 * @throws Exception if the given condition is null
	 * @throws Error if the value of this double mediator does not fulfill the given condition
	 */
	public final void fulfils(IElementTakerBooleanGetter<Double> condition) {
		
		if (condition == null) {
			throw new RuntimeException("The given condition is null.");
		}
		
		if (!condition.getOutput(value)) {
			test.addCurrentTestMethodError("A value that fulfils the given condition was expected, but " + value + " was received.");
		}
	}
	
	//method
	/**
	 * @param min
	 * @param max
	 * @throws Exception if the given min is bigger than the given max
	 * @throws Error if the value of this double mediator is not between the given min and max
	 */
	public final void isBetween(final double min, final double max) {
		
		if (min > max) {
			test.addCurrentTestMethodError("A value cannot not be between " + min + " and " + max + ".");
		}
		
		if (value < min || value > max) {
			test.addCurrentTestMethodError("A value that is between " + min + " and " + max + " was expected, but " + value + " was received.");
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws Error if the value of this double mediator is not bigger than the given value
	 */
	public final void isBiggerThan(final double value) {
		if (this.value <= value) {
			test.addCurrentTestMethodError("A value that is bigger than " + value + " was expected, but " + this.value + " was received.");
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws Error if the value of this double mediator is not bigger than or equal to the given value
	 */
	public final void isBiggerThanOrEqual(final double value) {
		if (this.value < value) {
			test.addCurrentTestMethodError("A value that is bigger than or equal to " + value + " was expected, but " + this.value + " was received.");
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws Error if the value of this double mediator is not equal to the given value
	 */
	public final void isEqualTo(final double value) {
		if (this.value != value) {
			test.addCurrentTestMethodError(value + " was expected, but " + this.value + " was expected.");
		}
	}
	
	//method
	/**
	 * @throws Error if the value of this double mediator is not negative
	 */
	public final void isNegative() {
		if (value >= 0.0) {
			test.addCurrentTestMethodError("A negative value was expected, but " + value + " was received.");
		}
	}
	
	//method
	/**
	 * @param min
	 * @param max
	 * @throws Exception if the given min is bigger than the given max
	 * @throws Error if the value of this double mediator is between the given min and max
	 */
	public final void isNotBetween(final int min, final int max) {
		
		if (min > max) {
			throw new RuntimeException("A value cannot not be between " + min + " and " + max + ".");
		}
		
		if (value >= min && value <= max) {
			test.addCurrentTestMethodError("A value that is not between " + min + " and " + max + " was expected, but " + value + " was received.");
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws Error if the value of this double mediator is equal to the given value
	 */
	public final void isNotEqualTo(final double value) {
		if (this.value == value) {
			test.addCurrentTestMethodError("A value that is not " + value + " was expected, but " + this.value + " was received.");
		}
	}
	
	//method
	/**
	 * @throws Error if the value of this double mediator is negative
	 */
	public final void isNotNegative() {
		if (value < 0.0) {
			test.addCurrentTestMethodError("A value that is not negative was expected, but " + value + " was received.");
		}
	}

	//method
	/**
	 * @throws Error if the value of this double mediator is 1.0
	 */
	public final void isNotOne() {
		isNotEqualTo(1.0);
	}
	
	//method
	/**
	 * @throws Error if the value of this double mediator is positive
	 */
	public final void isNotPositive() {
		if (value > 0.0) {
			test.addCurrentTestMethodError("A value that is not positive was expected, but " + value + " was received.");
		}
	}
	
	//method
	/**
	 * @throws Error if the value of this double mediator is 0.0
	 */
	public final void isNotZero() {
		isNotEqualTo(0.0);
	}
	
	//method
	/**
	 * @throws Error if the value of this double mediator is not 1.0
	 */
	public final void isOne() {
		isEqualTo(1.0);
	}

	//method
	/**
	 * @throws Error if the value of this double mediator is not positive
	 */
	public final void isPositive() {
		if (value <= 0.0) {
			test.addCurrentTestMethodError("A positive value was expected, but " + value + " was received.");
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws Exception if the value of this double mediator is not smaller than the given value
	 */
	public final void isSmallerThan(final double value) {
		if (this.value >= value) {
			test.addCurrentTestMethodError("A value that is smaller than " + value + " was expected, but " + this.value + " was received.");
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws Exception if the value of this doule mediator is not smaller than or equal to the given value
	 */
	public final void isSmallerThanOrEqualTo(final double value) {
		if (this.value > value) {
			test.addCurrentTestMethodError("A value that is smaller than or equal to  " + value + " was expected, but " + this.value + " was received.");
		}
	}
	
	//method
	/**
	 * @throws Error if the value of this double mediator is not zero
	 */
	public final void isZero() {
		isEqualTo(0.0);
	}
	
	//method
	/**
	 * @return new deviation mediator that belongs to this nolix test with a default max deviation
	 */
	public final DoubleDeviationMediator withDefaultMaxDeviation() {
		return new DoubleDeviationMediator(test, value, 0.5); //TODO
	}
	
	//method
	/**
	 * @param maxDeviation
	 * @return new deviation mediator that belongs to this test and has the given max deviation
	 * @throws Exception if the given max deviation is negative
	 */
	public final DoubleDeviationMediator withMaxDeviation(final double maxDeviation) {
		return new DoubleDeviationMediator(test, value, maxDeviation);
	}
}
