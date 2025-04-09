package ch.nolix.core.errorcontrol.invalidargumentexception;

import ch.nolix.coreapi.valueboxapi.ErrorPredicateDto;

/**
 * A {@link ArgumentDoesNotBelongToParentException} is a
 * {@link InvalidArgumentException} that is supposed to be thrown when a given
 * argument does undesirably not belong to a parent.
 * 
 * @author Silvan Wyss
 * @version 2022-01-30
 */
@SuppressWarnings("serial")
public final class ArgumentDoesNotBelongToParentException extends InvalidArgumentException {

  private static final String DEFAULT_PARENT_TYPE_NAME = Object.class.getSimpleName();

  /**
   * Creates a new {@link ArgumentDoesNotBelongToParentException} for the given
   * argument.
   * 
   * @param argument - Can be null.
   */
  private ArgumentDoesNotBelongToParentException(final Object argument) {
    super(argument, new ErrorPredicateDto("does not belong to a parent"));
  }

  /**
   * Creates a new {@link ArgumentDoesNotBelongToParentException} for the given
   * argument and parentType.
   * 
   * @param argument   - Can be null.
   * @param parentType
   * @throws IllegalArgumentException if the given parentType is null.
   */
  private ArgumentDoesNotBelongToParentException(
    final Object argument,
    final Class<?> parentType) {
    super(argument, new ErrorPredicateDto("does not belong to a " + getNameOfParentType(parentType)));
  }

  /**
   * Creates a new {@link ArgumentDoesNotBelongToParentException} for the given
   * argument and argumentName.
   * 
   * @param argument     - Can be null.
   * @param argumentName
   * @throws IllegalArgumentException if the given argumentName is null or blank.
   */
  private ArgumentDoesNotBelongToParentException(final Object argument, final String argumentName) {
    super(argument, argumentName, "does not belong to a parent");
  }

  /**
   * Creates a new {@link ArgumentDoesNotBelongToParentException} for the given
   * argument, argumentName and parentType.
   * 
   * @param argument     - Can be null.
   * @param argumentName
   * @param parentType
   * @throws IllegalArgumentException if the given argumentName is null or blank.
   * @throws IllegalArgumentException if the given parentType is null.
   */
  private ArgumentDoesNotBelongToParentException(
    final Object argument,
    final String argumentName,
    final Class<?> parentType) {
    super(argument, argumentName, "does not belong to a " + getNameOfParentType(parentType));
  }

  /**
   * @param argument - Can be null.
   * @return a new {@link ArgumentDoesNotBelongToParentException} for the given
   *         argument.
   */
  public static ArgumentDoesNotBelongToParentException forArgument(final Object argument) {
    return new ArgumentDoesNotBelongToParentException(argument);
  }

  /**
   * @param argument   - Can be null.
   * @param parentType
   * @return a new {@link ArgumentDoesNotBelongToParentException} for the given
   *         argument and parentType.
   * @throws IllegalArgumentException if the given parentType is null.
   */
  public static ArgumentDoesNotBelongToParentException forArgumentAndParentType(
    final Object argument,
    final Class<?> parentType) {
    return new ArgumentDoesNotBelongToParentException(argument, parentType);
  }

  /**
   * @param parentType
   * @return the name of the given parentType.
   * @throws IllegalArgumentException if the given parentType is null.
   */
  private static String getNameOfParentType(final Class<?> parentType) {

    if (parentType == null) {
      throw new IllegalArgumentException("The given parent type is null.");
    }

    final var name = parentType.getSimpleName();

    if (name != null && !name.isEmpty()) {
      return name;
    }

    return DEFAULT_PARENT_TYPE_NAME;
  }
}
