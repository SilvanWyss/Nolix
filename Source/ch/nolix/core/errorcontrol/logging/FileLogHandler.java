//package declaration
package ch.nolix.core.errorcontrol.logging;

//Java imports
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

//own imports
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.independent.nolixenvironment.GlobalNolixEnvironmentHelper;

//class
public final class FileLogHandler extends LogHandler {

  //constant
  public static final String NOLIX_LOG_FILE_NAME = "nolix_log.txt";

  //constant
  private static final char FOLDER_DELIMITER = '/';

  //static method
  public static String getLocalNolixLogFilePath() {

    final var localNolixFilePath = GlobalNolixEnvironmentHelper.getLocalNolixFolderPath() + FOLDER_DELIMITER
    + NOLIX_LOG_FILE_NAME;
    createFileIfDoesNotExist(localNolixFilePath);

    return localNolixFilePath;
  }

  //static method
  private static void createFileIfDoesNotExist(final String path) {

    final var lPath = Path.of(path);

    if (!Files.exists(lPath)) {
      try {
        Files.createFile(lPath);
      } catch (final IOException pIOException) {
        throw WrapperException.forError(pIOException);
      }
    }
  }

  //method
  @Override
  protected void log(final LogEntry logEntry) {
    try {

      Files.writeString(
        Paths.get(getLocalNolixLogFilePath()),
        logEntry.toString() + System.lineSeparator(),
        StandardOpenOption.APPEND);

      for (final var ail : logEntry.getAdditionalInfoLines()) {
        Files.writeString(
          Paths.get(getLocalNolixLogFilePath()),
          "  " + ail + System.lineSeparator(),
          StandardOpenOption.APPEND);
      }
    } catch (final IOException pIOException) {
      throw WrapperException.forError(pIOException);
    }
  }
}
