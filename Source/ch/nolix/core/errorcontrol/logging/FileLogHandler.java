/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.errorcontrol.logging;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import ch.nolix.core.errorcontrol.generalexception.WrapperException;
import ch.nolix.core.independent.nolixenvironment.NolixEnvironmentService;

/**
 * @author Silvan Wyss
 */
public final class FileLogHandler extends AbstractLogHandler {
  @Override
  protected void log(final LogEntry logEntry) {
    try {
      final var nolixLogFilePath = Path.of(NolixEnvironmentService.getNolixLogFilePath());

      Files.writeString(
        nolixLogFilePath,
        logEntry.toString() + System.lineSeparator(),
        StandardOpenOption.APPEND);

      for (final var l : logEntry.getAdditionalInfoLines()) {
        Files.writeString(
          nolixLogFilePath,
          "  " + l + System.lineSeparator(),
          StandardOpenOption.APPEND);
      }
    } catch (final IOException pIOException) {
      throw WrapperException.forError(pIOException);
    }
  }
}
