//package declaration
package ch.nolix.core.testing.basetest;

//Java imports
import java.lang.reflect.Method;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.coreapi.testingapi.testapi.Cleanup;
import ch.nolix.coreapi.testingapi.testapi.IgnoreTimeout;
import ch.nolix.coreapi.testingapi.testapi.Setup;

//class
public final class TestCaseWrapper {

  //attribute
  private final BaseTest parentTest;

  //attribute
  private final Method testCase;

  //optional attribute
  private final Method setup;

  //optional attribute
  private final Method cleanup;

  //constructor
  public TestCaseWrapper(final BaseTest parentTest, final Method testCase) {

    if (parentTest == null) {
      throw ArgumentIsNullException.forArgumentName("parent test");
    }

    if (testCase == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.TEST_CASE);
    }

    this.parentTest = parentTest;
    this.testCase = testCase;
    setup = extractSetup();
    cleanup = extractCleanup();
  }

  //method
  public BaseTest createTestInstance() {
    return parentTest.toTestInstance();
  }

  //method
  public BaseTest getParentTest() {
    return parentTest;
  }

  //method
  public Method getStoredCleanup() {

    supposeHasCleanup();

    return cleanup;
  }

  //method
  public Method getStoredSetup() {

    supposeHasSetup();

    return setup;
  }

  //method
  public Method getStoredTestCase() {
    return testCase;
  }

  //method
  public boolean hasCleanup() {
    return (cleanup != null);
  }

  //method
  public boolean hasSetup() {
    return (setup != null);
  }

  //method
  public boolean testCaseHasTimeout() {
    return !ReflectionHelper.elementHasAnnotation(getStoredTestCase(), IgnoreTimeout.class);
  }

  //method
  private Method extractCleanup() {

    Method lCleanup = null;
    for (final var m : parentTest.getClass().getMethods()) {

      if (ReflectionHelper.elementHasAnnotation(m, Cleanup.class)) {

        if (lCleanup != null) {
          throw InvalidArgumentException.forArgumentAndErrorPredicate(parentTest.getClass(),
            "contains more than 1 cleanup");
        }

        lCleanup = m;
      }
    }

    return lCleanup;
  }

  //method
  private Method extractSetup() {

    Method lSetup = null;
    for (final var m : parentTest.getClass().getMethods()) {

      if (ReflectionHelper.elementHasAnnotation(m, Setup.class)) {

        if (lSetup != null) {
          throw InvalidArgumentException.forArgumentAndErrorPredicate(parentTest.getClass(),
            "contains more than 1 setup");
        }

        lSetup = m;
      }
    }

    return lSetup;
  }

  //method
  private void supposeHasCleanup() {
    if (!hasCleanup()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalogue.CLEANUP);
    }
  }

  //method
  private void supposeHasSetup() {
    if (!hasSetup()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalogue.SETUP);
    }
  }
}
