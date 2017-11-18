//package declaration
package ch.nolix.core.test2;

//own imports
import ch.nolix.core.interfaces.ApproximativeEqualing;
import ch.nolix.core.testoid.TestAccessor;
import ch.nolix.core.validator2.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 80
 */
public final class DoubleDeviationMediator extends Mediator {

	//attributes
	private final double value;
	private final double maxDeviation;
	
	//package-visible constructor
	/**
	 * Creates new double deviation mediator that belongs to the given zeta test and has the given value and a default max deviation.
	 * 
	 * @param test
	 * @param value
	 * @throws NullArgumentException if the given zeta test is null.
	 */
	DoubleDeviationMediator(final Test test, final double value) {
		
		//Calls other constructor.
		this(test, value, ApproximativeEqualing.DEFAULT_MAX_DEVIATION);
	}
	
	//package-visible constructor
	/**
	 * Creates new double deviation mediator that belongs to the given zeta test and has the given value and max deviation.
	 * 
	 * @param test
	 * @param value
	 * @param maxDeviation
	 * @throws NullArgumentException if the given zeta test is null.
	 * @throws NegativeArgumentException if the given max deviation is negative.
	 */
	DoubleDeviationMediator(
		final Test test,
		final double value,
		final double maxDeviation
	) {
		//Calls constructor of the base class.
		super(test);
		
		//Checks if the given max deviation is not negative.
		Validator.suppose(maxDeviation).thatIsNamed("max deviation").isNotNegative();
		
		this.value = value;
		this.maxDeviation = maxDeviation;
	}
	
	//method
	/**
	 * Generates an error if the value of this double deviation mediator equals the given value with a deviation that is not bigger than the max deviation of this double deviation mediator.
	 *
	 * @param value
	 */
	public final void equals(final double value) {
		
		//Checks if the value of this double deviation mediator equals the given value with a devation that is not bigger than the max deviation of this double deviation mediator.
		if (Math.abs(this.value - value) > maxDeviation) {
			new TestAccessor(getTest()).addCurrentTestMethodError(this.value + "±" + maxDeviation + " was expected, but " + value + " was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this double deviation mediator is not 0.0 with a deviation that is not bigger than the max deviation of this double deviation mediator.
	 */
	public final void isZero() {
		
		//Checks if the value of this double deviation mediator is 0.0 with a devation that is not bigger than the max deviation of this double deviation mediator.
		equals(0.0);
	}
}
