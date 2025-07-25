package ch.nolix.core.errorcontrol.invalidargumentexception;

import ch.nolix.coreapi.errorcontrol.exceptionargumentbox.ArgumentNameDto;
import ch.nolix.coreapi.errorcontrol.exceptionargumentbox.ErrorPredicateDto;

/**
 * A {@link ArgumentIsOutOfRangeException} is a
 * {@link AbstractInvalidArgumentException} that is supposed to be thrown when a
 * given argument is undesirably not in a given range.
 * 
 * @author Silvan Wyss
 * @version 2016-03-01
 */
@SuppressWarnings("serial")
public final class ArgumentIsOutOfRangeException extends AbstractInvalidArgumentException {

  /**
   * Creates a new {@link ArgumentIsOutOfRangeException} for the given argument
   * and range defined by the given min and max.
   * 
   * @param argument - Can be null.
   * @param min
   * @param max
   */
  private ArgumentIsOutOfRangeException(final double argument, final double min, final double max) {
    super(argument, new ErrorPredicateDto("is not in [" + min + ", " + max + "]"));
  }

  /**
   * Creates a new {@link ArgumentIsOutOfRangeException} for the given argument,
   * argumentName and range defined by the given min and max.
   * 
   * @param argument
   * @param argumentName
   * @param min
   * @param max
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  private ArgumentIsOutOfRangeException(
    final double argument,
    final String argumentName,
    final double min,
    final double max) {
    super(argument, new ArgumentNameDto(argumentName), new ErrorPredicateDto("is not in [" + min + ", " + max + "]"));
  }

  /**
   * Creates a new {@link ArgumentIsOutOfRangeException} for the given argument
   * and range defined by the given min and max.
   * 
   * @param argument - Can be null.
   * @param min
   * @param max
   */
  private ArgumentIsOutOfRangeException(final long argument, final long min, final long max) {
    super(argument, new ErrorPredicateDto("is not in [" + min + ", " + max + "]"));
  }

  /**
   * Creates a new {@link ArgumentIsOutOfRangeException} for the given argument,
   * argumentName and range defined by the given min and max.
   * 
   * @param argument
   * @param argumentName
   * @param min
   * @param max
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  private ArgumentIsOutOfRangeException(
    final long argument,
    final String argumentName,
    final long min,
    final long max) {
    super(argument, new ArgumentNameDto(argumentName), new ErrorPredicateDto("is not in [" + min + ", " + max + "]"));
  }

  /**
   * @param argument
   * @param argumentName
   * @return a new {@link ArgumentIsOutOfRangeException} for the given argument,
   *         argumentName and range defined by the given min and max.
   * @param min
   * @param max
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  public static ArgumentIsOutOfRangeException forArgumentAndArgumentNameAndRangeWithMinAndMax(
    final double argument,
    final String argumentName,
    final double min,
    final double max) {
    return new ArgumentIsOutOfRangeException(argument, argumentName, min, max);
  }

  /**
   * @param argument
   * @param argumentName
   * @return a new {@link ArgumentIsOutOfRangeException} for the given argument,
   *         argumentName and range defined by the given min and max.
   * @param min
   * @param max
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  public static ArgumentIsOutOfRangeException forArgumentAndArgumentNameAndRangeWithMinAndMax(
    final long argument,
    final String argumentName,
    final long min,
    final long max) {
    return new ArgumentIsOutOfRangeException(argument, argumentName, min, max);
  }

  /**
   * @param argument
   * @return a new {@link ArgumentIsOutOfRangeException} for the given argument
   *         and range defined by the given min and max.
   * @param min
   * @param max
   */
  public static ArgumentIsOutOfRangeException forArgumentAndRangeWithMinAndMax(
    final double argument,
    final double min,
    final double max) {
    return new ArgumentIsOutOfRangeException(argument, min, max);
  }

  /**
   * @param argument
   * @return a new {@link ArgumentIsOutOfRangeException} for the given argument
   *         and range defined by the given min and max.
   * @param min
   * @param max
   */
  public static ArgumentIsOutOfRangeException forArgumentAndRangeWithMinAndMax(
    final long argument,
    final long min,
    final long max) {
    return new ArgumentIsOutOfRangeException(argument, min, max);
  }
}
