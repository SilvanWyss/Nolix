//package declaration
package ch.nolix.common.testing.test;

import ch.nolix.common.environment.nolixenvironment.NolixEnvironment;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.common.requestapi.ApproximativeEqualing;

//class
/**
 * An approximative equaling mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2017-01-01
 * @lines 60
 */
public final class ApproximativeEqualingMediator extends ValueMediator<ApproximativeEqualing> {
	
	//constructor
	/**
	 * Creates a new approximative equaling mediator
	 * that belongs to the given test and is for the given value.
	 * 
	 * @param test
	 * @param value
	 * @throws ArgumentIsNullException if the given test is null.
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
		return
		new ApproximativeEqualingDeviationMediator(
			getRefTest(),
			getRefValue(),
			NolixEnvironment.DEFAULT_MAX_DEVIATION
		);
	}
	
	//method
	/**
	 * @param maxDeviation
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
