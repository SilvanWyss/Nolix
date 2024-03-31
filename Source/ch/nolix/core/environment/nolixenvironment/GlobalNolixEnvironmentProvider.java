//package declaration
package ch.nolix.core.environment.nolixenvironment;

//Java imports
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

//own imports
import ch.nolix.coreapi.environmentapi.nolixenvironmentapi.NolixDirectoryAndFileCatalogue;

//class
public final class GlobalNolixEnvironmentProvider {

  //constructor
  private GlobalNolixEnvironmentProvider() {
  }

  //static method
  public static String getNolixConfigurationFilePath() {

    createDirectoryIfDoesNotExist(NolixDirectoryAndFileCatalogue.NOLIX_CONFIGURATION_FILE_PATH);

    return NolixDirectoryAndFileCatalogue.NOLIX_CONFIGURATION_FILE_PATH;
  }

  //static method
  public static String getNolixDirectoryPath() {

    createDirectoryIfDoesNotExist(NolixDirectoryAndFileCatalogue.NOLIX_DIRECTORY_PATH);

    return NolixDirectoryAndFileCatalogue.NOLIX_DIRECTORY_PATH;
  }

  //static method
  public static String getNolixLicensesDirectoryPath() {

    createDirectoryIfDoesNotExist(NolixDirectoryAndFileCatalogue.NOLIX_LICENSES_DIRECTORY_NAME);

    return NolixDirectoryAndFileCatalogue.NOLIX_LICENSES_DIRECTORY_NAME;
  }

  //static method
  public static String getNolixLogFilePath() {

    createFileIfDoesNotExist(NolixDirectoryAndFileCatalogue.NOLIX_LOG_FILE_PATH);

    return NolixDirectoryAndFileCatalogue.NOLIX_LOG_FILE_PATH;
  }

  //method
  private static void createDirectoryIfDoesNotExist(final String path) {

    final var localPath = Path.of(path);

    createDirectoryIfDoesNotExist(localPath);
  }

  //method
  private static void createDirectoryIfDoesNotExist(final Path path) {
    if (!Files.exists(path)) {
      createDirectoryWhenDoesNotExist(path);
    }
  }

  //method
  private static void createDirectoryWhenDoesNotExist(final Path path) {
    try {
      Files.createDirectory(path);
    } catch (final IOException ioException) {
      throw new UncheckedIOException(ioException);
    }
  }

  //method
  private static void createFileIfDoesNotExist(final String filePath) {

    final var localFilePath = Path.of(filePath);

    createFileIfDoesNotExist(localFilePath);
  }

  //method
  private static void createFileIfDoesNotExist(final Path filePath) {
    if (!Files.exists(filePath)) {
      createFileWhenDoesNotExist(filePath);
    }
  }

  //method
  private static void createFileWhenDoesNotExist(final Path filePath) {
    try {
      Files.createFile(filePath);
    } catch (final IOException ioException) {
      throw new UncheckedIOException(ioException);
    }
  }
}
