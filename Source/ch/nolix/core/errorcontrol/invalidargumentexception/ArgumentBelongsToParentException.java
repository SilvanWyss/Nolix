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
   * that belongs to the given parent.
   * 
   * @param argument
   * @param parent
   * @throws IllegalArgumentException if the given parent is null.
   */
  private ArgumentBelongsToParentException(final Object argument, final Object parent) {

    //Calls constructor of the base class.
    super(argument, "belongs to a " + getTypeNameOfParent(parent));
  }

  /**
   * @param argument
   * @param parent
   * @return a new {@link ArgumentBelongsToParentException} for the given argument
   *         that belongs to the given parent.
   * @throws IllegalArgumentException if the given parent is null.
   */
  public static ArgumentBelongsToParentException forArgumentAndParent(final Object argument, final Object parent) {
    return new ArgumentBelongsToParentException(argument, parent);
  }

  /**
   * @param parent
   * @return the name of the type of the given parent.
   * @throws IllegalArgumentException if the given parent is null.
   */
  private static String getTypeNameOfParent(final Object parent) {

    //Asserts that the given parent is not null.
    if (parent == null) {
      throw new IllegalArgumentException("The given parent is null.");
    }

    return parent.getClass().getSimpleName();
  }
}
