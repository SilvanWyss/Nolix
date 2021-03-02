//package declaration
package ch.nolix.common.license;

import ch.nolix.common.errorcontrol.validator.Validator;

//class
public final class ConditionMediator {
	
	//attributes
	private final LicenseManager parentLicenseManager;
	private final boolean condition;
	
	//constructor
	ConditionMediator(final LicenseManager parentLicenseManager, final boolean condition) {
		
		Validator.assertThat(parentLicenseManager).thatIsNamed("parent LicenseManager").isNotNull();
		
		this.parentLicenseManager = parentLicenseManager;
		this.condition = condition;
	}
	
	//method
	public <F extends Feature> AdditionalConditionMediator thenRequireFeature(final Class<F> type) {
		
		if (condition) {
			parentLicenseManager.requireFeature(type);
		}
		
		return new AdditionalConditionMediator(parentLicenseManager);
	}
}
