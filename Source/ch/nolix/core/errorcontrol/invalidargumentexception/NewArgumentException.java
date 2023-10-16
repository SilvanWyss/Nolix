//package declaration
package ch.nolix.core.errorcontrol.invalidargumentexception;

//class
/**
 * A {@link NewArgumentException} is a {@link InvalidArgumentException} that is
 * supposed to be thrown when a given argument is undesirably not new.
 * 
 * @author Silvan Wyss
 * @date 2021-07-16
 */
@SuppressWarnings("serial")
public final class NewArgumentException extends InvalidArgumentException {

  //constant
  private static final String ERROR_PREDICATE = "is new";

  //static method
  /**
   * @param argument
   * @return a new {@link NewArgumentException} for the given argument.
   */
  public static NewArgumentException forArgument(final Object argument) {
    return new NewArgumentException(argument);
  }

  //constructor
  /**
   * Creates a new {@link NewArgumentException} for the given argument.
   * 
   * @param argument
   */
  private NewArgumentException(final Object argument) {

    //Calls constructor of the base class.
    super(argument, ERROR_PREDICATE);
  }
}
