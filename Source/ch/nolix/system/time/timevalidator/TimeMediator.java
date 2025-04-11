package ch.nolix.system.time.timevalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.ArgumentMediator;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public class TimeMediator extends ArgumentMediator<ITime> {

  TimeMediator(final String argumentName, final ITime argument) {
    super(argumentName, argument);
  }

  TimeMediator(final ITime argument) {
    super(argument);
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
