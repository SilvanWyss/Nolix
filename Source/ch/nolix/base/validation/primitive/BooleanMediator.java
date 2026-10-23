/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.primitive;

import ch.nolix.base.validation.base.AbstractMediator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;

/**
 * @author Silvan Wyss
 */
public final class BooleanMediator extends AbstractMediator {
  private final boolean argument;

  private BooleanMediator(final boolean argument) {
    this.argument = argument;
  }

  /**
   * @param argument
   * @return a new {@link BooleanMediator} for the given argument
   */
  public static BooleanMediator forArgument(final boolean argument) {
    return new BooleanMediator(argument);
  }

  /**
   * @param value
   * @throws RuntimeException if the argument of the current
   *                          {@link BooleanMediator} is not the given value
   */
  public void is(final boolean value) { // NOSONAR: The parameter is a boolean.
    if (value) {
      isTrue();
    } else {
      isFalse();
    }
  }

  /**
   * @throws RuntimeException if the argument of the current
   *                          {@link BooleanMediator} is not false
   */
  public void isFalse() {
    if (argument) {
      throw InvalidArgumentException.forArgument(argument);
    }
  }

  /**
   * @throws RuntimeException if the argument of the current
   *                          {@link BooleanMediator} is not true
   */
  public void isTrue() {
    if (!argument) {
      throw InvalidArgumentException.forArgument(argument);
    }
  }
}
