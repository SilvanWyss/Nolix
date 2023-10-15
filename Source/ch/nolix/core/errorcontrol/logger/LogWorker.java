//package declaration
package ch.nolix.core.errorcontrol.logger;

import ch.nolix.core.independent.container.List;

//class
final class LogWorker extends Thread {

  // attribute
  private boolean active = true;

  // multi-attribute
  private final List<LogEntry> logEntries = new List<>();

  // constructor
  public LogWorker() {
    start();
  }

  // method
  @Override
  public void run() {

    var idle = false;
    long startTimeOfLastIdleInMilliseconds = -1;

    while (active) {
      if (containsLogEntries()) {
        idle = false;
        GlobalLogger.takeLogEntry(getAndRemoveNextLogEntry());
      } else {

        if (!idle) {
          idle = true;
          startTimeOfLastIdleInMilliseconds = System.currentTimeMillis();
        }

        if (System.currentTimeMillis() > startTimeOfLastIdleInMilliseconds + 1000) {
          active = false;
        }
      }
    }

    GlobalLogger.removeLogWorker();
  }

  // method
  public synchronized boolean containsLogEntries() {
    return !logEntries.isEmpty();
  }

  // method
  public synchronized void stop_() {
    active = false;
  }

  // method
  public synchronized void takeLogEntry(final LogEntry logEntry) {
    logEntries.addAtBegin(logEntry);
  }

  // method
  private synchronized LogEntry getAndRemoveNextLogEntry() {
    final var logEntry = logEntries.getStoredFirst();
    logEntries.removeFirst();
    return logEntry;
  }
}
