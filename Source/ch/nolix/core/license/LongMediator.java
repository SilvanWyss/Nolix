package ch.nolix.core.license;

import ch.nolix.core.errorcontrol.validator.Validator;

public final class LongMediator {

  private final LicenseManager parentLicenseManager;

  private final long value;

  LongMediator(final LicenseManager parentLicenseManager, final long value) {

    Validator.assertThat(parentLicenseManager).thatIsNamed("parent LicenseManager").isNotNull();

    this.parentLicenseManager = parentLicenseManager;
    this.value = value;
  }

  public ConditionMediator isBiggerThan(final long value) {
    return new ConditionMediator(parentLicenseManager, this.value > value);
  }
}
