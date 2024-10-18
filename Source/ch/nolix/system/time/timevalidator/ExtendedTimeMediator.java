package ch.nolix.system.time.timevalidator;

import ch.nolix.systemapi.timeapi.momentapi.ITime;

public final class ExtendedTimeMediator extends TimeMediator {

  ExtendedTimeMediator(final ITime argument) {
    super(argument);
  }

  public TimeMediator thatIsNamed(final String argumentName) {
    return new TimeMediator(argumentName, getStoredArgument());
  }
}
