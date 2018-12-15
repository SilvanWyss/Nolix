//package declaration
package ch.nolix.core.test2;

//own imports
import ch.nolix.core.skillAPI.ApproximativeEqualing;

//class
/**
 * An approximative equaling mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 60
 */
public final class ApproximativeEqualingMediator extends ValueMediator<ApproximativeEqualing> {
	
	//package-visible constructor
	/**
	 * Creates a new approximative equaling mediator
	 * that belongs to the given test and is for the given value.
	 * 
	 * @param test
	 * @param value
	 * @throws NullArgumentException if the given test is null.
	 */
	ApproximativeEqualingMediator(
		final Test test,
		final ApproximativeEqualing value
	) {
		//Calls constructor of the base class.
		super(test, value);
	}
	
	//method
	/**
	 * @return a new approximate equaling deviation mediator
	 * that belongs to the testthis approxiamative equaling mediator belongs
	 * and is for the given value
	 * and has a default max deviation.
	 */
	public ApproximativeEqualingDeviationMediator withDefaultMaxDeviation() {
		return new ApproximativeEqualingDeviationMediator(
			getRefTest(),
			getRefValue(),
			ApproximativeEqualing.DEFAULT_MAX_DEVIATION
		);
	}
	
	//method
	/**
	 * @return a new approximate equaling deviation mediator
	 * that belongs to the test of this approxiamative equaling mediator,
	 * and is for the given value
	 * and has the given max deviation.
	 * @throws NegativeArgumentException if the given max deviation is negative.
	 */
	public ApproximativeEqualingDeviationMediator withMaxDeviation(final double maxDeviation) {
		return new ApproximativeEqualingDeviationMediator(
			getRefTest(),
			getRefValue(),
			maxDeviation
		);
	}
}
