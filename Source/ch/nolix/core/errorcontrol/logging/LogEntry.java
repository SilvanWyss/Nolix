package ch.nolix.core.errorcontrol.logging;

import ch.nolix.core.errorcontrol.errormapping.StackTraceMapper;
import ch.nolix.core.independent.list.ImmutableList;
import ch.nolix.coreapi.errorcontrol.logging.HarmLevel;

/**
 * @author Silvan Wyss
 */
public final class LogEntry {
  private final String message;

  private final HarmLevel harmLevel;

  private final long creationTimeInMillisecondsSince1970;

  private final ImmutableList<String> additionalInfoLines;

  private LogEntry(final HarmLevel harmLevel, final String message, final String[] additionalInfoLines) {
    creationTimeInMillisecondsSince1970 = System.currentTimeMillis();

    if (harmLevel == null) {
      this.harmLevel = HarmLevel.ERROR;
    } else {
      this.harmLevel = harmLevel;
    }

    if (message == null) {
      this.message = "Error.";
    } else {
      this.message = message;
    }

    if (additionalInfoLines == null) {
      this.additionalInfoLines = ImmutableList.createEmptyList();
    } else {
      this.additionalInfoLines = ImmutableList.withElements(additionalInfoLines);
    }
  }

  public static LogEntry forError(final Throwable error) {
    return new LogEntry(HarmLevel.ERROR, getMessageFromError(error), getAdditionalInfoLinesFromError(error));
  }

  public static LogEntry withMessageAndHarmLevel(final String message, final HarmLevel harmLevel) {
    return new LogEntry(harmLevel, message, new String[0]);
  }

  private static String[] getAdditionalInfoLinesFromError(Throwable error) {
    return StackTraceMapper.mapErrorToStackTrace(error);
  }

  private static String getMessageFromError(final Throwable error) {
    if (error == null) {
      return "An error occured.";
    }

    final var message = error.getMessage();
    if (message == null || message.isBlank()) {
      return "An error occured.";
    }

    return message;
  }

  public ImmutableList<String> getAdditionalInfoLines() {
    return additionalInfoLines;
  }

  public long getCreationTimeInMillisecondsSince1970() {
    return creationTimeInMillisecondsSince1970;
  }

  public HarmLevel getHarmLevel() {
    return harmLevel;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public String toString() {
    return (getHarmLevel() + ": " + getMessage());
  }
}
