//package declaration
package ch.nolix.commonTest.licenseTest;

//own import
import ch.nolix.common.baseTest.TestPool;

//class
public final class LicenseTestPool extends TestPool {
	
	//constructor
	public LicenseTestPool() {
		addTestClass(CentralLicenseManagerTest.class);
	}
}
