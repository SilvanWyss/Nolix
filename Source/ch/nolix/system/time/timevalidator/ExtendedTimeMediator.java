/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.time.timevalidator;

import ch.nolix.systemapi.time.moment.ITime;

/**
 * @author Silvan Wyss
 */
public final class ExtendedTimeMediator extends TimeMediator {
  ExtendedTimeMediator(final ITime argument) {
    super(argument);
  }

  public TimeMediator thatIsNamed(final String argumentName) {
    return new TimeMediator(argumentName, getStoredArgument());
  }
}
