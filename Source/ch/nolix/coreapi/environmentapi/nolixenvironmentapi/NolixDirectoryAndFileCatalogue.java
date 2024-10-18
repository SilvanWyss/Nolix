package ch.nolix.coreapi.environmentapi.nolixenvironmentapi;

public final class NolixDirectoryAndFileCatalogue {

  private static final String APP_DATA = "APPDATA";

  private static final String APP_DATA_DIRECTORY_PATH = getAppDataDirectoryPath();

  private static final char FOLDER_DELIMITER = '/';

  public static final String NOLIX_DIRECTORY_NAME = "Nolix";

  public static final String NOLIX_DIRECTORY_PATH = APP_DATA_DIRECTORY_PATH + FOLDER_DELIMITER + NOLIX_DIRECTORY_NAME;

  public static final String NOLIX_LICENSES_DIRECTORY_NAME = "Licenses";

  public static final String NOLIX_LICENSES_DIRECTORY_PATH = //
  NOLIX_DIRECTORY_PATH + FOLDER_DELIMITER + NOLIX_LICENSES_DIRECTORY_NAME;

  public static final String NOLIX_CONFIGURATION_FILE_NAME = "configuration.txt";

  public static final String NOLIX_CONFIGURATION_FILE_PATH = //
  NOLIX_DIRECTORY_PATH + FOLDER_DELIMITER + NOLIX_CONFIGURATION_FILE_NAME;

  public static final String NOLI_LOG_FILE_NAME = "log.txt";

  public static final String NOLIX_LOG_FILE_PATH = NOLIX_DIRECTORY_PATH + FOLDER_DELIMITER + NOLI_LOG_FILE_NAME;

  private NolixDirectoryAndFileCatalogue() {
  }

  private static String getAppDataDirectoryPath() {
    return System.getenv(APP_DATA);
  }
}
