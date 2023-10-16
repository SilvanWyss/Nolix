//package declaration
package ch.nolix.core.errorcontrol.validator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnequalArgumentException;

//class
/**
 * @author Silvan Wyss
 * @date 2017-01-01
 */
public final class DoubleDeviationMediator extends Mediator {

  //attribute
  private final double argument;

  //attribute
  private final double maxDeviation;

  //constructor
  /**
   * Creates a new named double deviation mediator with the given argument name,
   * argument and max deviation.
   * 
   * @param argumentName
   * @param argument
   * @param maxDeviation
   * @throws ArgumentIsNullException   if the given argument name is null.
   * @throws EmptyArgumentException    if the given argument name is empty.
   * @throws NegativeArgumentException if the given max deviation is negative.
   */
  DoubleDeviationMediator(
      final String argumentName,
      final Double argument,
      final double maxDeviation) {

    //Calls constructor of the base class.
    super(argumentName);

    //Asserts that the given max deviation is not negative.
    if (maxDeviation < 0.0) {
      throw NegativeArgumentException.forArgumentNameAndArgument("max deviation", maxDeviation);
    }

    this.argument = argument;
    this.maxDeviation = maxDeviation;
  }

  //method
  /**
   * @param value
   * @throws UnequalArgumentException if the argument of this named double
   *                                  deviation mediator does not equal the given
   *                                  value with a deviation that is not bigger
   *                                  than the max deviation of this named double
   *                                  deviation mediator.
   */
  public void isEqualTo(final double value) {

    /*
     * Asserts that the argument of this named double deviation mediator equals the
     * given value with a deviation that is not bigger than the max deviation of
     * this named double deviation mediator.
     */
    if (Math.abs(value - argument) > maxDeviation) {
      throw UnequalArgumentException.forArgumentNameAndArgumentAndValue(getArgumentName(), value, argument);
    }
  }
}
