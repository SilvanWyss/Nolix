/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.environment.licenseapi;

import ch.nolix.base.environment.license.License;

/**
 * @author Silvan Wyss
 */
public interface ILicenseValidator {
  /**
   * @param license
   * @throws RuntimeException if the given {@link License} is null or not
   *                          activated.
   */
  void assertIsActivated(ILicense license);

  /**
   * @param license
   * @throws RuntimeException if the given {@link License} is null or already
   *                          activated.
   */
  void assertIsNotActivated(ILicense license);
}
