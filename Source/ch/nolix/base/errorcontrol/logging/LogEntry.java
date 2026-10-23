/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.errorcontrol.logging;

import ch.nolix.base.errorcontrol.errormapping.StackTraceMapper;
import ch.nolix.base.foundation.immutablelist.SimpleImmutableList;
import ch.nolix.baseapi.errorcontrol.logging.HarmLevel;

/**
 * @author Silvan Wyss
 */
public final class LogEntry {
  private final String message;

  private final HarmLevel harmLevel;

  private final long creationTimeInMillisecondsSince1970;

  private final SimpleImmutableList<String> additionalInfoLines;

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
      this.additionalInfoLines = SimpleImmutableList.createEmptyList();
    } else {
      this.additionalInfoLines = SimpleImmutableList.withElements(additionalInfoLines);
    }
  }

  /**
   * @param error
   * @return a new {@link LogEntry} for the given error
   */
  public static LogEntry forError(final Throwable error) {
    return new LogEntry(HarmLevel.ERROR, getMessageFromError(error), getAdditionalInfoLinesFromError(error));
  }

  /**
   * @param message
   * @param harmLevel
   * @return a new {@link LogEntry} with the given message and harmLevel
   */
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

  /**
   * @return the additional info lines of the current {@link LogEntry}
   */
  public SimpleImmutableList<String> getAdditionalInfoLines() {
    return additionalInfoLines;
  }

  /**
   * @return the creation time of the current {@link LogEntry} in milliseconds
   *         since 1970
   */
  public long getCreationTimeInMillisecondsSince1970() {
    return creationTimeInMillisecondsSince1970;
  }

  /**
   * @return the {@link HarmLevel} of the current {@link LogEntry}
   */
  public HarmLevel getHarmLevel() {
    return harmLevel;
  }

  /**
   * @return the message of the current {@link LogEntry}
   */
  public String getMessage() {
    return message;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return getHarmLevel() + ": " + getMessage();
  }
}
