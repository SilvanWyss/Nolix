package ch.nolix.core.license;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;

public final class ConditionMediator {

  private final LicenseManager parentLicenseManager;

  private final boolean condition;

  ConditionMediator(final LicenseManager parentLicenseManager, final boolean condition) {

    GlobalValidator.assertThat(parentLicenseManager).thatIsNamed("parent LicenseManager").isNotNull();

    this.parentLicenseManager = parentLicenseManager;
    this.condition = condition;
  }

  public <F extends Feature> AdditionalConditionMediator thenRequireFeature(final Class<F> type) {

    if (condition) {
      parentLicenseManager.requireFeature(type);
    }

    return new AdditionalConditionMediator(parentLicenseManager);
  }
}
