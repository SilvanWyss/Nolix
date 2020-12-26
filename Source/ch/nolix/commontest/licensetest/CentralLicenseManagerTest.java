//package declaration
package ch.nolix.commontest.licensetest;

//Java import
import java.util.Objects;

//own imports
import ch.nolix.common.basetest.TestCase;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
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
		final var testLicense = new TestLicense();
		testLicense.activate("0000-0000");
		CentralLicenseManager.addLicense(testLicense);
		
		//execution & verification
		expect(() -> CentralLicenseManager.requireFeature(TestFeature.class)).doesNotThrowException();
		
		//cleanup
		CentralLicenseManager.removeLicense(testLicense);
	}
	
	//static class
	private static final class TestLicense extends License {
		
		@Override
		protected boolean acceptsFilteredKey(String key) {
			return Objects.equals(key, "0000-0000");
		}
	}
	
	//static class
	//This class must be public that it can be processed by reflection.
	public static final class TestFeature extends Feature {
		
		@Override
		public IContainer<Class<?>> getAuthorizedLicenseTypes() {
			return LinkedList.withElements(TestLicense.class);
		}
	}
}
