/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.primitive;

import ch.nolix.base.validation.base.AbstractMediator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.UnequalArgumentException;

/**
 * @author Silvan Wyss
 */
public abstract class AbstractByteMediator extends AbstractMediator {
  private final byte argument;

  protected AbstractByteMediator(final byte argument) {
    this.argument = argument;
  }

  protected AbstractByteMediator(final byte argument, final String argumentName) {
    super(argumentName);

    this.argument = argument;
  }

  /**
   * @param value
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractByteMediator} does not equal the
   *                          given value
   */
  public final void isEqualTo(final byte value) {
    if (argument != value) {
      throw UnequalArgumentException.forArgumentAndArgumentNameAndValue(argument, getArgumentName(), value);
    }
  }

  /**
   * @param value
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractByteMediator} does not equal the
   *                          given value
   */
  public final void isEqualTo(final int value) {
    if (argument != value) {
      throw UnequalArgumentException.forArgumentAndArgumentNameAndValue(argument, getArgumentName(), value);
    }
  }

  protected final byte getArgument() {
    return argument;
  }
}
