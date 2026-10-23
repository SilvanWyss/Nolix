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

  /**
   * Adds the given abstractLogHandler to the {@link Logger}.
   * 
   * @param abstractLogHandler
   */
  public static void addLogHandler(final AbstractLogHandler abstractLogHandler) {
    abstractLogHandlers.addAtEnd(abstractLogHandler);
  }

  /**
   * Disables the {@link Logger}.
   */
  public static synchronized void disable() {
    if (active) {
      active = false;

      if (logWorker != null) {
        logWorker.inactivate();
        logWorker = null;
      }
    }
  }

  /**
   * Enables the {@link Logger}.
   */
  public static synchronized void enable() {
    if (!active) {
      active = true;
    }
  }

  /**
   * @return true if the {@link Logger} is active, false otherwise
   */
  public static boolean isActive() {
    return active;
  }

  /**
   * Logs the given error.
   * 
   * @param error
   */
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

  /**
   * Logs the given error.
   * 
   * @param error
   */
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

  /**
   * Logs an error that the value with the given valueName and value fulfills the
   * given errorPredicate.
   * 
   * @param valueName
   * @param value
   * @param errorPredicate
   */
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

  /**
   * Logs an error that the value with the given valueName and value fulfills the
   * given errorPredicate.
   * 
   * @param valueName
   * @param value
   * @param errorPredicate
   */
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

  /**
   * Logs the given fatalError.
   * 
   * @param fatalError
   */
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

  /**
   * Logs the given info.
   * 
   * @param info
   */
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

  /**
   * Logs the given value with the given valueName as info.
   * 
   * @param valueName
   * @param value
   */
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

  /**
   * Logs the given value with the given valueName as info.
   * 
   * @param valueName
   * @param value
   */
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

  /**
   * Logs the given warning.
   * 
   * @param warning
   */
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
