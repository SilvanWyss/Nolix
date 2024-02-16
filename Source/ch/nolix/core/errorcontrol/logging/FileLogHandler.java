//package declaration
package ch.nolix.core.errorcontrol.logging;

//Java imports
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

//own imports
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.independent.nolixenvironment.GlobalNolixEnvironmentTool;

//class
public final class FileLogHandler extends LogHandler {

  //method
  @Override
  protected void log(final LogEntry logEntry) {
    try {

      Files.writeString(
        Paths.get(GlobalNolixEnvironmentTool.NOLIX_LOG_FILE_PATH),
        logEntry.toString() + System.lineSeparator(),
        StandardOpenOption.APPEND);

      for (final var ail : logEntry.getAdditionalInfoLines()) {
        Files.writeString(
          Paths.get(GlobalNolixEnvironmentTool.NOLIX_LOG_FILE_PATH),
          "  " + ail + System.lineSeparator(),
          StandardOpenOption.APPEND);
      }
    } catch (final IOException pIOException) {
      throw WrapperException.forError(pIOException);
    }
  }
}
