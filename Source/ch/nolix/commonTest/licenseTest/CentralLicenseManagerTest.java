//package declaration
package ch.nolix.commonTest.licenseTest;

//own imports
import ch.nolix.common.baseTest.TestCase;
import ch.nolix.common.containers.IContainer;
import ch.nolix.common.containers.LinkedList;
import ch.nolix.common.license.CentralLicenseManager;
import ch.nolix.common.license.Feature;
import ch.nolix.common.license.License;
import ch.nolix.common.test.Test;

//class
public final class CentralLicenseManagerTest extends Test {
	
	//method
	@TestCase
	public void testCase_requireFeature_whenLicenseIsNotThere() {
		
		//execution & verification
		expect(() -> CentralLicenseManager.requireFeature(TestFeature.class)).throwsException();
	}
	
	//method
	@TestCase
	public void testCase_requireFeature_whenLicenseIsThere() {
		
		//setup
		final var testLicense = new TestLicense("");
		CentralLicenseManager.addLicense(testLicense);
		
		//execution & verification
		expect(() -> CentralLicenseManager.requireFeature(TestFeature.class)).doesNotThrowException();
		
		//cleanup
		CentralLicenseManager.removeLicense(testLicense);
	}
	
	//static class
	private static final class TestLicense extends License {
		
		//constructor
		public TestLicense(final String key) {
			super(key);
		}
		
		@Override
		public boolean accepts(String key) {
			return true;
		}
	}
	
	//static class
	//This class must be public that it can be processed by reflection.
	public static final class TestFeature extends Feature {
		
		@Override
		public IContainer<Class<?>> getAuthorizedLicenseTypes() {
			return new LinkedList<>(TestLicense.class);
		}
	}
}
