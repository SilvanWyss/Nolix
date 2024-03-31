//package declaration
package ch.nolix.coreapi.environmentapi.nolixenvironmentapi;

//class
public final class NolixDirectoryAndFileCatalogue {

  //constant
  private static final String APP_DATA = "APPDATA";

  //constant
  private static final String APP_DATA_DIRECTORY_PATH = getAppDataDirectoryPath();

  //constant
  private static final char FOLDER_DELIMITER = '/';

  //constant
  public static final String NOLIX_DIRECTORY_NAME = "Nolix";

  //constant
  public static final String NOLIX_DIRECTORY_PATH = APP_DATA_DIRECTORY_PATH + FOLDER_DELIMITER + NOLIX_DIRECTORY_NAME;

  //constant
  public static final String NOLIX_LICENSES_DIRECTORY_NAME = "Licenses";

  //constant
  public static final String NOLIX_LICENSES_DIRECTORY_PATH = //
  NOLIX_DIRECTORY_PATH + FOLDER_DELIMITER + NOLIX_LICENSES_DIRECTORY_NAME;

  //constant
  public static final String NOLIX_CONFIGURATION_FILE_NAME = "configuration.txt";

  //constant
  public static final String NOLIX_CONFIGURATION_FILE_PATH = //
  NOLIX_DIRECTORY_PATH + FOLDER_DELIMITER + NOLIX_CONFIGURATION_FILE_NAME;

  //constant
  public static final String NOLI_LOG_FILE_NAME = "log.txt";

  //constant
  public static final String NOLIX_LOG_FILE_PATH = NOLIX_DIRECTORY_PATH + FOLDER_DELIMITER + NOLI_LOG_FILE_NAME;

  //constructor
  private NolixDirectoryAndFileCatalogue() {
  }

  //static method
  private static String getAppDataDirectoryPath() {
    return System.getenv(APP_DATA);
  }
}
