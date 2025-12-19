package ch.nolix.core.errorcontrol.validator;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;

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
