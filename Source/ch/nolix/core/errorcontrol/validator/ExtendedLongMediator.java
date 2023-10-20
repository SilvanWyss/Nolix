//package declaration
package ch.nolix.core.errorcontrol.validator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;

//class
/**
 * An {@link ExtendedLongMediator} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2017-11-12
 */
public final class ExtendedLongMediator extends LongMediator {

  //constructor
  /**
   * Creates a new {@link ExtendedLongMediator} for the given argument.
   * 
   * @param argument
   */
  private ExtendedLongMediator(final long argument) {

    //Calls constructor of the base class.
    super(argument);
  }

  //static method
  /**
   * @param argument
   * @return a new {@link ExtendedLongMediator} for the given argument.
   */
  public static ExtendedLongMediator forArgument(final long argument) {
    return new ExtendedLongMediator(argument);
  }

  //method
  /**
   * @param argumentName
   * @return a new {@link LongMediator} for the given argumentName and the
   *         argument of the current {@link ExtendedLongMediator}.
   * @throws ArgumentIsNullException  if the given argument name is null.
   * @throws InvalidArgumentException if the given argument name is blank.
   */
  public LongMediator thatIsNamed(final String argumentName) {
    return LongMediator.forArgumentNameAndArgument(argumentName, getArgument());
  }
}
