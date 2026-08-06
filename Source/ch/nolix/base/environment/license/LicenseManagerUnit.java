/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.environment.license;

import java.lang.reflect.InvocationTargetException;

import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.environment.filesystem.FolderAccessor;
import ch.nolix.base.errorcontrol.generalexception.GeneralException;
import ch.nolix.base.errorcontrol.generalexception.WrapperException;
import ch.nolix.base.reflection.reflectiontool.ReflectionTool;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.environment.nolixenvironment.NolixDirectoryAndFileCatalog;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * A {@link LicenseManagerUnit} contains {@link AbstractLicense}s.
 * 
 * @author Silvan Wyss
 */
public final class LicenseManagerUnit {
  private static final LicenseValidator LICENSE_VALIDATOR = new LicenseValidator();

  private final LinkedList<AbstractLicense> abstractLicenses = LinkedList.createEmpty();

  /**
   * Creates and adds a new {@link AbstractLicense} of the given licenseType to
   * the current {@link LicenseManagerUnit}
   * 
   * @param licenseType
   * @param <L>         is the given licenseType
   * @return the current {@link LicenseManagerUnit}
   * @throws RuntimeException if the given licenseType is null
   * @throws RuntimeException if if there was not found a file with the key for a
   *                          License of the given licenseType
   * @throws RuntimeException if the given licenseType does not contain a
   *                          constructor with 1 {@link String} parameter
   * @throws RuntimeException if the found key is not valid
   * @throws RuntimeException if the current {@link LicenseManagerUnit} does
   *                          contain already a {@link AbstractLicense} of the
   *                          given licenseType.
   */
  public <L extends AbstractLicense> LicenseManagerUnit addLicense(final Class<L> licenseType) {
    final var key = readKeyFromLicenseFile(licenseType);

    final var license = ReflectionTool.createInstanceFromDefaultConstructorOfClass(AbstractLicense.class);
    license.activateWithKey(key);

    addLicense(license);

    return this;
  }

  /**
   * Adds the given license to the current {@link LicenseManagerUnit}.
   * 
   * @param abstractLicense
   * @return the current {@link LicenseManagerUnit}
   * @throws RuntimeException if the given license is null
   * @throws RuntimeException if the given license is not activated
   * @throws RuntimeException if the current {@link LicenseManagerUnit} contains
   *                          already a {@link AbstractLicense} of the type the
   *                          given license is.
   */
  public LicenseManagerUnit addLicense(final AbstractLicense abstractLicense) {
    // Asserts that the given license is not null.
    Validator.assertThat(abstractLicense).thatIsNamed(LowerCaseVariableNameCatalog.LICENSE).isNotNull();

    // Assets thath the given license is actiaved.
    LICENSE_VALIDATOR.assertIsActivated(abstractLicense);

    // Handles the case that the current LicenseManager
    // does not contain already a License of the type the given license is.
    if (!containsLicense(abstractLicense.getClass())) {
      abstractLicenses.addAtEnd(abstractLicense);
    }

    return this;
  }

  /**
   * @param featureType
   * @param <F>         is the given featureType
   * @return true if the current {@link LicenseManagerUnit} contains a
   *         {@link AbstractFeature} of the given featureType, false otherwise
   */
  public <F extends AbstractFeature> boolean containsFeature(final Class<F> featureType) {
    try {
      final var feature = featureType.getConstructor().newInstance();
      return feature.getAuthorizedLicenseTypes().containsAny(getLicenseTypes());
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
   * @param <L>         is the given licenseType
   * @return true if the current {@link LicenseManagerUnit} contains a
   *         {@link AbstractLicense} of the given licenseType, false otherwise
   */
  public <L extends AbstractLicense> boolean containsLicense(final Class<L> licenseType) {
    return abstractLicenses.containsMatching(l -> l.getClass() == licenseType);
  }

  /**
   * @return the types of the licenses of the current {@link LicenseManagerUnit}.
   */
  public ExtendedIterable<Class<?>> getLicenseTypes() {
    return abstractLicenses.to(AbstractLicense::getClass);
  }

  /**
   * Removes the given license from the current {@link LicenseManagerUnit}.
   * 
   * @param abstractLicense
   * @throws ArgumentDoesNotContainElementException if the current
   *                                                {@link LicenseManagerUnit}
   *                                                does not contain the given
   *                                                license.
   */
  public void removeLicense(final AbstractLicense abstractLicense) {
    abstractLicenses.removeStrictlyFirstOccurrenceOf(abstractLicense);
  }

  /**
   * Requires the current {@link LicenseManagerUnit} to contain a
   * {@link AbstractFeature} of the given featureType.
   * 
   * @param featureType
   * @param <F>         is the given featureType
   * @return the current {@link LicenseManagerUnit}
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link LicenseManagerUnit} does
   *                                               not contain a
   *                                               {@link AbstractFeature} of the
   *                                               given featureType.
   */
  public <F extends AbstractFeature> LicenseManagerUnit requireFeature(final Class<F> featureType) {
    // Asserts that the current LicenseManager contains a feature of the given
    // featureType.
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
    return LongMediator.forLicenseManagerAndValue(this, value);
  }

  /**
   * @param licenseType
   * @param <L>         is the given licenseType
   * @return the key of the license of the given licenseType from the license
   *         file. The license file is on the local computer
   * @throws RuntimeException if the given licenseType is null
   */
  private <L extends AbstractLicense> String readKeyFromLicenseFile(final Class<L> licenseType) {
    // Asserts that the given licenseType is not null.
    Validator.assertThat(licenseType).thatIsNamed(LowerCaseVariableNameCatalog.TYPE).isNotNull();

    return readKeyFromLicenseFile(licenseType.getName());
  }

  /**
   * @param licenseName
   * @return the key of the license with the given licenseName from the license
   *         file. The license file is on the local computer
   * @throws RuntimeException if there does not exist a license file with the key
   *                          for the license with the given licenseName.
   */
  private String readKeyFromLicenseFile(final String licenseName) {
    try {
      return FolderAccessor.forFolderPath(NolixDirectoryAndFileCatalog.NOLIX_CONFIGURATION_FILE_PATH)
        .readFile(licenseName + "." + LicenseEnvironment.LICENCSE_FILE_EXTENSION);
    } catch (final Throwable _) { // NOSONAR: All Throwable must be caught.
      throw //
      GeneralException.withErrorMessage(
        "There does not exist a license file with the key for the License '" + licenseName + "'");
    }
  }
}
