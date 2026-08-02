/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.time.moment;

import ch.nolix.systemapi.time.moment.IIncrementalCurrentTimeCreator;
import ch.nolix.systemapi.time.moment.ITime;
import ch.nolix.systemapi.time.timestructure.TimeZone;

/**
 * @author Silvan Wyss
 */
public final class IncrementalCurrentTimeCreator implements IIncrementalCurrentTimeCreator {
  private ITime latestTime = Time.ofNowAndTimeZone(TimeZone.UTC);

  @Override
  public ITime getCurrentTime() {
    ITime now = Time.ofNowAndTimeZone(TimeZone.UTC);

    if (now.equals(latestTime)) {
      now = now.withAddedOrSubtractedMicroseconds(1);
    }

    latestTime = now;

    return now;
  }
}
