//package declaration
package ch.nolix.core.testing.basetest;

//Java imports
import java.lang.reflect.Method;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;

//class
final class TestCaseRun {

  //constant
  public static final long MAX_DURATION_IN_MILLISECONDS = 5000;

  //constant
  private static final StackTraceElementFinder STACK_TRACE_ELEMENT_FINDER = new StackTraceElementFinder();

  //attribute
  private final TestCaseWrapper testCaseWrapper;

  //attribute
  private boolean started;

  //constructor
  public TestCaseRun(final BaseTest parentTest, final Method testCase) {
    this(new TestCaseWrapper(parentTest, testCase));
  }

  //constructor
  public TestCaseRun(final TestCaseWrapper testCaseWrapper) {

    if (testCaseWrapper == null) {
      throw ArgumentIsNullException.forArgumentType(TestCaseWrapper.class);
    }

    this.testCaseWrapper = testCaseWrapper;
  }

  //method
  public boolean hasStarted() {
    return started;
  }

  //method
  public boolean hasTimeout() {
    return testCaseWrapper.testCaseHasTimeout();
  }

  //method
  public TestCaseResult runAndGetResult() {

    if (!hasTimeout()) {
      return runAndGetResultWithoutTimeout();
    }

    return runAndGetResultWithTimeout();
  }

  //method
  public TestCaseResult runAndGetResultWithoutTimeout() {

    setStarted();
    final var testCaseRunner = new TestCaseRunner(testCaseWrapper);

    //This loop suffers from being optimized away by the compiler or the JVM.
    while (!testCaseRunner.isFinished()) {

      //This statement, which is theoretically unnecessary, makes that the current
      //loop is not optimized away.
      System.err.flush(); //NOSONAR: This statement is used to keep the loop.
    }

    return testCaseRunner.getResult();
  }

  //method
  public TestCaseResult runAndGetResultWithTimeout() {

    setStarted();
    final var testCaseRunner = new TestCaseRunner(testCaseWrapper);

    //This loop suffers from being optimized away by the compiler or the JVM.
    while (!testCaseRunner.isFinished()) {

      //This statement, which is theoretically unnecessary, makes that the current
      //loop is not optimized away.
      System.err.flush(); //NOSONAR: This statement is used to keep the loop.

      if (testCaseRunner.getRuntimeInMilliseconds() > MAX_DURATION_IN_MILLISECONDS) {
        testCaseRunner.stop(
          new Error(
            "Reached timeout.",
            testCaseWrapper.getStoredTestCase().getName(),
            STACK_TRACE_ELEMENT_FINDER
              .getStackTraceElementsOfRunningMethod(testCaseWrapper.getStoredTestCase())
              .getStoredFirst()
              .getLineNumber()));
      }
    }

    return testCaseRunner.getResult();
  }

  //method
  private void assertHasNotStarted() {
    if (hasStarted()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "has started already");
    }
  }

  //method
  private void setStarted() {
    assertHasNotStarted();
    started = true;
  }
}
