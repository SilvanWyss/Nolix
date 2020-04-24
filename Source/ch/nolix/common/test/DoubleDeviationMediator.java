//package declaration
package ch.nolix.common.test;

import ch.nolix.common.invalidArgumentException.NegativeArgumentException;
import ch.nolix.common.skillAPI.ApproximativeEqualing;

//class
/**
 * A double deviation mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 120
 */
public final class DoubleDeviationMediator extends Mediator {

	//attributes
	private final double value;
	private final double maxDeviation;
	
	//constructor
	/**
	 * Creates a new double deviation mediator
	 * that belongs to the given test
	 * and is for the given value
	 * and has a default max deviation.
	 * 
	 * @param test
	 * @param value
	 * @throws ArgumentIsNullException if the given test is null.
	 */
	DoubleDeviationMediator(final Test test, final double value) {
		
		//Calls other constructor.
		this(test, value, ApproximativeEqualing.DEFAULT_MAX_DEVIATION);
	}
	
	//constructor
	/**
	 * Creates a new double deviation mediator
	 * that belongs to the given test
	 * and is for the given value
	 * and has the given default max deviation.
	 * 
	 * @param test
	 * @param value
	 * @throws ArgumentIsNullException if the given test is null.
	 * @throws NegativeArgumentException if the given max deviation is negative.
	 */
	DoubleDeviationMediator(
		final Test test,
		final double value,
		final double maxDeviation
	) {
		//Calls constructor of the base class.
		super(test);
		
		//Asserts that the given max deviation is not negative.
		if (maxDeviation < 0) {
			throw new NegativeArgumentException("max deviation", maxDeviation);
		}
		
		//Sets the value of this double deviation mediator.
		this.value = value;
		
		//Sets the max deviatio nof this double deviation mediator.
		this.maxDeviation = maxDeviation;
	}
	
	//method
	/**
	 * Generates an error if the value of this double deviation mediator
	 * does not equal the given value with a deviation that is not bigger than the max deviation of this double deviation mediator.
	 *
	 * @param value
	 */
	public void isEqualTo(final double value) {
		
		//Asserts that the value of this double deviation mediator equals the given value with a devation that is not bigger than the max deviation of this double deviation mediator.
		if (Math.abs(this.value - value) > maxDeviation) {
			addCurrentTestCaseError(this.value + "±" + maxDeviation + " was expected, but " + value + " was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this double deviation mediator
	 * equals the given value with a deviation that is not bigger than the max deviation of this double deviation mediator.
	 *
	 * @param value
	 */
	public void isNotEqualTo(final double value) {
		
		//Asserts that the value of this double deviation mediator equals the given value with a devation that is not bigger than the max deviation of this double deviation mediator.
		if (Math.abs(this.value - value) <= maxDeviation) {
			addCurrentTestCaseError("A value that does not equal " + value + "±" + maxDeviation + " was expected, but " + this.value + " was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this double deviation mediator
	 * equals 0.0 with a deviation that is not bigger than the max deviation of this double deviation mediator.
	 */
	public void isNotZero() {
		
		//Asserts that the value of this double deviation mediator does not equal 0.0 with a deviation that is not bigger than the max deviation of this double deviation mediator.
		isNotEqualTo(0.0);
	}
	
	//method
	/**
	 * Generates an error if the value of this double deviation mediator
	 * does not equal 0.0 with a deviation that is not bigger than the max deviation of this double deviation mediator.
	 */
	public void isZero() {
		
		//Asserts that the value of this double deviation mediator equals 0.0 with a deviation that is not bigger than the max deviation of this double deviation mediator.
		isEqualTo(0.0);
	}
}
