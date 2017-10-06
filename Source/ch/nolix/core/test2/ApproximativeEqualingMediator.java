//package declaration
package ch.nolix.core.test2;

//own imports
import ch.nolix.core.interfaces.ApproximativeEqualing;
import ch.nolix.core.validator2.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 60
 */
public final class ApproximativeEqualingMediator {

	//attributes
	private final Test test;
	private final ApproximativeEqualing value;
	
	//package-visible constructor
	/**
	 * Creates new approximative equaling mediator with the given zeta test and value.
	 * 
	 * @param test
	 * @param value
	 * @throws NullArgumentException if the given zeta test is null.
	 */
	public ApproximativeEqualingMediator(
		final Test test,
		final ApproximativeEqualing value
	) {
		//Checks if the given zeta test is not null.
		Validator.suppose(test).thatIsNamed("zeta test").isNotNull();
		
		this.test = test;
		this.value = value;
	}
	
	//method
	/**
	 * Generates an error if the vlaue of this approximateive equaling mediator does not equal the given value.
	 * 
	 * @param value
	 */
	public void eualsTo(final Object value) {
		new ObjectMediator(test, this.value).equalsTo(value);
	}
	
	//method
	/**
	 * @return a new approximate equaling deviation mediator with the zeta test and the value of this approximative equaling mediator.
	 */
	public ApproximativeEqualingDeviationMediator withDefaultMaxDeviation() {
		return new ApproximativeEqualingDeviationMediator(
			test,
			value,
			ApproximativeEqualing.DEFAULT_MAX_DEVIATION
		);
	}
	
	//method
	/**
	 * @param maxDeviation
	 * @return a new approximative equaling deviation mediator with the zeta test and the value of this approximatve equaling mediator and the given max deviation.
	 * @throws NegativeArgumentException if the given max deviation is negative.
	 */
	public ApproximativeEqualingDeviationMediator withMaxDeviation(final double maxDeviation) {
		return new ApproximativeEqualingDeviationMediator(
			test,
			value,
			maxDeviation
		);
	}
}
