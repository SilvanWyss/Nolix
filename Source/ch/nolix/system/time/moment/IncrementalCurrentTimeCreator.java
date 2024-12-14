package ch.nolix.system.time.moment;

import ch.nolix.systemapi.timeapi.momentapi.IIncrementalCurrentTimeCreator;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

/**
 * @author Silvan Wyss
 * @version 2024-12-14
 */
public final class IncrementalCurrentTimeCreator implements IIncrementalCurrentTimeCreator {

  private ITime latestTime = Time.ofNow();

  @Override
  public ITime getCurrentTime() {

    ITime now = Time.ofNow();

    if (now.equals(latestTime)) {
      now = now.withAddedOrSubtractedNanoseconds(1);
    }

    latestTime = now;

    return now;
  }
}
