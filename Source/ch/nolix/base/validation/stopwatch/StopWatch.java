/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.stopwatch;

/**
 * @author Silvan Wyss
 */
public final class StopWatch {
  private long totalRunningTimeInMilliseconds;

  private boolean running;

  private long latestStartInMilliseconds;

  private StopWatch() {
  }

  /**
   * @return a new {@link StopWatch} that is not running
   */
  public static StopWatch createStandingStopWatch() {
    return new StopWatch();
  }

  /**
   * @return a new {@link StopWatch} that is running
   */
  public static StopWatch createStartedStopWatch() {
    final var stopWatch = new StopWatch();

    stopWatch.start();

    return stopWatch;
  }

  /**
   * @return the total running time of the current {@link StopWatch} in
   *         milliseconds
   */
  public synchronized long getTotalRunningTimeInMilliseconds() {
    if (isRunning()) {
      final var durationInMilliseconds = System.currentTimeMillis() - latestStartInMilliseconds;

      return totalRunningTimeInMilliseconds + durationInMilliseconds;
    }

    return totalRunningTimeInMilliseconds;
  }

  /**
   * @return true if the current {@link StopWatch} is running, false otherwise
   */
  public synchronized boolean isRunning() {
    return running;
  }

  /**
   * Starts the current {@link StopWatch}.
   * 
   * @throws RuntimeException if the current {@link StopWatch} is running already
   */
  public synchronized void start() {
    assertIsNotRunning();

    latestStartInMilliseconds = System.currentTimeMillis();
    running = true;
  }

  /**
   * Stops the current {@link StopWatch}.
   * 
   * @throws RuntimeException if the current {@link StopWatch} is not running
   */
  public synchronized void stop() {
    assertIsRunning();

    final var durationInMilliseconds = System.currentTimeMillis() - latestStartInMilliseconds;
    totalRunningTimeInMilliseconds += durationInMilliseconds;
    running = false;
  }

  /**
   * Stops the current {@link StopWatch}.
   * 
   * @return the number of milliseconds since the latest start of the current
   *         {@link StopWatch}
   * @throws RuntimeException if the current {@link StopWatch} is not running
   */
  public synchronized long stopAndGetMillisecondsSinceLatestStart() {
    assertIsRunning();

    final var durationInMilliseconds = System.currentTimeMillis() - latestStartInMilliseconds;
    totalRunningTimeInMilliseconds += durationInMilliseconds;
    running = false;

    return durationInMilliseconds;
  }

  /**
   * Stops the current {@link StopWatch}.
   * 
   * @return the total running time of the current {@link StopWatch} in
   *         milliseconds
   * @throws RuntimeException if the current {@link StopWatch} is not running
   */
  public synchronized long stopAndGetTotalRunningTimeInMilliseconds() {
    assertIsRunning();

    final var durationInMilliseconds = System.currentTimeMillis() - latestStartInMilliseconds;
    totalRunningTimeInMilliseconds += durationInMilliseconds;
    running = false;

    return totalRunningTimeInMilliseconds;
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
