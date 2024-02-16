//package declaration
package ch.nolix.coreapi.environmentapi.nolixenvironmentapi;

//Java imports
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

//class
public final class NolixDirectoryAndFileCatalogue {

  //constant
  public static final String NOLIX_DIRECTORY_NAME = "Nolix";

  //constant
  public static final String NOLIX_LICENSES_DIRECTORY_NAME = "Licenses";

  //constant
  public static final String NOLIX_CONFIGURATION_FILE_NAME = "nolix_configuration.txt";

  //constant
  public static final String NOLI_LOG_FILE_NAME = "log.txt";

  //constant
  public static final String NOLIX_DIRECTORY_PATH = getNolixDirectoryPath();

  //constant
  public static final String NOLIX_LICENSES_DIRECTORY_PATH = getNolixLicensesDirectoryPath();

  //constant
  public static final String NOLIX_CONFIGURATION_FILE_PATH = getNolixConfigurationFilePath();

  //constant
  public static final String NOLIX_LOG_FILE_PATH = getNolixLogFilePath();

  //constant
  private static final String APP_DATA = "APPDATA";

  //constant
  private static final char FOLDER_DELIMITER = '/';

  //constructor
  private NolixDirectoryAndFileCatalogue() {
  }

  //static method
  private static String getNolixDirectoryPath() {

    final var nolixDirectoryPath = getAppDataDirectoryPath() + FOLDER_DELIMITER + NOLIX_DIRECTORY_NAME;

    createDirectoryIfDoesNotExist(nolixDirectoryPath);

    return nolixDirectoryPath;
  }

  //static method
  private static String getAppDataDirectoryPath() {
    return System.getenv(APP_DATA);
  }

  //method
  private static void createDirectoryIfDoesNotExist(final String path) {

    final var localPath = Path.of(path);

    if (!Files.exists(localPath)) {
      createDirectoryWhenDoesNotExist(localPath);
    }
  }

  //method
  private static void createDirectoryWhenDoesNotExist(final Path lPath) {
    try {
      Files.createDirectory(lPath);
    } catch (final IOException ioException) {
      throw new UncheckedIOException(ioException);
    }
  }

  //static method
  private static String getNolixLicensesDirectoryPath() {
    return (NOLIX_DIRECTORY_PATH + FOLDER_DELIMITER + NOLIX_LICENSES_DIRECTORY_NAME);
  }

  //static method
  private static String getNolixConfigurationFilePath() {
    return (getNolixDirectoryPath() + FOLDER_DELIMITER + NOLIX_CONFIGURATION_FILE_NAME);
  }

  //static method
  private static String getNolixLogFilePath() {
    return (getNolixDirectoryPath() + FOLDER_DELIMITER + NOLI_LOG_FILE_NAME);
  }
}
