package ch.nolix.core.errorcontrol.logging;

import ch.nolix.coreapi.errorcontrolapi.loggingapi.HarmLevel;

public abstract class LogHandler {

  public static final HarmLevel DEFAULT_MIN_HARM_LEVEL = HarmLevel.INFO;

  private final HarmLevel minHarmLevel;

  protected LogHandler() {
    this(DEFAULT_MIN_HARM_LEVEL);
  }

  protected LogHandler(final HarmLevel minHarmLevel) {

    if (minHarmLevel == null) {
      throw new IllegalArgumentException("The given min harm level is null.");
    }

    this.minHarmLevel = minHarmLevel;
  }

  public final HarmLevel getMinHarmLevel() {
    return minHarmLevel;
  }

  public final boolean wouldLog(final LogEntry logEntry) {
    return (logEntry != null && !logEntry.getHarmLevel().isLowerThan(getMinHarmLevel()));
  }

  protected abstract void log(final LogEntry logEntry);

  final void takeLogEntry(final LogEntry logEntry) {
    if (wouldLog(logEntry)) {
      logSafely(logEntry);
    }
  }

  private void logSafely(final LogEntry logEntry) {
    try {
      log(logEntry);
    } catch (final Throwable error) { //NOSONAR: All Throwables must be caught.
      System.err.println("An error occured by writing a log entry."); //NOSONAR: This is a logger.
    }
  }
}
