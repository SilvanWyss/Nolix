/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.errorcontrol.invalidargumentexception;

import ch.nolix.coreapi.errorcontrol.exceptionargumentbox.ArgumentNameDto;
import ch.nolix.coreapi.errorcontrol.exceptionargumentbox.ErrorPredicateDto;

/**
 * A {@link EqualArgumentException} is a
 * {@link AbstractInvalidArgumentException} that is supposed to be thrown when a
 * given argument equals undesirably a given value.
 * 
 * @author Silvan Wyss
 */
@SuppressWarnings("serial")
public final class EqualArgumentException extends AbstractInvalidArgumentException {
  /**
   * Creates a new {@link EqualArgumentException} for the given argument and
   * equalValue.
   * 
   * @param argument   - Can be null.
   * @param equalValue - Can be null.
   */
  private EqualArgumentException(final Object argument, final Object equalValue) {
    super(argument, new ErrorPredicateDto("equals " + equalValue));
  }

  /**
   * Creates a new {@link EqualArgumentException} for the given argument,
   * argumentName and equalValue.
   * 
   * @param argument     - Can be null.
   * @param argumentName
   * @param equalValue   - Can be null.
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  private EqualArgumentException(final double argument, final String argumentName, final double equalValue) {
    super(argument, new ArgumentNameDto(argumentName), new ErrorPredicateDto("equals " + equalValue));
  }

  /**
   * Creates a new {@link EqualArgumentException} for the given argument,
   * argumentName and equalValue.
   * 
   * @param argument     - Can be null.
   * @param argumentName
   * @param equalValue   - Can be null.
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  private EqualArgumentException(final long argument, final String argumentName, final long equalValue) {
    super(argument, new ArgumentNameDto(argumentName), new ErrorPredicateDto("equals " + equalValue));
  }

  /**
   * @param argument     - Can be null.
   * @param argumentName
   * @param equalValue   - Can be null.
   * @return a new {@link EqualArgumentException} for the given argument,
   *         argumentName and equalValue.
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  public static EqualArgumentException forArgumentAndArgumentNameAndEqualValue(
    final double argument,
    final String argumentName,
    final double equalValue) {
    return new EqualArgumentException(argument, argumentName, equalValue);
  }

  /**
   * @param argument     - Can be null.
   * @param argumentName
   * @param equalValue   - Can be null.
   * @return a new {@link EqualArgumentException} for the given argument,
   *         argumentName and equalValue.
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  public static EqualArgumentException forArgumentAndArgumentNameAndEqualValue(
    final long argument,
    final String argumentName,
    final long equalValue) {
    return new EqualArgumentException(argument, argumentName, equalValue);
  }

  /**
   * @param argument   - Can be null.
   * @param equalValue - Can be null.
   * @return a new {@link EqualArgumentException} for the given argument and
   *         equalValue.
   */
  public static EqualArgumentException forArgumentAndEqualValue(final Object argument, final Object equalValue) {
    return new EqualArgumentException(argument, equalValue);
  }
}
