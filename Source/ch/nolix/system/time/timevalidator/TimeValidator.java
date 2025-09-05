package ch.nolix.system.time.timevalidator;

import ch.nolix.systemapi.time.moment.ITime;

public final class TimeValidator {
  private TimeValidator() {
  }

  public static ExtendedTimeMediator assertThat(final ITime time) {
    return new ExtendedTimeMediator(time);
  }
}
