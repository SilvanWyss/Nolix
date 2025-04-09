package ch.nolix.core.errorcontrol.invalidargumentexception;

/**
 * A {@link ArgumentIsNullException} is a {@link InvalidArgumentException} that
 * is supposed to be thrown when a given argument is undesirably null.
 * 
 * @author Silvan Wyss
 * @version 2016-05-01
 */
@SuppressWarnings("serial")
public final class ArgumentIsNullException extends InvalidArgumentException {

  private static final String ERROR_PREDICATE = "is null";

  /**
   * Creates a new {@link ArgumentIsNullException} for the given argumentType.
   * 
   * @param argumentType
   * @throws IllegalArgumentException if the given argumentType is null.
   */
  private ArgumentIsNullException(final Class<?> argumentType) {
    super((Object) null, getNameOfArgumentType(argumentType), ERROR_PREDICATE);
  }

  /**
   * Creates a new {@link ArgumentIsNullException} for the given argumentName.
   * 
   * @param argumentName
   * @throws IllegalArgumentException if the given argumentName is null or blank.
   */
  private ArgumentIsNullException(final String argumentName) {
    super((Object) null, argumentName, ERROR_PREDICATE);
  }

  /**
   * @param argumentName
   * @return a new {@link ArgumentIsNullException} for the given argumentName.
   * @throws IllegalArgumentException if the given argumentName is null or blank.
   */
  public static ArgumentIsNullException forArgumentName(final String argumentName) {
    return new ArgumentIsNullException(argumentName);
  }

  /**
   * @param argumentType
   * @return a new {@link ArgumentIsNullException} for the given argumentType.
   * @throws IllegalArgumentException if the given argumentType is null.
   */
  public static ArgumentIsNullException forArgumentType(final Class<?> argumentType) {
    return new ArgumentIsNullException(argumentType);
  }

  /**
   * @param argumentType
   * @return the name of the given argumentType.
   * @throws IllegalArgumentException if the given argumentType is null.
   */
  private static String getNameOfArgumentType(final Class<?> argumentType) {

    if (argumentType == null) {
      throw new IllegalArgumentException("The given argument type is null.");
    }

    return argumentType.getSimpleName();
  }
}
