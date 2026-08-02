/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.multi;

import ch.nolix.base.foundation.iterablemapper.SimpleIterableMapper;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.NonPositiveArgumentException;

/**
 * A {@link MultiDoubleMediator} is not mutable.
 * 
 * @author Silvan Wyss
 */
public final class MultiDoubleMediator extends AbstractMultiArgumentMediator<Double> {
  private static final SimpleIterableMapper ITERABLE_MAPPER = new SimpleIterableMapper();

  /**
   * Creates a new {@link MultiDoubleMediator} for the given arguments.
   * 
   * @param arguments
   * @throws RuntimeException if the given arguments is null
   */
  private MultiDoubleMediator(final Iterable<Double> arguments) {
    super(arguments);
  }

  /**
   * @param arguments
   * @return a new {@link MultiDoubleMediator} for the given arguments
   * @throws RuntimeException if the given arguments is null
   */
  public static MultiDoubleMediator forArugments(final double[] arguments) {
    final var argumentsIterable = ITERABLE_MAPPER.toIterable(arguments);

    return new MultiDoubleMediator(argumentsIterable);
  }

  /**
   * @param arguments
   * @return a new {@link MultiDoubleMediator} for the given arguments
   * @throws RuntimeException if the given arguments is null
   */
  public static MultiDoubleMediator forArugments(final Iterable<Double> arguments) {
    return new MultiDoubleMediator(arguments);
  }

  /**
   * @param limit
   * @throws RuntimeException if one of the arguments of the current
   *                          {@link MultiDoubleMediator} is null or not bigger
   *                          than the given limit.
   */
  public void areBiggerThan(final double limit) {
    var index = 1;

    for (final var a : getStoredArguments()) {
      if (a == null) {
        throw ArgumentIsNullException.forArgumentName(index + "th argument");
      }

      if (a <= limit) {
        throw //
        InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
          a,
          index + "th argument",
          "is not bigger than " + limit);
      }

      index++;
    }
  }

  /**
   * @throws RuntimeException if one of the arguments of the current
   *                          {@link MultiDoubleMediator} is null or not positive.
   */
  public void arePositive() {
    var index = 1;

    for (final var a : getStoredArguments()) {
      if (a == null) {
        throw ArgumentIsNullException.forArgumentName(index + "th argument");
      }

      if (a <= 0) {
        throw NonPositiveArgumentException.forArgumentAndArgumentName(a, index + "th argument");
      }

      index++;
    }
  }

  /**
   * @param limit
   * @throws RuntimeException if one of the arguments of the current
   *                          {@link MultiDoubleMediator} is null or not bigger
   *                          than the given limit.
   */
  public void areSmallerThan(final double limit) {
    var index = 1;

    for (final var a : getStoredArguments()) {
      if (a == null) {
        throw ArgumentIsNullException.forArgumentName(index + "th argument");
      }

      if (a >= limit) {
        throw //
        InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
          a,
          index + "th argument",
          "is not smaller than " + limit);
      }

      index++;
    }
  }
}
