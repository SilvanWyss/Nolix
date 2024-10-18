package ch.nolix.core.errorcontrol.invalidargumentexception;

/**
 * A {@link DeletedArgumentException} is a {@link InvalidArgumentException} that
 * is supposed to be thrown when a given argument is undesirably deleted.
 * 
 * @author Silvan Wyss
 * @version 2021-07-12
 */
@SuppressWarnings("serial")
public final class DeletedArgumentException extends InvalidArgumentException {

  private static final String ERROR_PREDICATE = "is deleted";

  /**
   * Creates a new {@link DeletedArgumentException} for the given argument.
   * 
   * @param argument
   */
  private DeletedArgumentException(final Object argument) {

    //Calls constructor of the base class.
    super(argument, ERROR_PREDICATE);
  }

  /**
   * @param argument
   * @return a new {@link DeletedArgumentException} for the given argument.
   */
  public static DeletedArgumentException forArgument(final Object argument) {
    return new DeletedArgumentException(argument);
  }
}
