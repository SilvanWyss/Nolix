/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.environment.license;

import ch.nolix.core.errorcontrol.validator.Validator;

/**
 * @author Silvan Wyss
 */
public final class LongMediator {
  private final LicenseManagerUnit parentLicenseManager;

  private final long memberValue;

  LongMediator(final LicenseManagerUnit parentLicenseManager, final long value) {
    Validator.assertThat(parentLicenseManager).thatIsNamed("parent LicenseManager").isNotNull();

    this.parentLicenseManager = parentLicenseManager;
    memberValue = value;
  }

  public ConditionMediator isBiggerThan(final long value) {
    return new ConditionMediator(parentLicenseManager, memberValue > value);
  }
}
