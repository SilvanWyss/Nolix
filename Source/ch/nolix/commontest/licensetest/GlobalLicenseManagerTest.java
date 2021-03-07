//package declaration
package ch.nolix.commontest.licensetest;

//Java import
import java.util.Objects;

import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.license.GlobalLicenseManager;
import ch.nolix.common.license.Feature;
import ch.nolix.common.license.License;
import ch.nolix.common.testing.basetest.TestCase;
import ch.nolix.common.testing.test.Test;

//class
public final class GlobalLicenseManagerTest extends Test {
	
	//method
	@TestCase
	public void testCase_requireFeature_whenLicenseIsNotThere() {
		
		//execution & verification
		expectRunning(() -> GlobalLicenseManager.requireFeature(TestFeature.class)).throwsException();
	}
	
	//method
	@TestCase
	public void testCase_requireFeature_whenLicenseIsThere() {
		
		//setup
		final var testLicense = new TestLicense();
		testLicense.activate("0000-0000");
		GlobalLicenseManager.addLicense(testLicense);
		
		//execution & verification
		expectRunning(() -> GlobalLicenseManager.requireFeature(TestFeature.class)).doesNotThrowException();
		
		//cleanup
		GlobalLicenseManager.removeLicense(testLicense);
	}
	
	//static class
	private static final class TestLicense extends License {
		
		@Override
		protected boolean acceptsFilteredKey(String key) {
			return Objects.equals(key, "00000000");
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
