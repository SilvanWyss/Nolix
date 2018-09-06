//package declaration
package ch.nolix.primitive.test2;

import ch.nolix.core.skillInterfaces.ApproximativeEqualing;
import ch.nolix.primitive.invalidArgumentException.NegativeArgumentException;

//class
/**
 * An approximative equaling deviation mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 100
 */
public final class ApproximativeEqualingDeviationMediator extends Mediator {

	//attributes
	private final ApproximativeEqualing value;
	private final double maxDeviation;
	
	//package-visible constructor
	/**
	 * Creates a new approximative equaling deviation mediator
	 * that belongs to the given test and is for the given value and has a default max deviation.
	 * 
	 * @param test
	 * @param value
	 * @throws NullArgumentException if the given test is not an instance.
	 */
	ApproximativeEqualingDeviationMediator(
		final Test test,
		final ApproximativeEqualing value
	) {
		
		//Calls other constructor.
		this(test, value, ApproximativeEqualing.DEFAULT_MAX_DEVIATION);
	}
	
	//package-visible constructor
	/**
	 * Creates a new approximative equaling deviation mediator
	 * that belongs to the given test and is for the given value and has the given max deviation.
	 * 
	 * @param test
	 * @param value
	 * @param maxDeviation
	 * @throws NullArgumentException if the given test is not an instance.
	 * @throws NegativeArgumentException if the given max deviation is negative.
	 */
	ApproximativeEqualingDeviationMediator(
		final Test test,
		final ApproximativeEqualing value,
		final double maxDeviation
	) {
		
		//Calls constructor of the base class.
		super(test);
		
		//Checks if the given max deviation is not negative.
		if (maxDeviation < 0) {
			throw new NegativeArgumentException( "max deviation", maxDeviation);
		}
		
		//Sets the value of this approximative equaling deviation mediator.
		this.value = value;
		
		//Sets the max deviation of this approximative equaling devation mediator.
		this.maxDeviation = maxDeviation;
	}
	
	//method
	/**
	 * Generates an error if the value of this approximate equaling deviation mediator
	 * does not equal to the given value with a deviation that is not bigger than the max deviation of this approximative equaling deviation mediator.
	 * 
	 * @param value
	 */
	public void isEqualTo(ApproximativeEqualing value) {
		
		if (this.value == null && value != null) {
			addCurrentTestCaseError("Null was expected, but '" + this.value + "' was received.");
		}
		
		if (this.value != null && !this.value.equalsApproximatively(value, maxDeviation)) {
			addCurrentTestCaseError("'" + this.value + "'±" + maxDeviation + " was expected, but '" + value + "' was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this approximate equaling deviation mediator
	 * equals to the given value with a deviation that is not bigger than the max deviation of this approximative equaling deviation mediator.
	 * 
	 * @param value
	 */
	public void isNotEqualTo(ApproximativeEqualing value) {
		
		if (this.value == null && value == null) {
			addCurrentTestCaseError("A value was expected, but null was received.");
		}
		
		if (this.value != null && this.value.equalsApproximatively(value, maxDeviation)) {
			addCurrentTestCaseError("'A value that does not equal " + value + " with ±" + maxDeviation + " was expected, but '" + this.value + "' was received.");
		}
	}
}
