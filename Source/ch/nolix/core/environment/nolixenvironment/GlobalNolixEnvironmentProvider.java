package ch.nolix.core.environment.nolixenvironment;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

import ch.nolix.coreapi.environmentapi.nolixenvironmentapi.NolixDirectoryAndFileCatalog;

public final class GlobalNolixEnvironmentProvider {

  private GlobalNolixEnvironmentProvider() {
  }

  public static String getNolixConfigurationFilePath() {

    createDirectoryIfDoesNotExist(NolixDirectoryAndFileCatalog.NOLIX_CONFIGURATION_FILE_PATH);

    return NolixDirectoryAndFileCatalog.NOLIX_CONFIGURATION_FILE_PATH;
  }

  public static String getNolixDirectoryPath() {

    createDirectoryIfDoesNotExist(NolixDirectoryAndFileCatalog.NOLIX_DIRECTORY_PATH);

    return NolixDirectoryAndFileCatalog.NOLIX_DIRECTORY_PATH;
  }

  public static String getNolixLicensesDirectoryPath() {

    createDirectoryIfDoesNotExist(NolixDirectoryAndFileCatalog.NOLIX_LICENSES_DIRECTORY_NAME);

    return NolixDirectoryAndFileCatalog.NOLIX_LICENSES_DIRECTORY_NAME;
  }

  public static String getNolixLogFilePath() {

    createFileIfDoesNotExist(NolixDirectoryAndFileCatalog.NOLIX_LOG_FILE_PATH);

    return NolixDirectoryAndFileCatalog.NOLIX_LOG_FILE_PATH;
  }

  private static void createDirectoryIfDoesNotExist(final String path) {

    final var localPath = Path.of(path);

    createDirectoryIfDoesNotExist(localPath);
  }

  private static void createDirectoryIfDoesNotExist(final Path path) {
    if (!Files.exists(path)) {
      createDirectoryWhenDoesNotExist(path);
    }
  }

  private static void createDirectoryWhenDoesNotExist(final Path path) {
    try {
      Files.createDirectory(path);
    } catch (final IOException ioException) {
      throw new UncheckedIOException(ioException);
    }
  }

  private static void createFileIfDoesNotExist(final String filePath) {

    final var localFilePath = Path.of(filePath);

    createFileIfDoesNotExist(localFilePath);
  }

  private static void createFileIfDoesNotExist(final Path filePath) {
    if (!Files.exists(filePath)) {
      createFileWhenDoesNotExist(filePath);
    }
  }

  private static void createFileWhenDoesNotExist(final Path filePath) {
    try {
      final var directoryPath = filePath.getParent();
      createDirectoryIfDoesNotExist(directoryPath);
      Files.createFile(filePath);
    } catch (final IOException ioException) {
      throw new UncheckedIOException(ioException);
    }
  }
}
