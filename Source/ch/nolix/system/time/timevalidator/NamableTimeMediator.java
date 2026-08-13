/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.time.timevalidator;

import ch.nolix.systemapi.time.main.ITime;

/**
 * @author Silvan Wyss
 */
public final class NamableTimeMediator extends AbstractTimeMediator {
  private NamableTimeMediator(final ITime argument) {
    super(argument);
  }

  public static NamableTimeMediator forArgument(ITime argument) {
    return new NamableTimeMediator(argument);
  }

  public TimeMediator thatIsNamed(final String argumentName) {
    return TimeMediator.forArugmentNameAndArgument(argumentName, getStoredArgument());
  }
}
