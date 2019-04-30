//package declaration
package ch.nolix.core.license;

//own import
import ch.nolix.core.validator.Validator;

//class
public final class ConditionMediator {
	
	//attributes
	private final InternalLicenseManager parentLicenseManager;
	private final boolean condition;
	
	//package-visible constructor
	ConditionMediator(final InternalLicenseManager parentLicenseManager, final boolean condition) {
		
		Validator.suppose(parentLicenseManager).thatIsNamed("parent LicenseManager").isNotNull();
		
		this.parentLicenseManager = parentLicenseManager;
		this.condition = condition;
	}
	
	//method
	public <P extends Permission> void thenRequirePermission(final Class<P> type) {
		if (condition) {
			parentLicenseManager.requirePermission(type);
		}
	}
}
