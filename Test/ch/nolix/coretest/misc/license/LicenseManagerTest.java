package ch.nolix.coretest.misc.license;

import java.util.Objects;

import org.junit.jupiter.api.Test;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.environment.license.AbstractFeature;
import ch.nolix.core.environment.license.License;
import ch.nolix.core.environment.license.LicenseManager;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.container.base.IContainer;

final class LicenseManagerTest extends StandardTest {

  private static final class TestLicense extends License {

    @Override
    protected boolean acceptsRefinedKey(String key) {
      return Objects.equals(key, "00000000");
    }
  }

  //This class must be public that it can be processed by reflection.
  public static final class TestFeature extends AbstractFeature {

    @Override
    public IContainer<Class<?>> getAuthorizedLicenseTypes() {
      return LinkedList.withElement(TestLicense.class);
    }
  }

  @Test
  void testCase_requireFeature_whenLicenseIsNotThere() {

    //execution & verification
    expectRunning(() -> LicenseManager.requireFeature(TestFeature.class)).throwsException();
  }

  @Test
  void testCase_requireFeature_whenLicenseIsThere() {

    //setup
    final var testLicense = new TestLicense();
    testLicense.activateWithKey("0000-0000");
    LicenseManager.addLicense(testLicense);

    //execution & verification
    expectRunning(() -> LicenseManager.requireFeature(TestFeature.class)).doesNotThrowException();

    //cleanup
    LicenseManager.removeLicense(testLicense);
  }
}
