package ch.nolix.core.errorcontrol.logging;

import ch.nolix.core.independent.list.List;

final class LogWorker extends Thread {
  private boolean active = true;

  private final List<LogEntry> logEntries = new List<>();

  public LogWorker() {
    start();
  }

  @Override
  public void run() {
    var idle = false;
    long startTimeOfLastIdleInMilliseconds = -1;

    while (active) {
      if (containsLogEntries()) {
        idle = false;
        Logger.takeLogEntry(getAndRemoveNextLogEntry());
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

    Logger.removeLogWorker();
  }

  public synchronized boolean containsLogEntries() {
    return !logEntries.isEmpty();
  }

  public synchronized void inactivate() {
    active = false;
  }

  public synchronized void takeLogEntry(final LogEntry logEntry) {
    logEntries.addAtBegin(logEntry);
  }

  private synchronized LogEntry getAndRemoveNextLogEntry() {
    final var logEntry = logEntries.getStoredFirst();
    logEntries.removeFirst();
    return logEntry;
  }
}
