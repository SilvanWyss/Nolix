//package declaration
package ch.nolix.core.license;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;

//class
public final class LongMediator {

  // attribute
  private final LicenseManager parentLicenseManager;

  // attribute
  private final long value;

  // constructor
  LongMediator(final LicenseManager parentLicenseManager, final long value) {

    GlobalValidator.assertThat(parentLicenseManager).thatIsNamed("parent LicenseManager").isNotNull();

    this.parentLicenseManager = parentLicenseManager;
    this.value = value;
  }

  // method
  public ConditionMediator isBiggerThan(final long value) {
    return new ConditionMediator(parentLicenseManager, this.value > value);
  }
}
