/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.environment.license;

import ch.nolix.base.validation.validator.Validator;

/**
 * @author Silvan Wyss
 */
public final class LongMediator {
  private final LicenseManagerUnit parentLicenseManager;

  private final long memberValue;

  private LongMediator(final LicenseManagerUnit parentLicenseManager, final long value) {
    Validator.assertThat(parentLicenseManager).thatIsNamed("parent LicenseManager").isNotNull();

    this.parentLicenseManager = parentLicenseManager;
    memberValue = value;
  }

  public static LongMediator forLicenseManagerAndValue(
    final LicenseManagerUnit parentLicenseManager,
    final long value) {
    return new LongMediator(parentLicenseManager, value);
  }

  public ConditionMediator isBiggerThan(final long value) {
    return new ConditionMediator(parentLicenseManager, memberValue > value);
  }
}
