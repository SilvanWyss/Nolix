/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.stopwatch;

/**
 * @author Silvan Wyss
 */
public final class DurationFinder {
  private DurationFinder() {
  }

  /**
   * Runs the given action.
   * 
   * @param action
   * @return the duration of the given action in milliseconds
   */
  public static long getDurationOfActionInMilliseconds(final Runnable action) {
    final var stopWatch = StopWatch.createStartedStopWatch();

    action.run();

    return stopWatch.getTotalRunningTimeInMilliseconds();
  }
}
