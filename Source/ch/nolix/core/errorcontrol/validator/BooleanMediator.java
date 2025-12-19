package ch.nolix.core.errorcontrol.validator;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;

/**
 * @author Silvan Wyss
 */
public final class BooleanMediator extends Mediator {
  private final boolean argument;

  private BooleanMediator(final boolean argument) {
    this.argument = argument;
  }

  public static BooleanMediator forArgument(final boolean argument) {
    return new BooleanMediator(argument);
  }

  public void is(final boolean value) { //NOSONAR: The parameter is a boolean.
    if (value) {
      isTrue();
    } else {
      isFalse();
    }
  }

  public void isFalse() {
    if (argument) {
      throw InvalidArgumentException.forArgument(argument);
    }
  }

  public void isTrue() {
    if (!argument) {
      throw InvalidArgumentException.forArgument(argument);
    }
  }
}
