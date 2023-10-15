//package declaration
package ch.nolix.core.analysis;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.logger.GlobalLogger;

//class
public final class StopWatch {

  // attribute
  private int finishedRoundCount;

  // attribute
  private long totalRunningTimeInMilliseconds;

  // attribute
  private boolean running;

  // attribute
  private long latestStartInMilliseconds;

  // method
  public synchronized int getFinishedRoundCount() {
    return finishedRoundCount;
  }

  // method
  public synchronized long getTotalRunningTimeInMilliseconds() {
    return totalRunningTimeInMilliseconds;
  }

  // method
  public synchronized boolean isRunning() {
    return running;
  }

  // method
  public synchronized long stopAndGetMillisecondsSinceLatestStart() {

    assertIsRunning();

    final var durationInMilliseconds = System.currentTimeMillis() - latestStartInMilliseconds;
    finishedRoundCount++;
    totalRunningTimeInMilliseconds += durationInMilliseconds;
    running = false;
    GlobalLogger.logInfo("Stop watch run for " + durationInMilliseconds + " ms.");

    return durationInMilliseconds;
  }

  // method
  public synchronized void start() {

    assertIsNotRunning();

    latestStartInMilliseconds = System.currentTimeMillis();
    running = true;
  }

  // method
  // method
  private void assertIsNotRunning() {
    if (running) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is started");
    }
  }

  // method
  private void assertIsRunning() {
    if (!running) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is not started");
    }
  }
}
