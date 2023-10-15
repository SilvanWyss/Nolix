//package declaration
package ch.nolix.core.errorcontrol.errormapping;

//own imports
import ch.nolix.core.independent.container.List;

//class
public final class StackTraceMapper {

  // method
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

  // method
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

  // method
  private void mapCauseStackTraceIntoList(final Throwable cause, final List<String> list) {

    list.addAtEnd("Cause: " + cause.getClass().getSimpleName() + ": " + getMessageFromError(cause));

    mapOwnStackTraceElementsOfErrorIntoList(cause, list);
  }

  // method
  private void mapOwnStackTraceElementsOfErrorIntoList(final Throwable cause, final List<String> list) {
    for (final var ste : cause.getStackTrace()) {
      list.addAtEnd(ste.toString());
    }
  }
}
