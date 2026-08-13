/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.time.timevalidator;

import ch.nolix.base.validation.object.AbstractObjectMediator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.time.main.ITime;

public abstract class AbstractTimeMediator extends AbstractObjectMediator<ITime> {
  protected AbstractTimeMediator(final ITime argument) {
    super(argument);
  }

  protected AbstractTimeMediator(final String argumentName, final ITime argument) {
    super(argument, argumentName);
  }

  public final void isAfter(final ITime time) {
    isNotNull();

    if (!getStoredArgument().isAfter(time)) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        getStoredArgument(),
        getArgumentName(),
        "is not after " + time);
    }
  }

  public final void isBefore(final ITime time) {
    isNotNull();

    if (!getStoredArgument().isBefore(time)) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        getStoredArgument(),
        getArgumentName(),
        "is not before " + time);
    }
  }
}
