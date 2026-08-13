/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.time.timevalidator;

import ch.nolix.systemapi.time.main.ITime;

/**
 * @author Silvan Wyss
 */
public final class TimeValidator {
  private TimeValidator() {
  }

  public static NamableTimeMediator assertThat(final ITime time) {
    return NamableTimeMediator.forArgument(time);
  }
}
