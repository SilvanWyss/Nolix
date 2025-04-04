package ch.nolix.core.license;

import java.util.Objects;

import org.junit.jupiter.api.Test;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

final class LicenseManagerTest extends StandardTest {

  private static final class TestLicense extends License {

    @Override
    protected boolean acceptsFilteredKey(String key) {
      return Objects.equals(key, "00000000");
    }
  }

  //This class must be public that it can be processed by reflection.
  public static final class TestFeature extends Feature {

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
    testLicense.activate("0000-0000");
    LicenseManager.addLicense(testLicense);

    //execution & verification
    expectRunning(() -> LicenseManager.requireFeature(TestFeature.class)).doesNotThrowException();

    //cleanup
    LicenseManager.removeLicense(testLicense);
  }
}
