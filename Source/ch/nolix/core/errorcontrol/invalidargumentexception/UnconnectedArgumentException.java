//package declaration
package ch.nolix.core.errorcontrol.invalidargumentexception;

//class
/**
 * A {@link UnconnectedArgumentException} is a {@link InvalidArgumentException}
 * that is supposed to be thrown when a given argument is undesirable not
 * connected.
 * 
 * @author Silvan Wyss
 * @date 2020-07-24
 */
@SuppressWarnings("serial")
public final class UnconnectedArgumentException extends InvalidArgumentException {

  //constant
  private static final String ERROR_PREDICATE = "is not connected";

  //constructor
  /**
   * Creates a new {@link UnconnectedArgumentException} for the given argument.
   * 
   * @param argument
   */
  private UnconnectedArgumentException(final Object argument) {

    //Calls constructor of the base class.
    super(argument, ERROR_PREDICATE);
  }

  //constructor
  /**
   * @param argument
   * @return a new {@link UnconnectedArgumentException} for the given argument.
   */
  public static UnconnectedArgumentException forArgument(final Object argument) {
    return new UnconnectedArgumentException(argument);
  }
}
