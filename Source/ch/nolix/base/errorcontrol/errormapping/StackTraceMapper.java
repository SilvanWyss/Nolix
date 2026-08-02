/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.errorcontrol.errormapping;

import ch.nolix.base.independent.linkedlist.LinkedList;
import ch.nolix.baseapi.errorcontrol.errormapping.IErrorMessageExtractor;

/**
 * @author Silvan Wyss
 */
public final class StackTraceMapper {
  private static final IErrorMessageExtractor ERROR_MESSAGE_EXTRACTOR = new ErrorMessageExtractor();

  private StackTraceMapper() {
  }

  public static String[] mapErrorToStackTrace(final Throwable error) {
    final LinkedList<String> list = LinkedList.createEmpty();

    mapOwnStackTraceElementsOfErrorIntoList(error, list);

    var cause = error.getCause();
    while (cause != null) {
      mapCauseStackTraceIntoList(cause, list);
      cause = cause.getCause();
    }

    return LinkedList.createArrayFromList(list);
  }

  private static void mapCauseStackTraceIntoList(final Throwable cause, final LinkedList<String> list) {
    final var errorMessage = ERROR_MESSAGE_EXTRACTOR.getMessageOfError(cause);

    list.addAtEnd("Cause: " + cause.getClass().getSimpleName() + ": " + errorMessage);

    mapOwnStackTraceElementsOfErrorIntoList(cause, list);
  }

  private static void mapOwnStackTraceElementsOfErrorIntoList(final Throwable cause, final LinkedList<String> list) {
    for (final var t : cause.getStackTrace()) {
      list.addAtEnd(t.toString());
    }
  }
}
