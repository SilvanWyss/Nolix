//package declaration
package ch.nolix.coreTest.licenseTest;

import ch.nolix.core.baseTest.TestPool;

//class
public final class LicenseTestPool extends TestPool {
	
	//constructor
	public LicenseTestPool() {
		addTestClass(LicenseManagerTest.class);
	}
}
