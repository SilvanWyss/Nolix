/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.environment.license;

import ch.nolix.base.validation.validator.Validator;

/**
 * @author Silvan Wyss
 */
public final class AdditionalConditionMediator {
  private final LicenseManagerUnit parentLicenseManager;

  private AdditionalConditionMediator(final LicenseManagerUnit parentLicenseManager) {
    Validator.assertThat(parentLicenseManager).thatIsNamed("parent LicenseManager").isNotNull();

    this.parentLicenseManager = parentLicenseManager;
  }

  public static AdditionalConditionMediator forLicenseManager(final LicenseManagerUnit licenseManager) {
    return new AdditionalConditionMediator(licenseManager);
  }

  public LongMediator andWhen(final long value) {
    return LongMediator.forLicenseManagerAndValue(parentLicenseManager, value);
  }
}
