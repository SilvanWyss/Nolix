//package declaration
package ch.nolix.core.testing.validation;

//own imports
import ch.nolix.core.environment.nolixenvironment.NolixEnvironment;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.functionapi.IElementTaker;
import ch.nolix.core.requestapi.ApproximativeEqualing;

//class
/**
 * An approximative equaling mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2017-01-01
 */
public final class ApproximativeEqualingMediator extends ValueMediator<ApproximativeEqualing> {
	
	//constructor
	/**
	 * Creates a new approximative equaling mediator
	 * that belongs to the given test and is for the given value.
	 * 
	 * @param expectationErrorTaker
	 * @param value
	 * @throws ArgumentIsNullException if the given test is null.
	 */
	public ApproximativeEqualingMediator(
		final IElementTaker<String> expectationErrorTaker,
		final ApproximativeEqualing value
	) {
		//Calls constructor of the base class.
		super(expectationErrorTaker, value);
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
			getRefExpectationErrorTaker(),
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
			getRefExpectationErrorTaker(),
			getRefValue(),
			maxDeviation
		);
	}
}
