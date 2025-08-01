package ch.nolix.core.environment.license;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;

/**
 * Of the {@link LicenseManager} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @version 2019-11-16
 */
public final class LicenseManager {

  private static final LicenseManagerUnit LICENSE_MANAGER = new LicenseManagerUnit();

  /**
   * Prevents that an instance of the {@link LicenseManager} can be created.
   */
  private LicenseManager() {
  }

  /**
   * Adds the given license to the {@link LicenseManager}.
   * 
   * @param license
   * @throws ArgumentIsNullException  if the given license is null.
   * @throws InvalidArgumentException if the {@link LicenseManager} contains
   *                                  already a {@link License} of the
   *                                  type the given license is.
   */
  public static void addLicense(final License license) {
    LICENSE_MANAGER.addLicense(license);
  }

  /**
   * Creates and adds a new {@link License} of the given licenseType to
   * the {@link LicenseManager}.
   * 
   * @param licenseType
   * @param <L>         is the given licenseType.
   * @throws ArgumentIsNullException  if the given licenseType is null.
   * @throws RuntimeException         if if there was not found a file with the
   *                                  key for a License of the given licenseType.
   * @throws InvalidArgumentException if the given licenseType does not contain a
   *                                  constructor with 1 {@link String} parameter.
   * @throws InvalidArgumentException if the found key is not valid.
   * @throws InvalidArgumentException if the {@link LicenseManager} contains
   *                                  already a {@link License} of the
   *                                  given licenseType.
   */
  public static <L extends License> void addLicense(final Class<L> licenseType) {
    LICENSE_MANAGER.addLicense(licenseType);
  }

  /**
   * Requires the {@link LicenseManager} to contain a {@link AbstractFeature} of
   * the given featureType.
   * 
   * @param featureType
   * @param <F>         is the given featureType.
   * @throws ArgumentDoesNotHaveAttributeException if the {@link LicenseManager}
   *                                               does not contain a
   *                                               {@link AbstractFeature} of the
   *                                               given featureType.
   */
  public static <F extends AbstractFeature> void requireFeature(final Class<F> featureType) {
    LICENSE_MANAGER.requireFeature(featureType);
  }

  /**
   * Removes the given license from the {@link LicenseManager}.
   * 
   * @param license
   * @throws InvalidArgumentException if the {@link LicenseManager} does not
   *                                  contain the given license.
   */
  public static void removeLicense(final License license) {
    LICENSE_MANAGER.removeLicense(license);
  }

  /**
   * @param value
   * @return a new {@link LongMediator} for the given value.
   */
  public static LongMediator when(final long value) {
    return LICENSE_MANAGER.when(value);
  }
}
