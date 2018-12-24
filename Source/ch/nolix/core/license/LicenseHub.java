//package declaration
package ch.nolix.core.license;

//class
/**
 * This class provides methods to manage licenses at a central point.
 * From this class an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 40
 */
public class LicenseHub {

	//static variable
	private static final LicenseManager LICENSE_MANAGER = new LicenseManager();
	
	//static method
	/**
	 * Adds the given license manager
	 * if the license hub does not contain already the given license.
	 * 
	 * @param license
	 */
	public static void addLicense(final License license) {
		LICENSE_MANAGER.addLicense(license);
	}
	
	//static method
	/**
	 * @param type
	 * @throws UnexistringAttributeException if the license hub does not contain a license of the given type.
	 */
	public static <L extends License> void supposeLicense(final Class<L> type) {
		LICENSE_MANAGER.supposeLicense(type);
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private LicenseHub() {}
}
