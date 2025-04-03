package ch.nolix.core.errorcontrol.invalidargumentexception;

/**
 * A {@link ArgumentBelongsToParentException} is a
 * {@link InvalidArgumentException} that is supposed to be thrown when a given
 * argument belongs undesirably to a parent.
 * 
 * @author Silvan Wyss
 * @version 2019-10-01
 */
@SuppressWarnings("serial")
public final class ArgumentBelongsToParentException extends InvalidArgumentException {

  /**
   * Creates a new {@link ArgumentBelongsToParentException} for the given argument
   * and argumentName and parent.
   * 
   * @param argument
   * @param argumentName
   * @param parent
   * @throws IllegalArgumentException if the given argumentName is null or blank.
   * @throws IllegalArgumentException if the given parent is null.
   */
  private ArgumentBelongsToParentException(final Object argument, final String argumentName, final Object parent) {
    super(argument, argumentName, "belongs to a " + getTypeNameOfParent(parent));
  }

  /**
   * @param argument
   * @param parent
   * @return a new {@link ArgumentBelongsToParentException} for the given argument
   *         that belongs to the given parent.
   * @throws IllegalArgumentException if the given parent is null.
   */
  public static ArgumentBelongsToParentException forArgumentAndParent(final Object argument, final Object parent) {
    return new ArgumentBelongsToParentException(argument, DEFAULT_ARGUMENT_NAME, parent);
  }

  /**
   * @param parent
   * @return the name of the type of the given parent.
   * @throws IllegalArgumentException if the given parent is null.
   */
  private static String getTypeNameOfParent(final Object parent) {

    if (parent == null) {
      throw new IllegalArgumentException("The given parent is null.");
    }

    return parent.getClass().getSimpleName();
  }
}
