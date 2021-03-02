//package declaration
package ch.nolix.common.license;

import ch.nolix.common.errorcontrol.validator.Validator;

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
