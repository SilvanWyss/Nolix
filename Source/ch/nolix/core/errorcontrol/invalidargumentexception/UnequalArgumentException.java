package ch.nolix.core.errorcontrol.invalidargumentexception;

import ch.nolix.coreapi.errorcontrolapi.exceptionargumentboxapi.ArgumentNameDto;
import ch.nolix.coreapi.errorcontrolapi.exceptionargumentboxapi.ErrorPredicateDto;

/**
 * A {@link UnequalArgumentException} is a
 * {@link AbstractInvalidArgumentException} that is supposed to be thrown when a
 * given argument does undesirably not equal a given value.
 * 
 * @author Silvan Wyss
 * @version 2017-01-01
 */
@SuppressWarnings("serial")
public final class UnequalArgumentException extends AbstractInvalidArgumentException {

  private static final String DEFAULT_VALUE_NAME = Object.class.getSimpleName();

  /**
   * Creates a new {@link UnequalArgumentException} for the given argument and
   * value.
   * 
   * @param argument - Can be null.
   * @param value
   * @throws RuntimeException if the given value is null.
   */
  private UnequalArgumentException(final Object argument, final Object value) {
    super(argument, new ErrorPredicateDto("does not equal the " + getNameOfValue(value) + " '" + value + "'"));
  }

  /**
   * Creates a new {@link UnequalArgumentException} for the given argument,
   * argumentName and value.
   * 
   * @param argument
   * @param argumentName
   * @param value
   * @throws RuntimeException if the given argumentName is null or blank.
   * @throws RuntimeException if the given value is null.
   */
  private UnequalArgumentException(final Object argument, final String argumentName, final Object value) {
    super(
      argument,
      new ArgumentNameDto(argumentName),
      new ErrorPredicateDto("does not equal the " + getNameOfValue(value) + " '" + value + "'"));
  }

  /**
   * @param argument
   * @param argumentName
   * @param value
   * @return a new {@link UnequalArgumentException} for the given argument,
   *         argumentName and value.
   * @throws RuntimeException if the given argumentName is null or blank.
   * @throws RuntimeException if the given value is null.
   */
  public static UnequalArgumentException forArgumentAndArgumentNameAndValue(
    final Object argument,
    final String argumentName,
    final Object value) {
    return new UnequalArgumentException(argument, argumentName, value);
  }

  /**
   * @param argument
   * @param value
   * @return a new {@link UnequalArgumentException} for the given argument and
   *         value.
   * @throws RuntimeException if the given value is null.
   */
  public static UnequalArgumentException forArgumentAndValue(final Object argument, final Object value) {
    return new UnequalArgumentException(argument, value);
  }

  /**
   * @param value
   * @return the name of the given value.
   * @throws RuntimeException if the given value is null.
   */
  private static String getNameOfValue(final Object value) {

    if (value == null) {
      throw new IllegalArgumentException("The given value is null.");
    }

    final var name = value.getClass().getSimpleName();

    if (name != null && !name.isEmpty()) {
      return name;
    }

    return DEFAULT_VALUE_NAME;
  }
}
