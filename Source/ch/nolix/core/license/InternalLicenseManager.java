//package declaration
package ch.nolix.core.license;

//Java import
import java.lang.reflect.Constructor;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.fileSystem.FolderAccessor;
import ch.nolix.core.invalidArgumentException.ArgumentMissesAttributeException;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.validator.Validator;

//class
/**
 * A {@link InternalLicenseManager} manages {@link License}s.
 * 
 * @author Silvan Wyss
 * @month 2019-04
 * @lines 200
 */
public final class InternalLicenseManager {
	
	//multi-attributes
	private final List<License> licenses = new List<License>();
	private final List<Permission> losePermissions = new List<Permission>();
	
	//package-visible constructor
	InternalLicenseManager() {}
	
	//method
	/**
	 * Creates and adds a new {@link License} of the given type to the current {@link InternalLicenseManager}
	 * if the current {@link InternalLicenseManager} does not contain already a {@link License} of that type.
	 * 
	 * @param type
	 * @return the current {@link InternalLicenseManager}.
	 * @throws NullArgumentException if the given type is null.
	 * @throws RuntimeException if if there was not found a file with the key for a License of the given type.
	 * @throws InvalidArgumentException
	 * if the given type does not contain an accessible constructor with 1 String parameter.
	 * @throws InvalidArgumentException if the found key is not valid.
	 * @throws InvalidArgumentException
	 * if the current {@link InternalLicenseManager} contains already a {@link License} of the given type.
	 */
	public <L extends License> InternalLicenseManager addLicense(final Class<L> type) {
		
		//Checks if the given type is not null.
		Validator.suppose(type).thatIsNamed(VariableNameCatalogue.TYPE).isNotNull();
		
		//Extracts the key for the license.
		String key = null;
		try {
			key = new FolderAccessor("Licenses").readFile(type.getName());
		}
		catch (final Exception exception) {
			throw
			new RuntimeException("There was not found a file with the key for a License '" + type.getName() + "'" );
		}
		
		//Extracts the constructor for the license.
		Constructor<L> constructor = null;
		try {
			constructor = type.getConstructor(String.class);
		}
		catch (final Exception exception) {
			
			//Handles the case that the given type does not contain an accessible constructor with 1 String parameter.
			throw
			new InvalidArgumentException(type, "does not contain an accessible constructor with 1 String parameter");
		}
		
		//Creates the license.
		final L license;
		try {
			license = constructor.newInstance(key);
		}
		catch (final Exception exception) {
			
			//Handles the case that the given key is not valid.
			throw new InvalidArgumentException("found key", key, "is not valid");
		}
		
		//Adds the license to the current InternalLicenseManager.
		return addLicense(license);
	}
	
	//method
	/**
	 * Adds the given license to the current {@link InternalLicenseManager}.
	 * 
	 * @param license
	 * @return the current {@link InternalLicenseManager}.
	 * @throws NullArgumentException if the given license is null.
	 * @throws InvalidArgumentException
	 * if the current {@link InternalLicenseManager}
	 * contains already a {@link License} of the type the given license is.
	 */
	public InternalLicenseManager addLicense(final License license) {
		
		//Checks if the given license is not null.
		Validator.suppose(license).thatIsNamed(VariableNameCatalogue.LICENSE).isNotNull();
		
		//Checks if the current InternalLicenseManager
		//does not contain already a License of the type the given license is.
		if (containsLicense(license.getClass())) {
			throw
			new InvalidArgumentException(this, "contains already a License of the type '" + license.getName() + "'");
		}
		
		licenses.addAtEnd(license);
		
		return this;
	}
	
	//method
	/**
	 * Adds the given permission to the current {@link InternalLicenseManager}.
	 * 
	 * @param permission
	 * @return the current {@link InternalLicenseManager}.
	 * @throws NullArgumentException if the given permission is null.
	 * @throws InvalidArgumentException if the current {@link InternalLicenseManager}
	 * contains already a permission of the same type the given permission is.
	 */
	public InternalLicenseManager addPermission(final Permission permission) {
		
		//Checks if the given permission is not null.
		Validator.suppose(permission).thatIsNamed(VariableNameCatalogue.PERMISSION).isNotNull();
		
		//Checks if the current InternalLicenseManager
		//does not contain already a permission of the same type the given permission is.
		if (containsPermission(permission.getClass())) {
			throw
			new InvalidArgumentException(
				this,
				"contains already a permission of the type the given permission has"
			);
		}
		
		losePermissions.addAtEnd(permission);
		
		return this;
	}
	
	//method
	/**
	 * @param type
	 * @return true if the current {@link InternalLicenseManager} contains a {@link License} of the given type.
	 */
	public <L extends License> boolean containsLicense(final Class<L> type) {
		return licenses.contains(l -> l.getClass() == type);
	}
	
	//method
	/**
	 * @param type
	 * @return true if the current {@link InternalLicenseManager} contains a {@link Permission} of the given type.
	 */
	public <FP extends Permission> boolean containsPermission(final Class<FP> type) {
		return
		licenses.contains(l -> l.containsPermission(type))
		|| losePermissions.contains(lp -> lp.getClass().equals(type));
	}
	
	//mmethod
	/**
	 * Removes the given license from the {@link LicenseManager}.
	 * 
	 * @param license
	 * @return the current {@link InternalLicenseManager}.
	 * @throws InvalidArgumentException
	 * if the current {@link InternalLicenseManager} does not contain the given license.
	 */
	public <L extends License> InternalLicenseManager removeLicense(final L license) {
		
		licenses.removeFirst(license);
		
		return this;
	}
	
	//method
	/**
	 * Lets the current {@link InternalLicenseManager} require a {@link Permission} of the given type.
	 * 
	 * @param type
	 * @return the current {@link InternalLicenseManager}.
	 * @throws ArgumentMissesAttributeException if the current {@link InternalLicenseManager}
	 * does not contain a {@link Permission} of the given type.
	 */
	public <FP extends Permission> InternalLicenseManager requirePermission(final Class<FP> type) {
		
		//Checks if the current InternalLicenseManager contains a permission of the given type.
		if (!containsPermission(type)) {
			throw new ArgumentMissesAttributeException(this, type.getSimpleName());
		}
		
		return this;
	}
}
