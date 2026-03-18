/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.primitivetype;

import ch.nolix.base.errorcontrol.validator.Mediator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;

/**
 * @author Silvan Wyss
 */
public class BitMediator extends Mediator {
  private final boolean argument;

  BitMediator(final boolean argument) {
    this.argument = argument;
  }

  BitMediator(final String argumentName, final boolean argument) {
    super(argumentName);

    this.argument = argument;
  }

  public final void isCleared() {
    if (argument) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        argument,
        getArgumentName(),
        "is not cleared");
    }
  }

  public final void isSet() {
    if (!argument) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        argument,
        getArgumentName(),
        "is not set");
    }
  }
}
