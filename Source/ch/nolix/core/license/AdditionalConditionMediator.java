//package declaration
package ch.nolix.core.license;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;

//class
public final class AdditionalConditionMediator {

  //attribute
  private final LicenseManager parentLicenseManager;

  //constructor
  AdditionalConditionMediator(final LicenseManager parentLicenseManager) {

    GlobalValidator.assertThat(parentLicenseManager).thatIsNamed("parent LicenseManager").isNotNull();

    this.parentLicenseManager = parentLicenseManager;
  }

  //method
  public LongMediator andWhen(final long value) {
    return new LongMediator(parentLicenseManager, value);
  }
}
