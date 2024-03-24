//package declaration
package ch.nolix.coretest.licensetest;

//Java imports
import java.util.Objects;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.license.Feature;
import ch.nolix.core.license.GlobalLicenseManager;
import ch.nolix.core.license.License;
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//class
final class GlobalLicenseManagerTest extends StandardTest {

  //method
  @Test
  void testCase_requireFeature_whenLicenseIsNotThere() {

    //execution & verification
    expectRunning(() -> GlobalLicenseManager.requireFeature(TestFeature.class)).throwsException();
  }

  //method
  @Test
  void testCase_requireFeature_whenLicenseIsThere() {

    //setup
    final var testLicense = new TestLicense();
    testLicense.activate("0000-0000");
    GlobalLicenseManager.addLicense(testLicense);

    //execution & verification
    expectRunning(() -> GlobalLicenseManager.requireFeature(TestFeature.class)).doesNotThrowException();

    //cleanup
    GlobalLicenseManager.removeLicense(testLicense);
  }

  //constant
  private static final class TestLicense extends License {

    @Override
    protected boolean acceptsFilteredKey(String key) {
      return Objects.equals(key, "00000000");
    }
  }

  //constant
  //This class must be that it can be processed by reflection.
  static final class TestFeature extends Feature {

    @Override
    public IContainer<Class<?>> getAuthorizedLicenseTypes() {
      return LinkedList.withElement(TestLicense.class);
    }
  }
}
