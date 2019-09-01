//package declaration
package ch.nolix.commonTest.licenseTest;

import ch.nolix.common.containers.IContainer;
import ch.nolix.common.containers.List;
import ch.nolix.common.license.License;
import ch.nolix.common.license.LicenseManager;
import ch.nolix.common.license.Permission;
import ch.nolix.common.test.Test;

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
