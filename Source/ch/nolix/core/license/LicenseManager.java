//package declaration
package ch.nolix.core.license;

//own imports
import ch.nolix.core.invalidArgumentException.ArgumentMissesAttributeException;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;

//class
/**
 * The {@link LicenseManager} manages {@link License}s.
 * Of the {@link LicenseManager} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2019-04
 * @lines 120
 */
public class LicenseManager {
	
	//static attribute
	private static final InternalLicenseManager internalLicenseManager = new InternalLicenseManager();
	
	//static method
	/**
	 * Creates and adds a new {@link License} of the given type to the {@link LicenseManager}
	 * if the {@link LicenseManager} does not contain already a {@link License} of that type.
	 * 
	 * @param type
	 * @return the {@link InternalLicenseManager} of the {@link LicenseManager}.
	 * @throws NullArgumentException if the given type is null.
	 * @throws RuntimeException if there was not found a file with the key for a License of the given type.
	 * @throws InvalidArgumentException 
	 * if the given type does not contain an accessible constructor with 1 String parameter.
	 * @throws InvalidArgumentException if the found key is not valid.
	 * @throws InvalidArgumentException
	 * if the {@link LicenseManager} contains already a {@link License} of the given type.
	 */
	public static <L extends License> InternalLicenseManager addLicense(final Class<L> type) {
		return internalLicenseManager.addLicense(type);
	}
	
	//static method
	/**
	 * Adds the given license to the {@link LicenseManager}.
	 * 
	 * @param license
	 * @return the {@link InternalLicenseManager} of the {@link LicenseManager}.
	 * @throws NullArgumentException if the given license is null.
	 * @throws InvalidArgumentException
	 * if the {@link LicenseManager} contains already a {@link License} of the same type.
	 * @throws InvalidArgumentException
	 * if the {@link LicenseManager} contains already a {@link License} of the type the given license is.
	 */
	public static InternalLicenseManager addLicense(final License license) {
		return internalLicenseManager.addLicense(license);
	}
	
	//method
	/**
	 * Adds the given permission to the {@link LicenseManager}.
	 * 
	 * @param permission
	 * @return the {@link InternalLicenseManager} of the {@link LicenseManager}.
	 * @throws NullArgumentException if the given permission is null.
	 * @throws InvalidArgumentException if the {@link LicenseManager}
	 * contains already a permission of the same type the given permission is.
	 */
	public InternalLicenseManager addPermission(final Permission permission) {
		return internalLicenseManager.addPermission(permission);
	}
	
	//method
	/**
	 * @param type
	 * @return true if the {@link LicenseManager} contains a {@link License} of the given type.
	 */
	public static <L extends License> boolean containsLicense(final Class<L> type) {
		return internalLicenseManager.containsLicense(type);
	}
	
	//static method
	/**
	 * Removes the given license from the {@link LicenseManager}.
	 * 
	 * @param license
	 * @return the {@link InternalLicenseManager} of the {@link LicenseManager}.
	 * @throws InvalidArgumentException if the {@link LicenseManager} does not contain the given license.
	 */
	public static <L extends License> InternalLicenseManager removeLicense(final L license) {
		return internalLicenseManager.removeLicense(license);
	}
	
	//static method
	/**
	 * Let the {@link LicenseManager} require a {@link Permission} of the given type.
	 * 
	 * @param type
	 * @return the {@link InternalLicenseManager} of the {@link LicenseManager}.
	 * @throws ArgumentMissesAttributeException if the {@link LicenseManager}
	 * does not contain a {@link Permission} of the given type.
	 */
	public static <P extends Permission> InternalLicenseManager requirePermission(final Class<P> type) {
		return internalLicenseManager.requirePermission(type);
	}
	
	//static method
	/**
	 * @param value
	 * @return a new {@link LongMediator} for the given value.
	 */
	public static LongMediator when(final long value) {
		return internalLicenseManager.when(value);
	}
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link LicenseManager} can be created.
	 */
	private LicenseManager() {}
}
