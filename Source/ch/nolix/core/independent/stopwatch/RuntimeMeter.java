/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.independent.stopwatch;

/**
 * @author Silvan Wyss
 */
public final class RuntimeMeter {
  public long getRuntimeOfActionInMilliseconds(final Runnable action) {
    final var stopWatch = StopWatch.createStartedStopWatch();

    action.run();

    return stopWatch.getTotalRunningTimeInMilliseconds();
  }
}
