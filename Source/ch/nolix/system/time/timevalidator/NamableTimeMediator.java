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

  /**
   * @param argument
   * @return a new {@link NamableTimeMediator} for the given argument
   */
  public static NamableTimeMediator forArgument(ITime argument) {
    return new NamableTimeMediator(argument);
  }

  /**
   * @param argumentName
   * @return a new {@link TimeMediator} for the argument of the current
   *         {@link NamableTimeMediator} with the given argumentName
   */
  public TimeMediator thatIsNamed(final String argumentName) {
    return TimeMediator.forArugmentNameAndArgument(argumentName, getStoredArgument());
  }
}
