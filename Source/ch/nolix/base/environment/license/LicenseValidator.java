/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.environment.license;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.environment.licenseapi.ILicense;
import ch.nolix.baseapi.environment.licenseapi.ILicenseValidator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;

/**
 * A {@link LicenseValidator} is not mutable.
 * 
 * @author Silvan Wyss
 */
public final class LicenseValidator implements ILicenseValidator {
  /**
   * {@inheritDoc}
   */
  @Override
  public void assertIsActivated(final ILicense license) {
    Validator.assertThat(license).thatIsNamed(ILicense.class).isNotNull();

    if (!license.isActivated()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is not actiaved");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void assertIsNotActivated(final ILicense license) {
    Validator.assertThat(license).thatIsNamed(ILicense.class).isNotNull();

    if (license.isActivated()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is actiaved");
    }
  }
}
