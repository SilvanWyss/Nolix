/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.time.timevalidator;

import ch.nolix.base.validation.object.AbstractObjectMediator;
import ch.nolix.systemapi.time.main.ITime;

/**
 * @author Silvan Wyss
 */
public final class TimeMediator extends AbstractObjectMediator<ITime> {
  private TimeMediator(final String argumentName, final ITime argument) {
    super(argument, argumentName);
  }

  public static TimeMediator forArugmentNameAndArgument(final String argumentName, final ITime argument) {
    return new TimeMediator(argumentName, argument);
  }
}
