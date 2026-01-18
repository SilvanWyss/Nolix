/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.errorcontrol.invalidargumentexception;

import ch.nolix.coreapi.errorcontrol.exceptionargumentbox.ArgumentNameDto;
import ch.nolix.coreapi.errorcontrol.exceptionargumentbox.ErrorPredicateDto;

/**
 * A {@link BiggerArgumentException} is a
 * {@link AbstractInvalidArgumentException} that is supposed to be thrown when a
 * given argument is undesirably bigger than a given max.
 * 
 * @author Silvan Wyss
 */
@SuppressWarnings("serial")
public final class BiggerArgumentException extends AbstractInvalidArgumentException {
  /**
   * Creates a new {@link BiggerArgumentException} for the given argument,
   * argumentName and max.
   * 
   * @param argument
   * @param argumentName
   * @param max
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  private BiggerArgumentException(final double argument, final String argumentName, final double max) {
    super(argument, new ArgumentNameDto(argumentName), new ErrorPredicateDto("is bigger than " + max));
  }

  /**
   * Creates a new {@link BiggerArgumentException} for the given argument,
   * argumentName and max.
   * 
   * @param argument
   * @param argumentName
   * @param max
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  private BiggerArgumentException(final long argument, final String argumentName, final long max) {
    super(argument, new ArgumentNameDto(argumentName), new ErrorPredicateDto("is bigger than " + max));
  }

  /**
   * @param argument
   * @param argumentName
   * @param max
   * @return a new {@link BiggerArgumentException} for the given argument,
   *         argumentName and max.
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  public static BiggerArgumentException forArgumentAndArgumentNameAndMax(
    final double argument,
    final String argumentName,
    final double max) {
    return new BiggerArgumentException(argument, argumentName, max);
  }

  /**
   * @param argument
   * @param argumentName
   * @param max
   * @return a new {@link BiggerArgumentException} for the given argument,
   *         argumentName and max.
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  public static BiggerArgumentException forArgumentAndArgumentNameAndMax(
    final long argument,
    final String argumentName,
    final long max) {
    return new BiggerArgumentException(argument, argumentName, max);
  }
}
