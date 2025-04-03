package ch.nolix.core.errorcontrol.invalidargumentexception;

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

  /**
   * Creates a new {@link ArgumentDoesNotBelongToParentException} for the given
   * argument.
   * 
   * @param argument
   */
  private ArgumentDoesNotBelongToParentException(final Object argument) {
    super(argument, DEFAULT_ARGUMENT_NAME, "does not belong to a parent");
  }

  /**
   * Creates a new {@link ArgumentDoesNotBelongToParentException} for the given
   * argument and argumentName.
   * 
   * @param argument
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
   * @param argument
   * @param argumentName
   * @param parentType
   * @throws IllegalArgumentException if the given argumentName is null or blank.
   * @throws IllegalArgumentException if the given parentType is null.
   */
  private ArgumentDoesNotBelongToParentException(
    final Object argument,
    final String argumentName,
    final Class<?> parentType) {

    //Calls constructor of the base class.
    super(argument, argumentName, "does not belong to a " + getNameOfParentType(parentType));
  }

  /**
   * @param argument
   * @return a new {@link ArgumentDoesNotBelongToParentException} for the given
   *         argument.
   */
  public static ArgumentDoesNotBelongToParentException forArgument(final Object argument) {
    return new ArgumentDoesNotBelongToParentException(argument);
  }

  /**
   * @param argument
   * @param parentType
   * @return a new {@link ArgumentDoesNotBelongToParentException} for the given
   *         argument and parentType.
   */
  public static ArgumentDoesNotBelongToParentException forArgumentAndParentType(
    final Object argument,
    final Class<?> parentType) {
    return new ArgumentDoesNotBelongToParentException(argument, DEFAULT_ARGUMENT_NAME, parentType);
  }

  /**
   * @param parentType
   * @return the name of the given parentType.
   * @throws IllegalArgumentException if the given parentType is null.
   */
  private static String getNameOfParentType(final Class<?> parentType) {

    //Asserts that the given parent type is not null.
    if (parentType == null) {
      throw new IllegalArgumentException("The given parent type is null.");
    }

    return parentType.getSimpleName();
  }
}
