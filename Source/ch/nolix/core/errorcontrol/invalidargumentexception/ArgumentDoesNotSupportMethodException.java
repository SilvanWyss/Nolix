package ch.nolix.core.errorcontrol.invalidargumentexception;

import ch.nolix.coreapi.valueboxapi.ErrorPredicateDto;

/**
 * A {@link ArgumentDoesNotSupportMethodException} is a
 * {@link InvalidArgumentException} that is supposed to be thrown when a given
 * argument does undesirably not support an invoked method.
 * 
 * @author Silvan Wyss
 * @version 2019-02-03
 */
@SuppressWarnings("serial")
public final class ArgumentDoesNotSupportMethodException extends InvalidArgumentException {

  /**
   * Creates a new {@link ArgumentDoesNotSupportMethodException} for the given
   * argument and methodName.
   * 
   * @param argument   - Can be null.
   * @param methodName
   * @throws IllegalArgumentException if the given methodName is null or blank.
   */
  private ArgumentDoesNotSupportMethodException(final Object argument, final String methodName) {
    super(
      argument,
      new ErrorPredicateDto("does not support the " + getValidatedMethodNameFromMethodName(methodName) + " method"));
  }

  /**
   * @param argument   - Can be null.
   * @param methodName
   * @return new {@link ArgumentDoesNotSupportMethodException} for the given
   *         argument and methodName.
   * @throws IllegalArgumentException if the given methodName is null or blank.
   */
  public static ArgumentDoesNotSupportMethodException forArgumentAndMethodName(
    final Object argument,
    final String methodName) {
    return new ArgumentDoesNotSupportMethodException(argument, methodName);
  }

  /**
   * @param methodName
   * @return a validated method name from the given methodName.
   * @throws IllegalArgumentException if the given methodName is null or blank.
   */
  private static String getValidatedMethodNameFromMethodName(final String methodName) {

    if (methodName == null) {
      throw new IllegalArgumentException("The given method name is null.");
    }

    if (methodName.isBlank()) {
      throw new IllegalArgumentException("The given method name is blank.");
    }

    return methodName;
  }
}
