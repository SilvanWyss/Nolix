/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.environment.license;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.environment.license.License;
import ch.nolix.baseapi.environment.license.ILicenseValidator;
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
  public void assertIsActivated(final License license) {
    Validator.assertThat(license).thatIsNamed(License.class).isNotNull();

    if (!license.isActivated()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is not actiaved");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void assertIsNotActivated(final License license) {
    Validator.assertThat(license).thatIsNamed(License.class).isNotNull();

    if (license.isActivated()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is actiaved");
    }
  }
}
