//package declaration
package ch.nolix.core.programcontrol.stopwatch;

//class
public final class RuntimeMeter {

  //method
  public long getRuntimeOfActionInMilliseconds(final Runnable action) {

    final var stopWatch = StopWatch.createStartedStopWatch();

    action.run();

    return stopWatch.getTotalRunningTimeInMilliseconds();
  }
}
