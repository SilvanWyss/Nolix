package ch.nolix.core.license;

import java.lang.reflect.InvocationTargetException;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.environment.filesystem.FolderAccessor;
import ch.nolix.core.errorcontrol.exception.GeneralException;
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.structurecontrol.reflectiontool.GlobalReflectionTool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.environmentapi.nolixenvironmentapi.NolixDirectoryAndFileCatalog;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

/**
 * A {@link LicenseManager} contains {@link License}s.
 * 
 * @author Silvan Wyss
 * @version 2019-11-16
 */
public final class LicenseManager {

  private final LinkedList<License> licenses = LinkedList.createEmpty();

  /**
   * Creates and adds a new {@link License} of the given licenseType to the
   * current {@link LicenseManager}
   * 
   * @param licenseType
   * @param <L>         is the given licenseType.
   * @return the current {@link LicenseManager}.
   * @throws ArgumentIsNullException  if the given licenseType is null.
   * @throws RuntimeException         if if there was not found a file with the
   *                                  key for a License of the given licenseType.
   * @throws InvalidArgumentException if the given licenseType does not contain a
   *                                  constructor with 1 {@link String} parameter.
   * @throws InvalidArgumentException if the found key is not valid.
   * @throws InvalidArgumentException if the current {@link LicenseManager} does
   *                                  contain already a {@link License} of the
   *                                  given licenseType.
   */
  public <L extends License> LicenseManager addLicense(final Class<L> licenseType) {

    final var key = readKeyFromLicenseFile(licenseType);

    final var license = GlobalReflectionTool.createInstanceFromDefaultConstructorOfClass(License.class);
    license.activate(key);

    addLicense(license);

    return this;
  }

  /**
   * Adds the given license to the current {@link LicenseManager}.
   * 
   * @param license
   * @return the current {@link LicenseManager}.
   * @throws ArgumentIsNullException  if the given license is null.
   * @throws InvalidArgumentException if the given license is not activated.
   * @throws InvalidArgumentException if the current {@link LicenseManager}
   *                                  contains already a {@link License} of the
   *                                  type the given license is.
   */
  public LicenseManager addLicense(final License license) {

    //Asserts that the given license is not null.
    Validator.assertThat(license).thatIsNamed(LowerCaseVariableCatalog.LICENSE).isNotNull();

    //Assets thath the given license is actiaved.
    license.assetIsActivated();

    //Handles the case that the current LicenseManager
    //does not contain already a License of the type the given license is.
    if (!containsLicense(license.getClass())) {
      licenses.addAtEnd(license);
    }

    return this;
  }

  /**
   * @param featureType
   * @param <F>         is the given featureType.
   * @return true if the current {@link LicenseManager} contains a {@link Feature}
   *         of the given featureType.
   */
  public <F extends Feature> boolean containsFeature(final Class<F> featureType) {
    try {
      final var feature = featureType.getConstructor().newInstance();
      return feature.getAuthorizedLicenseTypes().containsAnyOf(getLicenseTypes());
    } catch (final
    InstantiationException
    | IllegalAccessException
    | IllegalArgumentException
    | InvocationTargetException
    | NoSuchMethodException
    | SecurityException exception) {
      throw WrapperException.forError(exception);
    }
  }

  /**
   * @param licenseType
   * @param <L>         is the given licenseType.
   * @return true if the current {@link LicenseManager} contains a {@link License}
   *         of the given licenseType.
   */
  public <L extends License> boolean containsLicense(final Class<L> licenseType) {
    return licenses.containsAny(l -> l.getClass() == licenseType);
  }

  /**
   * @return the types of the licenses of the current {@link LicenseManager}.
   */
  public IContainer<Class<?>> getLicenseTypes() {
    return licenses.to(License::getClass);
  }

  /**
   * Removes the given license from the current {@link LicenseManager}.
   * 
   * @param license
   * @throws ArgumentDoesNotContainElementException if the current
   *                                                {@link LicenseManager} does
   *                                                not contain the given license.
   */
  public void removeLicense(final License license) {
    licenses.removeStrictlyFirstOccurrenceOf(license);
  }

  /**
   * Requires the current {@link LicenseManager} to contain a {@link Feature} of
   * the given featureType.
   * 
   * @param featureType
   * @param <F>         is the given featureType.
   * @return the current {@link LicenseManager}.
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link LicenseManager} does not
   *                                               contain a {@link Feature} of
   *                                               the given featureType.
   */
  public <F extends Feature> LicenseManager requireFeature(final Class<F> featureType) {

    //Asserts that the current LicenseManager contains a feature of the given
    //featureType.
    if (!containsFeature(featureType)) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, featureType.getSimpleName());
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

  /**
   * @param licenseType
   * @param <L>         is the given licenseType.
   * @return the key of the license of the given licenseType from the license
   *         file. The license file is on the local computer.
   * @throws ArgumentIsNullException if the given licenseType is null.
   */
  private <L extends License> String readKeyFromLicenseFile(final Class<L> licenseType) {

    //Asserts that the given licenseType is not null.
    Validator.assertThat(licenseType).thatIsNamed(LowerCaseVariableCatalog.TYPE).isNotNull();

    return readKeyFromLicenseFile(licenseType.getName());
  }

  /**
   * @param licenseName
   * @return the key of the license with the given licenseName from the license
   *         file. The license file is on the local computer.
   * @throws RuntimeException if there does not exist a license file with the key
   *                          for the license with the given licenseName.
   */
  private String readKeyFromLicenseFile(final String licenseName) {
    try {
      return new FolderAccessor(NolixDirectoryAndFileCatalog.NOLIX_CONFIGURATION_FILE_PATH)
        .readFile(licenseName + "." + LicenseEnvironment.LICENCSE_FILE_EXTENSION);
    } catch (final Throwable error) { //NOSONAR: All Throwables must be caught.
      throw GeneralException.withErrorMessage(
        "There does not exist a license file with the key for the License '" + licenseName + "'");
    }
  }
}
