//package declaration
package ch.nolix.core.programcontrol.stopwatch;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;

//class
public final class StopWatch {

  //attribute
  private long totalRunningTimeInMilliseconds;

  //attribute
  private boolean running;

  //attribute
  private long latestStartInMilliseconds;

  //constructor
  private StopWatch() {
  }

  //static method
  public static StopWatch createStandingStopWatch() {
    return new StopWatch();
  }

  //static method
  public static StopWatch createStartedStopWatch() {

    final var stopWatch = new StopWatch();

    stopWatch.start();

    return stopWatch;
  }

  //method
  public synchronized long getTotalRunningTimeInMilliseconds() {

    if (isRunning()) {

      final var durationInMilliseconds = System.currentTimeMillis() - latestStartInMilliseconds;

      return (totalRunningTimeInMilliseconds + durationInMilliseconds);
    }

    return totalRunningTimeInMilliseconds;
  }

  //method
  public synchronized boolean isRunning() {
    return running;
  }

  //method
  public synchronized void stop() {

    assertIsRunning();

    final var durationInMilliseconds = System.currentTimeMillis() - latestStartInMilliseconds;
    totalRunningTimeInMilliseconds += durationInMilliseconds;
    running = false;
  }

  //method
  public synchronized long stopAndGetMillisecondsSinceLatestStart() {

    assertIsRunning();

    final var durationInMilliseconds = System.currentTimeMillis() - latestStartInMilliseconds;
    totalRunningTimeInMilliseconds += durationInMilliseconds;
    running = false;

    return durationInMilliseconds;
  }

  //method
  public synchronized long stopAndGetTotalRunningTimeInMilliseconds() {

    assertIsRunning();

    final var durationInMilliseconds = System.currentTimeMillis() - latestStartInMilliseconds;
    totalRunningTimeInMilliseconds += durationInMilliseconds;
    running = false;

    return totalRunningTimeInMilliseconds;
  }

  //method
  public synchronized void start() {

    assertIsNotRunning();

    latestStartInMilliseconds = System.currentTimeMillis();
    running = true;
  }

  //method
  private void assertIsNotRunning() {
    if (isRunning()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is started");
    }
  }

  //method
  private void assertIsRunning() {
    if (!isRunning()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is not started");
    }
  }
}
