package ch.nolix.core.errorcontrol.invalidargumentexception;

import ch.nolix.coreapi.errorcontrol.exceptionargumentbox.ArgumentNameDto;
import ch.nolix.coreapi.errorcontrol.exceptionargumentbox.ErrorPredicateDto;

/**
 * A {@link ArgumentIsNullException} is a
 * {@link AbstractInvalidArgumentException} that is supposed to be thrown when a
 * given argument is undesirably null.
 * 
 * @author Silvan Wyss
 * @version 2016-05-01
 */
@SuppressWarnings("serial")
public final class ArgumentIsNullException extends AbstractInvalidArgumentException {
  private static final String ERROR_PREDICATE = "is null";

  /**
   * Creates a new {@link ArgumentIsNullException} for the given argumentType.
   * 
   * @param argumentType
   * @throws RuntimeException if the given argumentType is null.
   */
  private ArgumentIsNullException(final Class<?> argumentType) {
    super(
      null,
      new ArgumentNameDto(getNameOfArgumentType(argumentType)),
      new ErrorPredicateDto(ERROR_PREDICATE));
  }

  /**
   * Creates a new {@link ArgumentIsNullException} for the given argumentName.
   * 
   * @param argumentName
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  private ArgumentIsNullException(final String argumentName) {
    super(null, new ArgumentNameDto(argumentName), new ErrorPredicateDto(ERROR_PREDICATE));
  }

  /**
   * @param argumentName
   * @return a new {@link ArgumentIsNullException} for the given argumentName.
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  public static ArgumentIsNullException forArgumentName(final String argumentName) {
    return new ArgumentIsNullException(argumentName);
  }

  /**
   * @param argumentType
   * @return a new {@link ArgumentIsNullException} for the given argumentType.
   * @throws RuntimeException if the given argumentType is null.
   */
  public static ArgumentIsNullException forArgumentType(final Class<?> argumentType) {
    return new ArgumentIsNullException(argumentType);
  }

  /**
   * @param argumentType
   * @return the name of the given argumentType.
   * @throws RuntimeException if the given argumentType is null.
   */
  private static String getNameOfArgumentType(final Class<?> argumentType) {
    if (argumentType == null) {
      throw new IllegalArgumentException("The given argument type is null.");
    }

    return argumentType.getSimpleName();
  }
}
