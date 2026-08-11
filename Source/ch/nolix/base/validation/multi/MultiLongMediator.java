/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.multi;

import ch.nolix.base.foundation.iterablemapper.SimpleIterableMapper;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.NonPositiveArgumentException;

/**
 * A {@link MultiLongMediator} is not mutable.
 * 
 * @author Silvan Wyss
 */
public final class MultiLongMediator extends AbstractMultiArgumentMediator<Long> {
  private static final SimpleIterableMapper ITERABLE_MAPPER = new SimpleIterableMapper();

  /**
   * Creates a new {@link MultiLongMediator} for the given arguments.
   * 
   * @param arguments
   * @throws RuntimeException if the given arguments is null
   */
  private MultiLongMediator(final Iterable<Long> arguments) {
    super(arguments);
  }

  /**
   * @param arguments
   * @return a new {@link MultiLongMediator} for the given arguments
   * @throws RuntimeException if the given arguments is null
   */
  public static MultiLongMediator forArguments(final int[] arguments) {
    final var argumentsIterable = ITERABLE_MAPPER.toIterable(arguments);

    return new MultiLongMediator(argumentsIterable);
  }

  /**
   * @param arguments
   * @return a new {@link MultiLongMediator} for the given arguments
   * @throws RuntimeException if the given arguments is null
   */
  public static MultiLongMediator forArguments(final Iterable<Long> arguments) {
    return new MultiLongMediator(arguments);
  }

  /**
   * @param arguments
   * @return a new {@link MultiLongMediator} for the given arguments
   * @throws RuntimeException if the given arguments is null
   */
  public static MultiLongMediator forArguments(final long[] arguments) {
    final var argumentsIterable = ITERABLE_MAPPER.toIterable(arguments);

    return new MultiLongMediator(argumentsIterable);
  }

  /**
   * @throws RuntimeException if one of the arguments of the current
   *                          {@link MultiLongMediator} is null or not positive
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
   * @throws RuntimeException if one of the arguments of the current
   *                          {@link MultiLongMediator} is null or negative
   */
  public void areNotNegative() {
    var index = 1;

    for (final var a : getStoredArguments()) {
      if (a == null) {
        throw ArgumentIsNullException.forArgumentName(index + "th argument");
      }

      if (a < 0) {
        throw NegativeArgumentException.forArgumentAndArgumentName(a, index + "th argument");
      }

      index++;
    }
  }
}
