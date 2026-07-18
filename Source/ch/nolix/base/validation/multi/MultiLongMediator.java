/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.multi;

import ch.nolix.base.independent.list.List;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.NonPositiveArgumentException;

/**
 * A {@link MultiLongMediator} is not mutable.
 * 
 * @author Silvan Wyss
 */
public final class MultiLongMediator extends AbstractMultiArgumentMediator<Long> {
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
    final List<Long> longArguments = List.createEmpty();

    for (final var a : arguments) {
      longArguments.addAtEnd((long) a);
    }

    return new MultiLongMediator(longArguments);
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
    final List<Long> longArguments = List.createEmpty();

    for (final var a : arguments) {
      longArguments.addAtEnd(a);
    }

    return new MultiLongMediator(longArguments);
  }

  /**
   * @throws RuntimeException if one of the arguments of the current
   *                          {@link MultiLongMediator} is null or not positive.
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
   *                          {@link MultiLongMediator} is null or negative.
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
