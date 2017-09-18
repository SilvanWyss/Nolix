//package declaration
package ch.nolix.core.test2;

import ch.nolix.core.baseTest.TestAccessor;
//own imports
import ch.nolix.core.interfaces.ApproximativeEqualing;
import ch.nolix.core.validator2.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 80
 */
public final class ApproximativeEqualingDeviationMediator extends Mediator {

	//attributes
	private final ApproximativeEqualing value;
	private final double maxDeviation;
	
	//package-visible constructor
	/**
	 * Creates new approximative equaling deviation mediator with the given zeta test, value and default max deviation.
	 * 
	 * @param test
	 * @param value
	 * @throws NullArgumentException if the given zeta test is null.
	 */
	public ApproximativeEqualingDeviationMediator(
		final Test test,
		final ApproximativeEqualing value
	) {
		//Calls other constructor.
		this(test, value, ApproximativeEqualing.DEFAULT_MAX_DEVIATION);
	}
	
	//package-visible constructor
	/**
	 * Creates new approximative equaling deviation mediator with the given zeta test, value and max deviation.
	 * 
	 * @param test
	 * @param value
	 * @param maxDeviation
	 * @throws NullArgumentException if the given zeta test is null.
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
		Validator.supposeThat(maxDeviation).thatIsNamed("max deviation").isNotNegative();
		
		this.value = value;
		this.maxDeviation = maxDeviation;
	}
	
	//method
	/**
	 * @param value
	 * @throws Error if the value of this deviation object mediator is not equal to the given value with a deviation that is not bigger than the max deviation of this deviation object mediator
	 */
	public final void equals(ApproximativeEqualing value) {
		
		if (this.value != null && value == null) {
			new TestAccessor(getZetaTest()).addCurrentTestMethodError("'" + value + "'±" + maxDeviation + " was expected, but null was received.");
		}
		
		if (this.value == null && value != null) {
			new TestAccessor(getZetaTest()).addCurrentTestMethodError("Null was expected, but '" + this.value + "' was received.");
		}
		
		if (!this.value.equalsApproximatively(value, maxDeviation)) {
			new TestAccessor(getZetaTest()).addCurrentTestMethodError("'" + value + "'±" + maxDeviation + " was expected, but " + this.value + " was received.");
		}
	}
}
