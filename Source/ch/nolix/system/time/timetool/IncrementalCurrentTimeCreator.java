/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.time.timetool;

import ch.nolix.system.time.main.Time;
import ch.nolix.systemapi.time.main.ITime;
import ch.nolix.systemapi.time.main.TimeZone;
import ch.nolix.systemapi.time.timetool.IIncrementalCurrentTimeCreator;

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
