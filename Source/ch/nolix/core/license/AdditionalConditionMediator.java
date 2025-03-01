package ch.nolix.core.license;

import ch.nolix.core.errorcontrol.validator.Validator;

public final class AdditionalConditionMediator {

  private final LicenseManager parentLicenseManager;

  AdditionalConditionMediator(final LicenseManager parentLicenseManager) {

    Validator.assertThat(parentLicenseManager).thatIsNamed("parent LicenseManager").isNotNull();

    this.parentLicenseManager = parentLicenseManager;
  }

  public LongMediator andWhen(final long value) {
    return new LongMediator(parentLicenseManager, value);
  }
}
