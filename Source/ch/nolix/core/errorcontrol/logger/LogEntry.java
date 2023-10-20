//package declaration
package ch.nolix.core.errorcontrol.logger;

//own imports
import ch.nolix.core.errorcontrol.errormapping.StackTraceMapper;
import ch.nolix.core.independent.container.ImmutableList;

//class
public final class LogEntry {

  //constant
  private static final StackTraceMapper STACK_TRACE_MAPPER = new StackTraceMapper();

  //attribute
  private final String message;

  //attribute
  private final HarmLevel harmLevel;

  //attribute
  private final long creationTimeInMillisecondsSince1970;

  //multi-attribute
  private final ImmutableList<String> additionalInfoLines;

  //constructor
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

  //static method
  public static LogEntry forError(final Throwable error) {
    return new LogEntry(HarmLevel.ERROR, getMessageFromError(error), getAdditionalInfoLinesFromError(error));
  }

  //static method
  public static LogEntry withMessageAndHarmLevel(final String message, final HarmLevel harmLevel) {
    return new LogEntry(harmLevel, message, new String[0]);
  }

  //static method
  private static String[] getAdditionalInfoLinesFromError(Throwable error) {
    return STACK_TRACE_MAPPER.mapErrorToStackTrace(error);
  }

  //method
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

  //method
  public ImmutableList<String> getAdditionalInfoLines() {
    return additionalInfoLines;
  }

  //method
  public long getCreationTimeInMillisecondsSince1970() {
    return creationTimeInMillisecondsSince1970;
  }

  //method
  public HarmLevel getHarmLevel() {
    return harmLevel;
  }

  //method
  public String getMessage() {
    return message;
  }

  //method
  @Override
  public String toString() {
    return (getHarmLevel() + ": " + getMessage());
  }
}
