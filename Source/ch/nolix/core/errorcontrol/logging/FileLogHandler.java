package ch.nolix.core.errorcontrol.logging;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import ch.nolix.core.environment.nolixenvironment.GlobalNolixEnvironmentProvider;
import ch.nolix.core.errorcontrol.exception.WrapperException;

public final class FileLogHandler extends LogHandler {

  @Override
  protected void log(final LogEntry logEntry) {
    try {

      final var nolixLogFilePath = Path.of(GlobalNolixEnvironmentProvider.getNolixLogFilePath());

      Files.writeString(
        nolixLogFilePath,
        logEntry.toString() + System.lineSeparator(),
        StandardOpenOption.APPEND);

      for (final var ail : logEntry.getAdditionalInfoLines()) {
        Files.writeString(
          nolixLogFilePath,
          "  " + ail + System.lineSeparator(),
          StandardOpenOption.APPEND);
      }
    } catch (final IOException pIOException) {
      throw WrapperException.forError(pIOException);
    }
  }
}
