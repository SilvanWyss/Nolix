package ch.nolix.core.errorcontrol.invalidargumentexception;

/**
 * A {@link ReferencedArgumentException} is a {@link InvalidArgumentException}
 * that is supposed to be thrown when a given argument is undesirably
 * referenced.
 * 
 * @author Silvan Wyss
 * @version 2021-07-16
 */
@SuppressWarnings("serial")
public final class ReferencedArgumentException extends InvalidArgumentException {

  private static final String ERROR_PREDICATE = "is referenced";

  /**
   * Creates a new {@link ReferencedArgumentException} for the given argument.
   * 
   * @param argument
   */
  private ReferencedArgumentException(final Object argument) {

    //Calls constructor of the base class.
    super(argument, ERROR_PREDICATE);
  }

  /**
   * @param argument
   * @return a new {@link ReferencedArgumentException} for the given argument.
   */
  public static ReferencedArgumentException forArgument(final Object argument) {
    return new ReferencedArgumentException(argument);
  }
}
