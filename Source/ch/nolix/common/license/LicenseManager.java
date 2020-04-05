//package declaration
package ch.nolix.common.license;

//Java import
import java.lang.reflect.InvocationTargetException;

//own imports
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.containers.LinkedList;
import ch.nolix.common.fileSystem.FolderAccessor;
import ch.nolix.common.invalidArgumentExceptions.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.reflectionWrappers.ClassWrapper;
import ch.nolix.common.validator.Validator;
import ch.nolix.common.wrapperException.WrapperException;

//class
/**
 * A {@link LicenseManager} contains {@link License}s.
 * 
 * @author Silvan Wyss
 * @month 2019-11
 * @lines 190
 */
public final class LicenseManager {
	
	//multi-attribute
	private final LinkedList<License> licenses = new LinkedList<>();
	
	//method
	/**
	 * Creates and adds a new {@link License} of the given licenseType to the current {@link LicenseManager}
	 * 	 * 
	 * @param licenseType
	 * @return the current {@link LicenseManager}.
	 * @throws ArgumentIsNullException if the given licenseType is null.
	 * @throws RuntimeException if if there was not found a file with the key for a License of the given licenseType.
	 * @throws InvalidArgumentException
	 * if the given licenseType does not contain a constructor with 1 {@link String} parameter.
	 * @throws InvalidArgumentException if the found key is not valid.
	 * @throws InvalidArgumentException if the current {@link LicenseManager}
	 * does contain already a {@link License} of the given licenseType.
	 */
	public <L extends License> LicenseManager addLicense(final Class<L> licenseType) {
		
		final var key = readKeyFromLicenseFile(licenseType);
		addLicense(new ClassWrapper<>(License.class).createInstance(key));
		
		return this;
	}
	
	//method
	/**
	 * Adds the given license to the current {@link LicenseManager}.
	 * 
	 * @param license
	 * @return the current {@link LicenseManager}.
	 * @throws ArgumentIsNullException if the given license is null.
	 * @throws InvalidArgumentException if the current {@link InternalLicenseManager}
	 * contains already a {@link License} of the type the given license is.
	 */
	public LicenseManager addLicense(final License license) {
		
		//Checks if the given license is not null.
		Validator.suppose(license).thatIsNamed(VariableNameCatalogue.LICENSE).isNotNull();
		
		//Handles the case that the current LicenseManager
		//does not contain already a License of the type the given license is.
		if (!containsLicense(license.getClass())) {
			licenses.addAtEnd(license);
		}
		
		return this;
	}

	//method
	/**
	 * @param featureType
	 * @return true if the current {@link InternalLicenseManager} contains a {@link Feature} of the given featureType.
	 */
	public <F extends Feature> boolean containsFeature(final Class<F> featureType) {
		try {
			final var feature = featureType.getConstructor().newInstance();
			return feature.getAuthorizedLicenseTypes().containsAnyFrom(getLicenseTypes());
		}
		catch (
			final 
			InstantiationException
			| IllegalAccessException
			| IllegalArgumentException
			| InvocationTargetException
			| NoSuchMethodException
			| SecurityException
			exception
		) {
			throw new WrapperException(exception);
		}
	}
	
	//method
	/**
	 * @param licenseType
	 * @return true if the current {@link LicenseManager} contains a {@link License} of the given licenseType.
	 */
	public <L extends License> boolean containsLicense(final Class<L> licenseType) {
		return licenses.contains(l -> l.getClass() == licenseType);
	}
	
	//method
	/**
	 * @return the types of the licenses of the current {@link LicenseManager}.
	 */
	public LinkedList<Class<?>> getLicenseTypes() {
		return licenses.to(License::getClass);
	}
	
	//method
	/**
	 * Removes the given license from the current {@link LicenseManager}.
	 * 
	 * @param license
	 * @throws InvalidArgumentException if the current {@link LicenseManager} does not contain the given license.
	 */
	public LicenseManager removeLicense(final License license) {
		
		licenses.removeFirst(license);
		
		return this;
	}
	
	//method
	/**
	 * Requires the current {@link lLicenseManager} to contain a {@link Feature} of the given featureType.
	 * 
	 * @param featureType
	 * @return the current {@link LicenseManager}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link LicenseManager}
	 * does not contain a {@link Feature} of the given featureType.
	 */
	public <F extends Feature> LicenseManager requireFeature(final Class<F> featureType) {
		
		//Checks if the current LicenseManager contains a feature of the given featureType.
		if (!containsFeature(featureType)) {
			throw new ArgumentDoesNotHaveAttributeException(this, featureType.getSimpleName());
		}
		
		return this;
	}
	
	/**
	 * @param value
	 * @return a new {@link LongMediator} for the given value.
	 */
	public LongMediator when(final long value) {
		return new LongMediator(this, value);
	}
	
	//method
	/**
	 * @param licenseType
	 * @return the key of the license of the given licenseType from the license file.
	 * The license file is on the local computer.
	 * @throws ArgumentIsNullException if the given licenseType is null.
	 */
	private <L extends License> String readKeyFromLicenseFile(final Class<L> licenseType) {
		
		//Checks if the given licenseType is not null.
		Validator.suppose(licenseType).thatIsNamed(VariableNameCatalogue.TYPE).isNotNull();
		
		return readKeyFromLicenseFile(licenseType.getName());
	}
	
	//method
	/**
	 * @param licenseName
	 * @return the key of the license with the given licenseName from the license file.
	 * The license file is on the local computer.
	 * @throws RuntimeException if there does not exist a license file
	 * with the key for the license with the given licenseName.
	 */
	private String readKeyFromLicenseFile(final String licenseName) {
		try {
			return
			new FolderAccessor(LicenseEnvironment.LOCAL_LICENSE_FOLDER_NAME)
			.readFile(licenseName + "." + LicenseEnvironment.LICENCSE_FILE_EXTENSION);
		}
		catch (final Exception exception) {
			throw
			new RuntimeException(
				"There does not exist a license file with the key for the License '" + licenseName + "'"
			);
		}
	}
}
