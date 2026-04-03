/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.environment.license;

import ch.nolix.base.validation.validator.Validator;

/**
 * @author Silvan Wyss
 */
public final class ConditionMediator {
  private final LicenseManagerUnit parentLicenseManager;

  private final boolean condition;

  private ConditionMediator(final LicenseManagerUnit parentLicenseManager, final boolean condition) {
    Validator.assertThat(parentLicenseManager).thatIsNamed("parent LicenseManager").isNotNull();

    this.parentLicenseManager = parentLicenseManager;
    this.condition = condition;
  }

  public static ConditionMediator forLicenseManagerAndCondition(final LicenseManagerUnit licenseManager,
    final boolean condition) {
    return new ConditionMediator(licenseManager, condition);
  }

  public <F extends AbstractFeature> AdditionalConditionMediator thenRequireFeature(final Class<F> type) {
    if (condition) {
      parentLicenseManager.requireFeature(type);
    }

    return AdditionalConditionMediator.forLicenseManager(parentLicenseManager);
  }
}
