//package declaration
package ch.nolix.primitive.test2;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.functionInterfaces.IElementTakerBooleanGetter;
import ch.nolix.primitive.invalidArgumentException.NonBiggerArgumentException;
import ch.nolix.primitive.invalidArgumentException.NullArgumentException;

//class
/**
 * A double mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 240
 */
public final class DoubleMediator extends Mediator {

	//attribute
	private final double value;
	
	//package-visible constructor
	/**
	 * Creates a new double mediator that belongs to the given test and is for the given value.
	 * 
	 * @param value
	 * @throws NullArgumentException if the given test is null.
	 */
	DoubleMediator(final Test test, final double value) {
		
		//Calls constructor of the base class.
		super(test);

		//Sets the value of this double mediator.
		this.value = value;
	}
	
	//method
	/**
	 * Generates an error if the value of this double mediator does not fulfil the given condition.
	 * 
	 * @param condition
	 * @throws NullArgumentException if the given condition is null.
	 */
	public void fulfils(final IElementTakerBooleanGetter<Double> condition) {
		
		//Checks if the given condition is not null.
		if (condition == null) {
			throw new NullArgumentException(VariableNameCatalogue.CONDITION);
		}
		
		if (!condition.getOutput(value)) {
			addCurrentTestCaseError("A value that fulfils the given condition was expected, but " + value + " was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this double mediator is not between the given min and max.
	 * 
	 * @param min
	 * @param max
	 * @throws NonBiggerArgumentException if the given max is not bigger than the given min.
	 */
	public void isBetween(final double min, final double max) {
		
		//Checks if the given max is bigger than the given min.
		if (max <= min) {
			throw new NonBiggerArgumentException("max", max, min);
		}
		
		if (value < min || value > max) {
			addCurrentTestCaseError("A value that is between " + min + " and " + max + " was expected, but " + value + " was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this double mediator is not bigger than the given value.
	 * 
	 * @param value
	 */
	public void isBiggerThan(final double value) {
		if (this.value <= value) {
			addCurrentTestCaseError("A value that is bigger than " + value + " was expected, but " + this.value + " was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this double mediator is not bigger or does not equal the given value.
	 * 
	 * @param value
	 */
	public void isBiggerThanOrEquals(final double value) {
		if (this.value < value) {
			addCurrentTestCaseError("A value that is bigger than or equals " + value + " was expected, but " + this.value + " was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this double mediator does not equal the given value.
	 * 
	 * @param value
	 */
	public void isEqualTo(final double value) {
		if (this.value != value) {
			addCurrentTestCaseError(value + " was expected, but " + this.value + " was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this double mediator is not negative.
	 */
	public void isNegative() {
		if (value >= 0.0) {
			addCurrentTestCaseError("A negative value was expected, but " + value + " was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this double mediator is between the given min and max.
	 * 
	 * @param min
	 * @param max
	 * @throws NonBiggerArgumentException if the given max is not bigger than the given min.
	 */
	public void isNotBetween(final double min, final double max) {
		
		//Checks if the given max is bigger than the given min.
		if (max <= min) {
			throw new NonBiggerArgumentException("max", max, min);
		}
		
		if (value >= min && value <= max) {
			addCurrentTestCaseError("A value that is not between " + min + " and " + max + " was expected, but " + value + " was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this double mediator equals the given value.
	 * 
	 * @param value
	 */
	public void isNotEqualTo(final double value) {
		if (this.value == value) {
			addCurrentTestCaseError("A value that does not equal " + value + " was expected, but " + this.value + " was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this double mediator is negative.
	 */
	public void isNotNegative() {
		if (value < 0.0) {
			addCurrentTestCaseError("A value that is not negative was expected, but " + value + " was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this double mediator is positive.
	 */
	public void isNotPositive() {
		if (value > 0.0) {
			addCurrentTestCaseError("A value that is not positive was expected, but " + value + " was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this double mediator is 0.0.
	 */
	public void isNotZero() {
		isNotEqualTo(0.0);
	}

	//method
	/**
	 * Generates an error if the value of this double mediator is not positive.
	 */
	public void isPositive() {
		if (value <= 0.0) {
			addCurrentTestCaseError("A positive value was expected, but " + value + " was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this double mediator is not smaller than the given value.
	 */
	public void isSmallerThan(final double value) {
		if (this.value >= value) {
			addCurrentTestCaseError("A value that is smaller than " + value + " was expected, but " + this.value + " was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this double mediator is not smaller than or does not equal the given value.
	 * 
	 * @param value
	 */
	public void isSmallerThanOrEquals(final double value) {
		if (this.value > value) {
			addCurrentTestCaseError("A value that is smaller than or equals " + value + " was expected, but " + this.value + " was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this double mediator is not zero.
	 */
	public void isZero() {
		isEqualTo(0.0);
	}
	
	//method
	/**
	 * @return a new double deviation mediator
	 * that belongs to the test this double mediator belongs to
	 * and is for the given value
	 * and has a default max deviation.
	 */
	public DoubleDeviationMediator withDefaultMaxDeviation() {
		return new DoubleDeviationMediator(getRefTest(), value);
	}
	
	//method
	/**
	 * @param maxDeviation
	 * @return new deviation mediator
	 * that belongs to the test this double mediator belongs to
	 * and is for the given value
	 * and has the given max deviation.
	 * @throws NegativeArgumentException if the given max deviation is negative.
	 */
	public DoubleDeviationMediator withMaxDeviation(final double maxDeviation) {
		return new DoubleDeviationMediator(getRefTest(), value, maxDeviation);
	}
}
