/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.primitive;

import ch.nolix.base.validation.base.AbstractMediator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;

/**
 * @author Silvan Wyss
 */
public abstract class AbstractBitMediator extends AbstractMediator {
  private final boolean argument;

  protected AbstractBitMediator(final boolean argument) {
    this.argument = argument;
  }

  protected AbstractBitMediator(final boolean argument, final String argumentName) {
    super(argumentName);

    this.argument = argument;
  }

  /**
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractBitMediator} is not cleared
   */
  public final void isCleared() {
    if (argument) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        argument,
        getArgumentName(),
        "is not cleared");
    }
  }

  /**
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractBitMediator} is not set
   */
  public final void isSet() {
    if (!argument) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(argument, getArgumentName(), "is not set");
    }
  }
}
