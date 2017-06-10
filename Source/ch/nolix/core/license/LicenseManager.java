//package declaration
package ch.nolix.core.license;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;

//class
/**
 * A license manager manages licenses.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 60
 */
public final class LicenseManager {

	//attribute
	private final List<License> licenses = new List<License>();
	
	//method
	/**
	 * Adds the given license to this license manager
	 * if this license manager does not contain already a license of the same type.
	 * 
	 * @param license
	 */
	public void addLicense(final License license) {
		if (!containsLicense(license.getClass())) {
			licenses.addAtEnd(license);
		}
	}
	
	//method
	/**
	 * @param type
	 * @return true if this license manager contains a license of the given type.
	 */
	public <L extends License> boolean  containsLicense(final Class<L> type) {
		return licenses.contains(l -> l.getClass().isAssignableFrom(type));
	}
	
	//method
	/**
	 * @return the number of licenses of this license manager.
	 */
	public int getLicenseCount() {
		return licenses.getElementCount();
	}
	
	//method
	/**
	 * @param type
	 * @throws UnexistringAttributeException if this license manager contains no license of the given type.
	 */
	public <L extends License> void supposeLicense(final Class<L> type) {
		
		//Checks if this license manager contains a license of the given type.
		if (!containsLicense(type)) {
			throw new UnexistingAttributeException(this, type.getSimpleName());
		}
	}
}
