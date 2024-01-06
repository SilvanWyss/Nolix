//package declaration
package ch.nolix.core.testing.basetest;

//Java imports
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.independent.container.List;
import ch.nolix.coreapi.methodapi.loggingapi.ILinePrinter;
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.GroupCloseable;
import ch.nolix.coreapi.testingapi.testapi.TestCase;

//class
/**
 * @author Silvan Wyss
 * @date 2016-09-01
 */
public abstract class BaseTest { //NOSONAR: BaseTest does not have abstract methods.

  //attribute
  private boolean isTestInstance;

  //multi-attribute
  private final List<Error> expectationErrors = new List<>();

  //multi-attribute
  private final List<AutoCloseable> closableElements = new List<>();

  //method
  /**
   * @return the name of the current {@link BaseTest}.
   */
  public final String getName() {
    return getClass().getName();
  }

  //method
  /**
   * @return the simple name of the current {@link BaseTest}.
   */
  public final String getSimpleName() {
    return getClass().getSimpleName();
  }

  //method
  /**
   * Runs the test cases of the current {@link BaseTest} and prints out the result
   * to the console.
   */
  public final void run() {
    runAndGetResult(new StandardConsoleLinePrinter());
  }

  //method
  /**
   * Runs the test cases of the current {@link BaseTest} and prints out the result
   * using the given linePrinter.
   * 
   * @param linePrinter
   * @return the result when the run is finished.
   * @throws ArgumentIsNullException if the given linePrinter is null.
   */
  public TestResult runAndGetResult(final ILinePrinter linePrinter) {

    final var testRun = new TestRun(this, linePrinter);

    return testRun.runAndGetResult();
  }

  //method
  /**
   * Lets the current {@link BaseTest} register the given element to close.
   * 
   * @param element
   */
  protected final void registerToClose(final AutoCloseable element) {
    if (element != null) {
      closableElements.addAtEnd(element);
    }
  }

  //method
  /**
   * Lets the current {@link BaseTest} register the given element to close.
   * 
   * @param element
   */
  protected final void registerToClose(final GroupCloseable element) {
    if (element != null) {
      registerToClose(element::close);
    }
  }

  //method
  /**
   * Adds the given expectationError to the current {@link BaseTest}.
   * 
   * @param expectationError
   */
  protected final void addExpectationError(final String expectationError) {

    String className = null;
    var lineNumber = -1;
    Class<?> lClass = getClass();
    while (lClass != null) {

      for (final var ste : Thread.currentThread().getStackTrace()) {
        if (ste.getClassName().equals(lClass.getName())) {
          className = ste.getClassName();
          lineNumber = ste.getLineNumber();
          break;
        }
      }

      if (className != null) {
        break;
      }

      lClass = lClass.getSuperclass();
    }

    expectationErrors.addAtEnd(new Error(expectationError, className, lineNumber));
  }

  //method
  /**
   * @return the expectation errors of the current {@link BaseTest}.
   */
  final List<Error> getExpectationErrors() {
    return expectationErrors;
  }

  //method
  /**
   * @return the {@link AutoCloseable}s of the current {@link BaseTest}.
   */
  final List<AutoCloseable> getStoredClosableElements() {
    return closableElements;
  }

  //method
  /**
   * @return the test cases of the current {@link BaseTest} ordered
   *         alphabetically.
   */
  List<Method> getStoredTestCasesOrderedAlphabetically() {

    final var testCasesOrderedAlphabetically = new List<Method>();
    final var testCases = getStoredTestCases();
    while (!testCases.isEmpty()) {

      var testCase = testCases.getStoredFirst();
      for (final var tc : testCases) {
        if (tc.getName().compareTo(testCase.getName()) < 0) {
          testCase = tc;
        }
      }

      testCases.removeFirstOccurrenceOf(testCase);
      testCasesOrderedAlphabetically.addAtEnd(testCase);
    }

    return testCasesOrderedAlphabetically;
  }

  //method
  /**
   * @return true if the current {@link BaseTest} is a test instance.
   */
  final boolean isTestInstance() {
    return isTestInstance;
  }

  //method
  /**
   * @return a new test instance from the current {@link BaseTest}.
   */
  final BaseTest toTestInstance() {

    final var testInstance = getCopy();
    testInstance.isTestInstance = true;

    return testInstance;
  }

  //method
  /**
   * @return a new copy of the current {@link BaseTest}.
   * @throws InvalidArgumentException if the current {@link BaseTest} could not be
   *                                  copied.
   */
  private BaseTest getCopy() {
    try {
      return ReflectionHelper.getDefaultConstructor(getClass()).newInstance();
    } catch (final
    IllegalAccessException
    | InstantiationException
    | InvocationTargetException exception) {
      throw InvalidArgumentException.forArgumentAndErrorPredicateAndCause(this, "could not be copied", exception);
    }
  }

  /**
   * @return the test cases of the current {@link BaseTest}.
   */
  private List<Method> getStoredTestCases() {

    final var testCases = new List<Method>();
    Class<?> lClass = getClass();
    while (lClass != null) {

      for (final var m : lClass.getDeclaredMethods()) {
        if (isTestCase(m)) {
          testCases.addAtEnd(m);
        }
      }

      lClass = lClass.getSuperclass();
    }

    return testCases;
  }

  //method
  /**
   * @param method
   * @return true if the given method is a test case.
   */
  private boolean isTestCase(final Method method) {
    return (method != null && ReflectionHelper.elementHasAnnotation(method, TestCase.class));
  }
}
