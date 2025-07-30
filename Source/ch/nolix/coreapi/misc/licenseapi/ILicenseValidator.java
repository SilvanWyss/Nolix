package ch.nolix.coreapi.misc.licenseapi;

import ch.nolix.core.misc.license.License;

/**
 * @author Silvan Wyss
 * @version 2025-07-30
 */
public interface ILicenseValidator {

  /**
   * @param license
   * @throws RuntimeException if the given {@link License} is not activated.
   */
  void assertIsActivated(ILicense license);
}
