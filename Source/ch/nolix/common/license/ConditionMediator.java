//package declaration
package ch.nolix.common.license;

//own import
import ch.nolix.common.validator.Validator;

//class
public final class ConditionMediator {
	
	//attributes
	private final LicenseManager parentLicenseManager;
	private final boolean condition;
	
	//package-visible constructor
	ConditionMediator(final LicenseManager parentLicenseManager, final boolean condition) {
		
		Validator.suppose(parentLicenseManager).thatIsNamed("parent LicenseManager").isNotNull();
		
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
