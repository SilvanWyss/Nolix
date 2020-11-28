//package declaration
package ch.nolix.common.license;

import ch.nolix.common.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;

//class
/**
 * Of the {@link CentralLicenseManager} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2019-11
 * @lines 80
 */
public final class CentralLicenseManager {
	
	//static attribute
	private static final LicenseManager licenseManager = new LicenseManager();
	
	//static method
	/**
	 * Adds the given license to the {@link CentralLicenseManager}.
	 * 
	 * @param license
	 * @throws ArgumentIsNullException if the given license is null.
	 * @throws InvalidArgumentException if the {@link CentralLicenseManager}
	 * contains already a {@link License} of the type the given license is.
	 */
	public static void addLicense(final License license) {
		licenseManager.addLicense(license);
	}
	
	//static method
	/**
	 * Creates and adds a new {@link License} of the given licenseType to the {@link CentralLicenseManager}.
	 * 
	 * @param licenseType
	 * @throws ArgumentIsNullException if the given licenseType is null.
	 * @throws RuntimeException if if there was not found a file with the key for a License of the given licenseType.
	 * @throws InvalidArgumentException
	 * if the given licenseType does not contain a constructor with 1 {@link String} parameter.
	 * @throws InvalidArgumentException if the found key is not valid.
	 * @throws InvalidArgumentException if the {@link CentralLicenseManager}
	 * contains already a {@link License} of the given licenseType.
	 */
	public static <L extends License> void addLicense(final Class<L> licenseType) {
		licenseManager.addLicense(licenseType);
	}
	
	//static method
	/**
	 * Requires the {@link CentralLicenseManager} to contain a {@link Feature} of the given featureType.
	 * 
	 * @param featureType
	 * @throws ArgumentDoesNotHaveAttributeException if the {@link CentralLicenseManager}
	 * does not contain a {@link Feature} of the given featureType.
	 */
	public static <F extends Feature> void requireFeature(final Class<F> featureType) {
		licenseManager.requireFeature(featureType);
	}
	
	//static method
	/**
	 * Removes the given license from the {@link CentralLicenseManager}.
	 * 
	 * @param license
	 * @throws InvalidArgumentException if the {@link CentralLicenseManager} does not contain the given license.
	 */
	public static void removeLicense(final License license) {
		licenseManager.removeLicense(license);
	}
	
	//static method
	/**
	 * @param value
	 * @return a new {@link LongMediator} for the given value.
	 */
	public static LongMediator when(final long value) {
		return licenseManager.when(value);
	}
	
	//visibility-reduced constructor
	/**
	 * Avoids that an instance of the {@link CentralLicenseManager} can be created.
	 */
	private CentralLicenseManager() {}
}
