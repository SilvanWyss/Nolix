//package declaration
package ch.nolix.common.license;

//own imports
import ch.nolix.common.nolixenvironment.NolixEnvironment;

//class
/**
 * The {@link LicenseEnvironment} provides information about the Nolix license environment on the local computer.
 * Of the {@link LicenseEnvironment} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2019-11
 * @lines 30
 */
public final class LicenseEnvironment {
	
	//constants
	public static final String LOCAL_LICENSE_FOLDER_NAME = "Licenses";
	public static final String LICENCSE_FILE_EXTENSION = "license";
	
	//static method
	/**
	 * @return the path of the license folder on the local computer.
	 */
	public static String getLocalLicenseFolderPath() {
		return (NolixEnvironment.getLocalNolixFolderPath() + "/" + LOCAL_LICENSE_FOLDER_NAME);
	}
	
	//visibility-reduced constructor
	/**
	 * Avoids that an instance of the {@link LicenseEnvironment} can be created.
	 */
	private LicenseEnvironment() {}
}
