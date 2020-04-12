//package declaration
package ch.nolix.common.license;

//own import
import ch.nolix.common.validator.Validator;

//class
public final class LongMediator {
	
	//attributes
	private final LicenseManager parentLicenseManager;
	private final long value;
	
	//constructor
	LongMediator(final LicenseManager parentLicenseManager, final long value) {
		
		Validator.assertThat(parentLicenseManager).thatIsNamed("parent LicenseManager").isNotNull();
		
		this.parentLicenseManager = parentLicenseManager;
		this.value = value;
	}
	
	//method
	public ConditionMediator isBiggerThan(final long value) {
		return new ConditionMediator(parentLicenseManager, this.value > value);
	}
}
