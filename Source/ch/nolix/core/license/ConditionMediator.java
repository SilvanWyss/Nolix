package ch.nolix.core.license;

import ch.nolix.core.errorcontrol.validator.Validator;

public final class ConditionMediator {

  private final LicenseManagerUnit parentLicenseManager;

  private final boolean condition;

  ConditionMediator(final LicenseManagerUnit parentLicenseManager, final boolean condition) {

    Validator.assertThat(parentLicenseManager).thatIsNamed("parent LicenseManager").isNotNull();

    this.parentLicenseManager = parentLicenseManager;
    this.condition = condition;
  }

  public <F extends AbstractFeature> AdditionalConditionMediator thenRequireFeature(final Class<F> type) {

    if (condition) {
      parentLicenseManager.requireFeature(type);
    }

    return new AdditionalConditionMediator(parentLicenseManager);
  }
}
