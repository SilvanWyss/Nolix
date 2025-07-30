package ch.nolix.core.misc.license;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.misc.licenseapi.ILicense;
import ch.nolix.coreapi.misc.licenseapi.ILicenseValidator;

/**
 * A {@link LicenseValidator} is not mutable.
 * 
 * @author Silvan Wyss
 * @version 2025-07-30
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
