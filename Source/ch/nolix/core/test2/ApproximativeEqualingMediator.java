//package declaration
package ch.nolix.core.test2;

//own imports
import ch.nolix.core.interfaces.ApproximativeEqualing;
import ch.nolix.core.validator2.ZetaValidator;

//class
/**
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 60
 */
public final class ApproximativeEqualingMediator {

	//attributes
	private final ZetaTest zetaTest;
	private final ApproximativeEqualing value;
	
	//package-visible constructor
	/**
	 * Creates new approximative equaling mediator with the given zeta test and value.
	 * 
	 * @param zetaTest
	 * @param value
	 * @throws NullArgumentException if the given zeta test is null.
	 */
	public ApproximativeEqualingMediator(
		final ZetaTest zetaTest,
		final ApproximativeEqualing value
	) {
		//Checks if the given zeta test is not null.
		ZetaValidator.supposeThat(zetaTest).thatIsNamed("zeta test").isNotNull();
		
		this.zetaTest = zetaTest;
		this.value = value;
	}
	
	//method
	/**
	 * @return a new approximate equaling deviation mediator with the zeta test and the value of this approximative equaling mediator.
	 */
	public ApproximativeEqualingDeviationMediator withDefaultMaxDeviation() {
		return new ApproximativeEqualingDeviationMediator(
			zetaTest,
			value,
			ApproximativeEqualing.DEFAULT_DEVIATION
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
			zetaTest,
			value,
			maxDeviation
		);
	}
}
