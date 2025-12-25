package ch.nolix.core.errorcontrol.errormapping;

import ch.nolix.core.independent.list.List;
import ch.nolix.coreapi.errorcontrol.errormapping.IErrorMessageExtractor;

/**
 * @author Silvan Wyss
 */
public final class StackTraceMapper {
  private static final IErrorMessageExtractor ERROR_MESSAGE_EXTRACTOR = new ErrorMessageExtractor();

  private StackTraceMapper() {
  }

  public static String[] mapErrorToStackTrace(final Throwable error) {
    final var list = new List<String>();

    mapOwnStackTraceElementsOfErrorIntoList(error, list);

    var cause = error.getCause();
    while (cause != null) {
      mapCauseStackTraceIntoList(cause, list);
      cause = cause.getCause();
    }

    return List.createArrayFromList(list);
  }

  private static void mapCauseStackTraceIntoList(final Throwable cause, final List<String> list) {
    final var errorMessage = ERROR_MESSAGE_EXTRACTOR.getMessageOfError(cause);

    list.addAtEnd("Cause: " + cause.getClass().getSimpleName() + ": " + errorMessage);

    mapOwnStackTraceElementsOfErrorIntoList(cause, list);
  }

  private static void mapOwnStackTraceElementsOfErrorIntoList(final Throwable cause, final List<String> list) {
    for (final var t : cause.getStackTrace()) {
      list.addAtEnd(t.toString());
    }
  }
}
