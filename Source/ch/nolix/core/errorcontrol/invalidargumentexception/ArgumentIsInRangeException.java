/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.errorcontrol.invalidargumentexception;

import ch.nolix.coreapi.errorcontrol.exceptionargumentbox.ArgumentNameDto;
import ch.nolix.coreapi.errorcontrol.exceptionargumentbox.ErrorPredicateDto;

/**
 * A {@link ArgumentIsInRangeException} is a
 * {@link AbstractInvalidArgumentException} that is supposed to be thrown when a
 * given value is undesirably in a given range.
 * 
 * @author Silvan Wyss
 */
@SuppressWarnings("serial")
public final class ArgumentIsInRangeException extends AbstractInvalidArgumentException {
  /**
   * Creates a new {@link ArgumentIsInRangeException} for the given argument and
   * range defined by the given min and max.
   * 
   * @param argument
   * @param min
   * @param max
   */
  private ArgumentIsInRangeException(final double argument, final double min, final double max) {
    super(argument, new ErrorPredicateDto("is in [" + min + ", " + max + "]"));
  }

  /**
   * Creates a new {@link ArgumentIsInRangeException} for the given argument and
   * range defined by the given min and max.
   * 
   * @param argument
   * @param min
   * @param max
   */
  private ArgumentIsInRangeException(final long argument, final long min, final long max) {
    super(argument, new ErrorPredicateDto("is in [" + min + ", " + max + "]"));
  }

  /**
   * Creates a new {@link ArgumentIsInRangeException} for the given argument,
   * argumentName and range defined by the given min and max.
   * 
   * @param argument
   * @param argumentName
   * @param min
   * @param max
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  private ArgumentIsInRangeException(
    final double argument,
    final String argumentName,
    final double min,
    final double max) {
    super(argument, new ArgumentNameDto(argumentName), new ErrorPredicateDto("is in [" + min + ", " + max + "]"));
  }

  /**
   * Creates a new {@link ArgumentIsInRangeException} for the given argument,
   * argumentName and range defined by the given min and max.
   * 
   * @param argument
   * @param argumentName
   * @param min
   * @param max
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  private ArgumentIsInRangeException(
    final long argument,
    final String argumentName,
    final long min,
    final long max) {
    super(argument, new ArgumentNameDto(argumentName), new ErrorPredicateDto("is in [" + min + ", " + max + "]"));
  }

  /**
   * @param argument
   * @param min
   * @param max
   * @return a new {@link ArgumentIsInRangeException} for the given argument and
   *         range defined by the given min and max.
   */
  public static ArgumentIsInRangeException forArgumentAndRangeWithMinAndMax(
    final double argument,
    final double min,
    final double max) {
    return new ArgumentIsInRangeException(argument, min, max);
  }

  /**
   * @param argument
   * @param min
   * @param max
   * @return a new {@link ArgumentIsInRangeException} for the given argument and
   *         range defined by the given min and max.
   */
  public static ArgumentIsInRangeException forArgumentAndRangeWithMinAndMax(
    final long argument,
    final long min,
    final long max) {
    return new ArgumentIsInRangeException(argument, min, max);
  }

  /**
   * @param argument
   * @param argumentName
   * @param min
   * @param max
   * @return a new {@link ArgumentIsInRangeException} for the given argument,
   *         argumentName and range defined by the given min and max.
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  public static ArgumentIsInRangeException forArgumentNameAndArgumentAndRangeWithMinAndMax(
    final double argument,
    final String argumentName,
    final double min,
    final double max) {
    return new ArgumentIsInRangeException(argument, argumentName, min, max);
  }

  /**
   * @param argument
   * @param argumentName
   * @param min
   * @param max
   * @return a new {@link ArgumentIsInRangeException} for the given argument,
   *         argumentName and range defined by the given min and max.
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  public static ArgumentIsInRangeException forArgumentNameAndArgumentAndRangeWithMinAndMax(
    final String argumentName,
    final long argument,
    final long min,
    final long max) {
    return new ArgumentIsInRangeException(argument, argumentName, min, max);
  }
}
