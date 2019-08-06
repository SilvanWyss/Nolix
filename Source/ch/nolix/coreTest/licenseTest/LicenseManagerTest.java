//package declaration
package ch.nolix.coreTest.licenseTest;

import ch.nolix.core.containers.IContainer;
import ch.nolix.core.containers.List;
import ch.nolix.core.license.License;
import ch.nolix.core.license.LicenseManager;
import ch.nolix.core.license.Permission;
import ch.nolix.core.test.Test;

//test class
public final class LicenseManagerTest extends Test {
	
	//test case
	public void testCase_requirePermission_whenLicenseIsNotThere() {
		
		//execution & verification
		expect(() -> LicenseManager.requirePermission(TestPermission.class)).throwsException();
	}
	
	//test case
	public void testCase_requirePermission_whenLicenseIsThere() {
		
		//setup
		final var testLicense = new TestLicense();
		LicenseManager.addLicense(testLicense);
		
		//execution & verification
		expect(() -> LicenseManager.requirePermission(TestPermission.class)).doesNotThrowException();
		
		//cleanup
		LicenseManager.removeLicense(testLicense);
	}
	
	//inner class
	private final class TestLicense extends License {
		
		//method
		@Override
		public IContainer<Permission> getPermissions() {
			return new List<>(new TestPermission());
		}
	}
	
	//inner class
	private final class TestPermission extends Permission {}
}
