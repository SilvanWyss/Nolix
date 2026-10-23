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

  /**
   * @param parentLicenseManager
   * @param value
   * @return a new {@link LongMediator} for the given parentLicenseManager and
   *         value
   */
  public static LongMediator forLicenseManagerAndValue(
    final LicenseManagerUnit parentLicenseManager,
    final long value) {
    return new LongMediator(parentLicenseManager, value);
  }

  /**
   * @param value
   * @return a new {@link ConditionMediator} with the condition that the value of
   *         the current {@link LongMediator} is bigger than the given value
   */
  public ConditionMediator isBiggerThan(final long value) {
    return ConditionMediator.forLicenseManagerAndCondition(parentLicenseManager, memberValue > value);
  }
}
