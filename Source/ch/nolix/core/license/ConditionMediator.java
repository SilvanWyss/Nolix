//package declaration
package ch.nolix.core.license;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;

//class
public final class ConditionMediator {

  // attribute
  private final LicenseManager parentLicenseManager;

  // attribute
  private final boolean condition;

  // constructor
  ConditionMediator(final LicenseManager parentLicenseManager, final boolean condition) {

    GlobalValidator.assertThat(parentLicenseManager).thatIsNamed("parent LicenseManager").isNotNull();

    this.parentLicenseManager = parentLicenseManager;
    this.condition = condition;
  }

  // method
  public <F extends Feature> AdditionalConditionMediator thenRequireFeature(final Class<F> type) {

    if (condition) {
      parentLicenseManager.requireFeature(type);
    }

    return new AdditionalConditionMediator(parentLicenseManager);
  }
}
