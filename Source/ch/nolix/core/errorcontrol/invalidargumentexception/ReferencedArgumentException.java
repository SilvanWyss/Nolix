//package declaration
package ch.nolix.core.errorcontrol.invalidargumentexception;

//class
/**
 * A {@link ReferencedArgumentException} is a {@link InvalidArgumentException}
 * that is supposed to be thrown when a given argument is undesirably
 * referenced.
 * 
 * @author Silvan Wyss
 * @date 2021-07-16
 */
@SuppressWarnings("serial")
public final class ReferencedArgumentException extends InvalidArgumentException {

  //constant
  private static final String ERROR_PREDICATE = "is referenced";

  //constructor
  /**
   * Creates a new {@link ReferencedArgumentException} for the given argument.
   * 
   * @param argument
   */
  private ReferencedArgumentException(final Object argument) {

    //Calls constructor of the base class.
    super(argument, ERROR_PREDICATE);
  }

  //static method
  /**
   * @param argument
   * @return a new {@link ReferencedArgumentException} for the given argument.
   */
  public static ReferencedArgumentException forArgument(final Object argument) {
    return new ReferencedArgumentException(argument);
  }
}
