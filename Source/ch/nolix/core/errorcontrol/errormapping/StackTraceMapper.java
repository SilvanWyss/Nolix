package ch.nolix.core.errorcontrol.errormapping;

import ch.nolix.core.independent.list.List;
import ch.nolix.coreapi.errorcontrol.errormapping.IErrorMessageExtractor;

public final class StackTraceMapper {
  private static final IErrorMessageExtractor ERROR_MESSAGE_EXTRACTOR = new ErrorMessageExtractor();

  public String[] mapErrorToStackTrace(final Throwable error) {
    final var list = new List<String>();

    mapOwnStackTraceElementsOfErrorIntoList(error, list);

    var cause = error.getCause();
    while (cause != null) {
      mapCauseStackTraceIntoList(cause, list);
      cause = cause.getCause();
    }

    return List.createArrayFromList(list);
  }

  private void mapCauseStackTraceIntoList(final Throwable cause, final List<String> list) {
    final var errorMessage = ERROR_MESSAGE_EXTRACTOR.getMessageOfError(cause);

    list.addAtEnd("Cause: " + cause.getClass().getSimpleName() + ": " + errorMessage);

    mapOwnStackTraceElementsOfErrorIntoList(cause, list);
  }

  private void mapOwnStackTraceElementsOfErrorIntoList(final Throwable cause, final List<String> list) {
    for (final var ste : cause.getStackTrace()) {
      list.addAtEnd(ste.toString());
    }
  }
}
