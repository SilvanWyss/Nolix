//package declaration
package ch.nolix.commonTest.licenseTest;

import ch.nolix.common.baseTest.TestPool;

//class
public final class LicenseTestPool extends TestPool {
	
	//constructor
	public LicenseTestPool() {
		addTestClass(LicenseManagerTest.class);
	}
}
