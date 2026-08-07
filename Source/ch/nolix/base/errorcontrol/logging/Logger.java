/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.errorcontrol.logging;

import ch.nolix.base.foundation.linkedlist.SimpleLinkedList;
import ch.nolix.baseapi.errorcontrol.logging.HarmLevel;

/**
 * @author Silvan Wyss
 */
public final class Logger {
  // static attribute
  private static boolean active = true;

  // static attribute
  private static LogWorker logWorker;

  // static multi-attribute
  private static final SimpleLinkedList<AbstractLogHandler> abstractLogHandlers = SimpleLinkedList.createEmpty();

  private Logger() {
  }

  // static initialization
  static {
    abstractLogHandlers.addAtEnd(new StandardConsoleLogHandler());
    abstractLogHandlers.addAtEnd(new FileLogHandler());
  }

  public static void addLogHandler(final AbstractLogHandler abstractLogHandler) {
    abstractLogHandlers.addAtEnd(abstractLogHandler);
  }

  public static synchronized void disable() {
    if (active) {
      active = false;

      if (logWorker != null) {
        logWorker.inactivate();
        logWorker = null;
      }
    }
  }

  public static synchronized void enable() {
    if (!active) {
      active = true;
    }
  }

  public static boolean isActive() {
    return active;
  }

  public static void logError(final Throwable error) {
    if (active) {
      synchronized (Logger.class) {
        if (logWorker == null) {
          logWorker = new LogWorker();
        }
      }

      final var logEntry = LogEntry.forError(error);
      logWorker.takeLogEntry(logEntry);
    }
  }

  public static void logError(final String error) {
    if (active) {
      synchronized (Logger.class) {
        if (logWorker == null) {
          logWorker = new LogWorker();
        }
      }

      logWorker.takeLogEntry(
        LogEntry.withMessageAndHarmLevel(
          error,
          HarmLevel.ERROR));
    }
  }

  public static void logError(
    final String valueName,
    final double value,
    final String errorPredicate) {
    if (active) {
      synchronized (Logger.class) {
        if (logWorker == null) {
          logWorker = new LogWorker();
        }
      }

      logWorker.takeLogEntry(
        LogEntry.withMessageAndHarmLevel(
          "The " + valueName + " " + value + " " + errorPredicate + ".",
          HarmLevel.ERROR));
    }
  }

  public static void logError(
    final String valueName,
    final long value,
    final String errorPredicate) {
    if (active) {
      synchronized (Logger.class) {
        if (logWorker == null) {
          logWorker = new LogWorker();
        }
      }

      logWorker.takeLogEntry(
        LogEntry.withMessageAndHarmLevel(
          "The " + valueName + " " + value + " " + errorPredicate + ".",
          HarmLevel.ERROR));
    }
  }

  public static void logFatalError(final String fatalError) {
    if (active) {
      synchronized (Logger.class) {
        if (logWorker == null) {
          logWorker = new LogWorker();
        }
      }

      logWorker.takeLogEntry(
        LogEntry.withMessageAndHarmLevel(
          fatalError,
          HarmLevel.FATAL_ERROR));
    }
  }

  public static void logInfo(final String info) {
    if (active) {
      synchronized (Logger.class) {
        if (logWorker == null) {
          logWorker = new LogWorker();
        }
      }

      logWorker.takeLogEntry(
        LogEntry.withMessageAndHarmLevel(
          info,
          HarmLevel.INFO));
    }
  }

  public static void logInfo(final String valueName, final double value) {
    if (active) {
      synchronized (Logger.class) {
        if (logWorker == null) {
          logWorker = new LogWorker();
        }
      }

      logWorker.takeLogEntry(
        LogEntry.withMessageAndHarmLevel(
          valueName + " " + value,
          HarmLevel.INFO));
    }
  }

  public static void logInfo(final String valueName, final long value) {
    if (active) {
      synchronized (Logger.class) {
        if (logWorker == null) {
          logWorker = new LogWorker();
        }
      }

      logWorker.takeLogEntry(
        LogEntry.withMessageAndHarmLevel(
          valueName + " " + value,
          HarmLevel.INFO));
    }
  }

  public static void logWarning(final String warning) {
    if (active) {
      synchronized (Logger.class) {
        if (logWorker == null) {
          logWorker = new LogWorker();
        }
      }

      logWorker.takeLogEntry(
        LogEntry.withMessageAndHarmLevel(
          warning,
          HarmLevel.WARNING));
    }
  }

  static void takeLogEntry(final LogEntry logEntry) {
    for (final AbstractLogHandler lh : abstractLogHandlers) {
      lh.takeLogEntry(logEntry);
    }
  }

  static synchronized void removeLogWorker() {
    logWorker = null;
  }
}
