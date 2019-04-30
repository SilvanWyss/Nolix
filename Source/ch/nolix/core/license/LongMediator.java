//package declaration
package ch.nolix.core.license;

//own import
import ch.nolix.core.validator.Validator;

//class
public final class LongMediator {
	
	//attributes
	private final InternalLicenseManager parentLicenseManager;
	private final long value;
	
	//package-visible constructor
	LongMediator(final InternalLicenseManager parentLicenseManager, final long value) {
		
		Validator.suppose(parentLicenseManager).thatIsNamed("parent LicenseManager").isNotNull();
		
		this.parentLicenseManager = parentLicenseManager;
		this.value = value;
	}
	
	//method
	public ConditionMediator isBiggerThan(final long value) {
		return new ConditionMediator(parentLicenseManager, this.value > value);
	}
}
