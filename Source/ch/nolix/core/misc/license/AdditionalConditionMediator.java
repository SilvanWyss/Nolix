package ch.nolix.core.misc.license;

import ch.nolix.core.errorcontrol.validator.Validator;

public final class AdditionalConditionMediator {

  private final LicenseManagerUnit parentLicenseManager;

  AdditionalConditionMediator(final LicenseManagerUnit parentLicenseManager) {

    Validator.assertThat(parentLicenseManager).thatIsNamed("parent LicenseManager").isNotNull();

    this.parentLicenseManager = parentLicenseManager;
  }

  public LongMediator andWhen(final long value) {
    return new LongMediator(parentLicenseManager, value);
  }
}
