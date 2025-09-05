package ch.nolix.core.errorcontrol.logging;

public final class StandardConsoleLogHandler extends AbstractLogHandler {
  @Override
  protected void log(final LogEntry logEntry) {
    System.out.println(logEntry.toString()); //NOSONAR: This is a logger.

    for (final var ail : logEntry.getAdditionalInfoLines()) {
      System.out.println("  " + ail); //NOSONAR: This is a logger.
    }
  }
}
