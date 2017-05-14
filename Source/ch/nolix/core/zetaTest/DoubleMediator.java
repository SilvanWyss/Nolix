//package declaration
package ch.nolix.core.zetaTest;

//own imports
import ch.nolix.core.functional.IElementTakerBooleanGetter;
import ch.nolix.core.test.Accessor;
import ch.nolix.core.zetaValidator.ZetaValidator;

//class
/**
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 220
 */
public final class DoubleMediator extends Mediator {

	//attribute
	private final double value;
	
	//packave-visible constructor
	/**
	 * Creates new double mediator that belongs to the given zeta test and has the given value.
	 * 
	 * @param value
	 * @throws NullArgumentException if the given zeta test is null.
	 */
	DoubleMediator(final ZetaTest zetaTest, final double value) {
		
		//Calls constructor of the base class.
		super(zetaTest);

		this.value = value;
	}
	
	//method
	/**
	 * Generates an error if the value of this double mediator does not fulfil the given condition.
	 * 
	 * @param condition
	 * @return a new double conjunction mediator that belongs to the zeta test this double mediator belongs to and that has the value of this double mediator.
	 * @throws NullArgumentException if the given condition is null.
	 */
	public final DoubleConjunctionMediator fulfils(final IElementTakerBooleanGetter<Double> condition) {
		
		//Checks if the given condition is not null.
		ZetaValidator.supposeThat(condition).thatIsNamed("condition").isNotNull();
		
		if (!condition.getOutput(value)) {
			new Accessor(getZetaTest()).addCurrentTestMethodError("A value that fulfils the given condition was expected, but " + value + " was received.");
		}
		
		return new DoubleConjunctionMediator(getZetaTest(), value);
	}
	
	//method
	/**
	 * Generates an error if the value of this double mediator is not between the given min and max.
	 * 
	 * @param min
	 * @param max
	 * @return a new double conjunction mediator that belongs to the zeta test this double mediator belongs to and that has the value of this double mediator.
	 * @throws RuntimeException if the given min is bigger than the given max.
	 */
	public final DoubleConjunctionMediator isBetween(final double min, final double max) {
		
		ZetaValidator.supposeThat(max).thatIsNamed("max").isBiggerThanOrEquals(min);
		if (min > max) {
			new Accessor(getZetaTest()).addCurrentTestMethodError("A value cannot not be between " + min + " and " + max + ".");
		}
		
		if (value < min || value > max) {
			new Accessor(getZetaTest()).addCurrentTestMethodError("A value that is between " + min + " and " + max + " was expected, but " + value + " was received.");
		}
		
		return new DoubleConjunctionMediator(getZetaTest(), value);
	}
	
	//method
	/**
	 * @param value
	 * @throws Error if the value of this double mediator is not bigger than the given value
	 */
	public final void isBiggerThan(final double value) {
		if (this.value <= value) {
			new Accessor(getZetaTest()).addCurrentTestMethodError("A value that is bigger than " + value + " was expected, but " + this.value + " was received.");
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws Error if the value of this double mediator is not bigger than or equal to the given value
	 */
	public final void isBiggerThanOrEqual(final double value) {
		if (this.value < value) {
			new Accessor(getZetaTest()).addCurrentTestMethodError("A value that is bigger than or equal to " + value + " was expected, but " + this.value + " was received.");
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws Error if the value of this double mediator is not equal to the given value
	 */
	public final void equals(final double value) {
		if (this.value != value) {
			new Accessor(getZetaTest()).addCurrentTestMethodError(value + " was expected, but " + this.value + " was received.");
		}
	}
	
	//method
	/**
	 * @throws Error if the value of this double mediator is not negative
	 */
	public final void isNegative() {
		if (value >= 0.0) {
			new Accessor(getZetaTest()).addCurrentTestMethodError("A negative value was expected, but " + value + " was received.");
		}
	}
	
	//method
	/**
	 * @param min
	 * @param max
	 * @throws Exception if the given min is bigger than the given max
	 * @throws Error if the value of this double mediator is between the given min and max
	 */
	public final void isNotBetween(final double min, final double max) {
		
		if (min > max) {
			throw new RuntimeException("A value cannot not be between " + min + " and " + max + ".");
		}
		
		if (value >= min && value <= max) {
			new Accessor(getZetaTest()).addCurrentTestMethodError("A value that is not between " + min + " and " + max + " was expected, but " + value + " was received.");
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws Error if the value of this double mediator is equal to the given value
	 */
	public final void equalsNot(final double value) {
		if (this.value == value) {
			new Accessor(getZetaTest()).addCurrentTestMethodError("A value that is not " + value + " was expected, but " + this.value + " was received.");
		}
	}
	
	//method
	/**
	 * @throws Error if the value of this double mediator is negative
	 */
	public final void isNotNegative() {
		if (value < 0.0) {
			new Accessor(getZetaTest()).addCurrentTestMethodError("A value that is not negative was expected, but " + value + " was received.");
		}
	}

	//method
	/**
	 * @throws Error if the value of this double mediator is 1.0
	 */
	public final void isNotOne() {
		equalsNot(1.0);
	}
	
	//method
	/**
	 * @throws Error if the value of this double mediator is positive
	 */
	public final void isNotPositive() {
		if (value > 0.0) {
			new Accessor(getZetaTest()).addCurrentTestMethodError("A value that is not positive was expected, but " + value + " was received.");
		}
	}
	
	//method
	/**
	 * @throws Error if the value of this double mediator is 0.0
	 */
	public final void isNotZero() {
		equalsNot(0.0);
	}
	
	//method
	/**
	 * @throws Error if the value of this double mediator is not 1.0
	 */
	public final void isOne() {
		equals(1.0);
	}

	//method
	/**
	 * @throws Error if the value of this double mediator is not positive
	 */
	public final void isPositive() {
		if (value <= 0.0) {
			new Accessor(getZetaTest()).addCurrentTestMethodError("A positive value was expected, but " + value + " was received.");
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws Exception if the value of this double mediator is not smaller than the given value
	 */
	public final void isSmallerThan(final double value) {
		if (this.value >= value) {
			new Accessor(getZetaTest()).addCurrentTestMethodError("A value that is smaller than " + value + " was expected, but " + this.value + " was received.");
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws Exception if the value of this doule mediator is not smaller than or equal to the given value
	 */
	public final void isSmallerThanOrEqualTo(final double value) {
		if (this.value > value) {
			new Accessor(getZetaTest()).addCurrentTestMethodError("A value that is smaller than or equal to  " + value + " was expected, but " + this.value + " was received.");
		}
	}
	
	//method
	/**
	 * @throws Error if the value of this double mediator is not zero
	 */
	public final void isZero() {
		equals(0.0);
	}
	
	//method
	/**
	 * @return new deviation mediator that belongs to this nolix test with a default max deviation
	 */
	public final DoubleDeviationMediator withDefaultMaxDeviation() {
		return new DoubleDeviationMediator(getZetaTest(), value);
	}
	
	//method
	/**
	 * @param maxDeviation
	 * @return new deviation mediator that belongs to this test and has the given max deviation
	 * @throws Exception if the given max deviation is negative
	 */
	public final DoubleDeviationMediator withMaxDeviation(final double maxDeviation) {
		return new DoubleDeviationMediator(getZetaTest(), value, maxDeviation);
	}
}
