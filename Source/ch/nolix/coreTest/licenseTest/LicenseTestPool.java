//package declaration
package ch.nolix.coreTest.licenseTest;

//own import
import ch.nolix.core.testoid.TestPool;

//class
public final class LicenseTestPool extends TestPool {
	
	//constructor
	public LicenseTestPool() {
		addTestClass(LicenseManagerTest.class);
	}
}
