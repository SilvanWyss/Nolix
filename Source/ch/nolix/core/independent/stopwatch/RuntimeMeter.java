package ch.nolix.core.independent.stopwatch;

public final class RuntimeMeter {

  public long getRuntimeOfActionInMilliseconds(final Runnable action) {

    final var stopWatch = StopWatch.createStartedStopWatch();

    action.run();

    return stopWatch.getTotalRunningTimeInMilliseconds();
  }
}
