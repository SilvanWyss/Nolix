/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.errorcontrol.errormapping;

import ch.nolix.base.foundation.linkedlist.SimpleLinkedList;
import ch.nolix.baseapi.errorcontrol.errormapping.IErrorMessageExtractor;

/**
 * @author Silvan Wyss
 */
public final class StackTraceMapper {
  private static final IErrorMessageExtractor ERROR_MESSAGE_EXTRACTOR = new ErrorMessageExtractor();

  private StackTraceMapper() {
  }

  public static String[] mapErrorToStackTrace(final Throwable error) {
    final SimpleLinkedList<String> list = SimpleLinkedList.createEmpty();

    mapStackTraceElementsOfErrorIntoList(error, list);

    var cause = error.getCause();
    while (cause != null) {
      mapCauseStackTraceIntoList(cause, list);
      cause = cause.getCause();
    }

    return SimpleLinkedList.createArrayFromList(list);
  }

  private static void mapCauseStackTraceIntoList(final Throwable cause, final SimpleLinkedList<String> list) {
    final var errorMessage = ERROR_MESSAGE_EXTRACTOR.getMessageOfError(cause);

    list.addAtEnd("Cause: " + cause.getClass().getSimpleName() + ": " + errorMessage);

    mapStackTraceElementsOfErrorIntoList(cause, list);
  }

  private static void mapStackTraceElementsOfErrorIntoList(
    final Throwable error,
    final SimpleLinkedList<String> list) {
    for (final var t : error.getStackTrace()) {
      list.addAtEnd(t.toString());
    }
  }
}
