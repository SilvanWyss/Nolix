package ch.nolix.core.errorcontrol.invalidargumentexception;

import ch.nolix.coreapi.errorcontrol.exceptionargumentbox.ErrorPredicateDto;

/**
 * A {@link ArgumentBelongsToParentException} is a
 * {@link AbstractInvalidArgumentException} that is supposed to be thrown when a
 * given argument belongs undesirably to a parent.
 * 
 * @author Silvan Wyss
 * @version 2019-10-01
 */
@SuppressWarnings("serial")
public final class ArgumentBelongsToParentException extends AbstractInvalidArgumentException {
  private static final String DEFAULT_TYPE_NAME = Object.class.getSimpleName();

  /**
   * Creates a new {@link ArgumentBelongsToParentException} for the given argument
   * and parent.
   * 
   * @param argument - Can be null.
   * @param parent
   * @throws RuntimeException if the given parent is null.
   */
  private ArgumentBelongsToParentException(final Object argument, final Object parent) {
    super(argument, new ErrorPredicateDto("belongs to a " + getTypeNameOfObject(parent)));
  }

  /**
   * @param argument - Can be null.
   * @param parent
   * @return a new {@link ArgumentBelongsToParentException} for the given argument
   *         and parent.
   * @throws RuntimeException if the given parent is null.
   */
  public static ArgumentBelongsToParentException forArgumentAndParent(final Object argument, final Object parent) {
    return new ArgumentBelongsToParentException(argument, parent);
  }

  /**
   * @param object
   * @return the name of the type of the given object.
   * @throws RuntimeException if the given object is null.
   */
  private static String getTypeNameOfObject(final Object object) {
    if (object == null) {
      throw new IllegalArgumentException("The given object is null.");
    }

    final var name = object.getClass().getSimpleName();

    if (name != null && !name.isEmpty()) {
      return name;
    }

    return DEFAULT_TYPE_NAME;
  }
}
