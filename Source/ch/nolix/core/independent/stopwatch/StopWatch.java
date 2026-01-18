/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.independent.stopwatch;

/**
 * @author Silvan Wyss
 */
public final class StopWatch {
  private long totalRunningTimeInMilliseconds;

  private boolean running;

  private long latestStartInMilliseconds;

  private StopWatch() {
  }

  public static StopWatch createStandingStopWatch() {
    return new StopWatch();
  }

  public static StopWatch createStartedStopWatch() {
    final var stopWatch = new StopWatch();

    stopWatch.start();

    return stopWatch;
  }

  public synchronized long getTotalRunningTimeInMilliseconds() {
    if (isRunning()) {
      final var durationInMilliseconds = System.currentTimeMillis() - latestStartInMilliseconds;

      return (totalRunningTimeInMilliseconds + durationInMilliseconds);
    }

    return totalRunningTimeInMilliseconds;
  }

  public synchronized boolean isRunning() {
    return running;
  }

  public synchronized void stop() {
    assertIsRunning();

    final var durationInMilliseconds = System.currentTimeMillis() - latestStartInMilliseconds;
    totalRunningTimeInMilliseconds += durationInMilliseconds;
    running = false;
  }

  public synchronized long stopAndGetMillisecondsSinceLatestStart() {
    assertIsRunning();

    final var durationInMilliseconds = System.currentTimeMillis() - latestStartInMilliseconds;
    totalRunningTimeInMilliseconds += durationInMilliseconds;
    running = false;

    return durationInMilliseconds;
  }

  public synchronized long stopAndGetTotalRunningTimeInMilliseconds() {
    assertIsRunning();

    final var durationInMilliseconds = System.currentTimeMillis() - latestStartInMilliseconds;
    totalRunningTimeInMilliseconds += durationInMilliseconds;
    running = false;

    return totalRunningTimeInMilliseconds;
  }

  public synchronized void start() {
    assertIsNotRunning();

    latestStartInMilliseconds = System.currentTimeMillis();
    running = true;
  }

  private void assertIsNotRunning() {
    if (isRunning()) {
      throw new IllegalStateException("The current StopWatch is started.");
    }
  }

  private void assertIsRunning() {
    if (!isRunning()) {
      throw new IllegalStateException("The current StopWatch is not running.");
    }
  }
}
