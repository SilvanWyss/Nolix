package ch.nolix.core.errorcontrol.errormapping;

import ch.nolix.core.independent.list.List;

public final class StackTraceMapper {

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

  private String getMessageFromError(final Throwable error) {

    if (error == null) {
      return "An error occured.";
    }

    final var message = error.getMessage();
    if (message == null || message.isBlank()) {
      return "An error occured.";
    }

    return message;
  }

  private void mapCauseStackTraceIntoList(final Throwable cause, final List<String> list) {

    list.addAtEnd("Cause: " + cause.getClass().getSimpleName() + ": " + getMessageFromError(cause));

    mapOwnStackTraceElementsOfErrorIntoList(cause, list);
  }

  private void mapOwnStackTraceElementsOfErrorIntoList(final Throwable cause, final List<String> list) {
    for (final var ste : cause.getStackTrace()) {
      list.addAtEnd(ste.toString());
    }
  }
}
