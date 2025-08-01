package ch.nolix.core.misc.stopwatch;

public final class RuntimeMeter {

  public long getRuntimeOfActionInMilliseconds(final Runnable action) {

    final var stopWatch = StopWatch.createStartedStopWatch();

    action.run();

    return stopWatch.getTotalRunningTimeInMilliseconds();
  }
}
