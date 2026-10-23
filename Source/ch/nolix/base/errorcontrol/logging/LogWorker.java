/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.errorcontrol.logging;

import ch.nolix.base.foundation.linkedlist.SimpleLinkedList;

final class LogWorker extends Thread {
  private boolean active = true;

  private final SimpleLinkedList<LogEntry> logEntries = SimpleLinkedList.createEmpty();

  public LogWorker() {
    start();
  }

  /**
   * @return true if the current {@link LogWorker} contains log entries, false
   *         otherwise
   */
  public synchronized boolean containsLogEntries() {
    return !logEntries.isEmpty();
  }

  /**
   * Inactivates the current {@link LogWorker}.
   */
  public synchronized void inactivate() {
    active = false;
  }

  /**
   * {@inheritDoc}
   */
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

  /**
   * Lets the current {@link LogWorker} take the given logEntry.
   * 
   * @param logEntry
   */
  public synchronized void takeLogEntry(final LogEntry logEntry) {
    logEntries.addAtEnd(logEntry);
  }

  private synchronized LogEntry getAndRemoveNextLogEntry() {
    final var logEntry = logEntries.getStoredFirst();
    logEntries.removeFirst();
    return logEntry;
  }
}
