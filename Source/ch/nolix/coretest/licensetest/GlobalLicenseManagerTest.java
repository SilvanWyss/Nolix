//package declaration
package ch.nolix.coretest.licensetest;

//Java imports
import java.util.Objects;

import ch.nolix.core.container.main.LinkedList;
//own imports
import ch.nolix.core.containerapi.IContainer;
import ch.nolix.core.license.Feature;
import ch.nolix.core.license.GlobalLicenseManager;
import ch.nolix.core.license.License;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

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
