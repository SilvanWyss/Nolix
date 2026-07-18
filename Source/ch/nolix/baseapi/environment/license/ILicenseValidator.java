/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.environment.license;

import ch.nolix.base.environment.license.AbstractLicense;

/**
 * @author Silvan Wyss
 */
public interface ILicenseValidator {
  /**
   * @param license
   * @throws RuntimeException if the given {@link AbstractLicense} is null or not
   *                          activated
   */
  void assertIsActivated(License license);

  /**
   * @param license
   * @throws RuntimeException if the given {@link AbstractLicense} is null or
   *                          already activated
   */
  void assertIsNotActivated(License license);
}
