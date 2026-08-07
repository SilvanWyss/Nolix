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

  public synchronized boolean containsLogEntries() {
    return !logEntries.isEmpty();
  }

  public synchronized void inactivate() {
    active = false;
  }

  public synchronized void takeLogEntry(final LogEntry logEntry) {
    logEntries.addAtEnd(logEntry);
  }

  private synchronized LogEntry getAndRemoveNextLogEntry() {
    final var logEntry = logEntries.getStoredFirst();
    logEntries.removeFirst();
    return logEntry;
  }
}
