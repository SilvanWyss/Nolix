//package declaration
package ch.nolix.common.zetaTest;

//own imports
import ch.nolix.common.interfaces.ApproximativeEqualing;
import ch.nolix.common.zetaValidator.ZetaValidator;

//class
/**
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 80
 */
public final class ApproximativeEqualingDeviationMediator {

	//attributes
	private final ZetaTest zetaTestest;
	private final ApproximativeEqualing value;
	private final double maxDeviation;
	
	//package-visible constructor
	/**
	 * Creates new approximative equaling deviation mediator with the given zeta test, value and default max deviation.
	 * 
	 * @param zetaTest
	 * @param value
	 * @throws NullArgumentException if the given zeta test is null.
	 */
	public ApproximativeEqualingDeviationMediator(
		final ZetaTest zetaTest,
		final ApproximativeEqualing value
	) {
		//Calls other constructor.
		this(zetaTest, value, ApproximativeEqualing.DEFAULT_DEVIATION);
	}
	
	//package-visible constructor
	/**
	 * Creates new approximative equaling deviation mediator with the given zeta test, value and max deviation.
	 * 
	 * @param zetaTest
	 * @param value
	 * @param maxDeviation
	 * @throws NullArgumentException if the given zeta test is null.
	 * @throws NegativeArgumentException if the given max deviation is negative.
	 */
	ApproximativeEqualingDeviationMediator(
		final ZetaTest zetaTest,
		final ApproximativeEqualing value,
		final double maxDeviation
	) {
		//Checks if the given zeta test is not null.
		ZetaValidator.supposeThat(zetaTest).thatIsNamed("zeta test").isNotNull();
		
		//Checks if the given max deviation is not negative.
		ZetaValidator.supposeThat(maxDeviation).thatIsNamed("max deviation").isNotNegative();
		
		this.zetaTestest = zetaTest;
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
			zetaTestest.addCurrentTestMethodError("'" + value + "'±" + maxDeviation + " was expected, but null was received.");
		}
		
		if (this.value == null && value != null) {
			zetaTestest.addCurrentTestMethodError("Null was expected, but '" + this.value + "' was received.");
		}
		
		if (!this.value.equalsApproximatively(value, maxDeviation)) {
			zetaTestest.addCurrentTestMethodError("'" + value + "'±" + maxDeviation + " was expected, but " + this.value + " was received.");
		}
	}
}
