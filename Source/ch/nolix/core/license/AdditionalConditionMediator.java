//package declaration
package ch.nolix.core.license;

import ch.nolix.core.errorcontrol.validator.Validator;

//class
public final class AdditionalConditionMediator {
	
	//attribute
	private final LicenseManager parentLicenseManager;
	
	//constructor
	AdditionalConditionMediator(final LicenseManager parentLicenseManager) {
		
		Validator.assertThat(parentLicenseManager).thatIsNamed("parent LicenseManager").isNotNull();
		
		this.parentLicenseManager = parentLicenseManager;
	}
	
	//method
	public LongMediator andWhen(final long value) {
		return new LongMediator(parentLicenseManager, value);
	}
}
