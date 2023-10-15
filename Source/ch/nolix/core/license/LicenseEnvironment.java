//package declaration
package ch.nolix.core.license;

//own imports
import ch.nolix.core.independent.nolixenvironment.GlobalNolixEnvironmentHelper;

//class
/**
 * The {@link LicenseEnvironment} provides information about the Nolix license
 * environment on the local computer. Of the {@link LicenseEnvironment} an
 * instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2019-11-16
 */
public final class LicenseEnvironment {

  // constant
  public static final String LOCAL_LICENSE_FOLDER_NAME = "Licenses";

  // constant
  public static final String LICENCSE_FILE_EXTENSION = "license";

  // static method
  /**
   * @return the path of the license folder on the local computer.
   */
  public static String getLocalLicenseFolderPath() {
    return (GlobalNolixEnvironmentHelper.getLocalNolixFolderPath() + "/" + LOCAL_LICENSE_FOLDER_NAME);
  }

  // constructor
  /**
   * Prevents that an instance of the {@link LicenseEnvironment} can be created.
   */
  private LicenseEnvironment() {
  }
}
