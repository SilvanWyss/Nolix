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

  /**
   * @param licenseManager
   * @param condition
   * @return a new {@link ConditionMediator} for the given licenseManager and
   *         condition
   */
  public static ConditionMediator forLicenseManagerAndCondition(final LicenseManagerUnit licenseManager,
    final boolean condition) {
    return new ConditionMediator(licenseManager, condition);
  }

  /**
   * Requires the feature of the given type if the condition of the current
   * {@link ConditionMediator} is fulfilled.
   * 
   * @param type
   * @param <F>  the type of the feature
   * @return a new {@link AdditionalConditionMediator}
   * @throws RuntimeException if the condition of the current
   *                          {@link ConditionMediator} is fulfilled and the
   *                          feature of the given type is not licensed
   */
  public <F extends AbstractFeature> AdditionalConditionMediator thenRequireFeature(final Class<F> type) {
    if (condition) {
      parentLicenseManager.requireFeature(type);
    }

    return AdditionalConditionMediator.forLicenseManager(parentLicenseManager);
  }
}
