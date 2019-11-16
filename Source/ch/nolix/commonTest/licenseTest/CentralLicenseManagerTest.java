//package declaration
package ch.nolix.commonTest.licenseTest;

//own imports
import ch.nolix.common.containers.IContainer;
import ch.nolix.common.containers.List;
import ch.nolix.common.license.CentralLicenseManager;
import ch.nolix.common.license.Feature;
import ch.nolix.common.license.License;
import ch.nolix.common.test.Test;

//test class
public final class CentralLicenseManagerTest extends Test {
	
	//test case
	public void testCase_requireFeature_whenLicenseIsNotThere() {
		
		//execution & verification
		expect(() -> CentralLicenseManager.requireFeature(TestFeature.class)).throwsException();
	}
	
	//test case
	public void testCase_requireFeature_whenLicenseIsThere() {
		
		//setup
		final var testLicense = new TestLicense("");
		CentralLicenseManager.addLicense(testLicense);
		
		//execution & verification
		expect(() -> CentralLicenseManager.requireFeature(TestFeature.class)).doesNotThrowException();
		
		//cleanup
		CentralLicenseManager.removeLicense(testLicense);
	}
	
	//inner class
	private final class TestLicense extends License {
		
		//constructor
		public TestLicense(final String key) {
			super(key);
		}
		
		@Override
		public boolean accepts(String key) {
			return true;
		}
	}
	
	//inner class
	private final class TestFeature extends Feature {
		
		@Override
		public IContainer<Class<?>> getAuthorizedLicenseTypes() {
			return new List<>(TestLicense.class);
		}
	}
}
