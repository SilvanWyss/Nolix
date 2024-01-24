//package declaration
package ch.nolix.core.testing.basetest;

//Java imports
import java.lang.reflect.InvocationTargetException;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.independent.container.List;
import ch.nolix.coreapi.errorcontrolapi.loggingapi.ILinePrinter;

//class
public final class TestPoolRun {

  //attribute
  private final TestPool parentTestPool;

  //attribute
  private final ILinePrinter linePrinter;

  //attribute
  private boolean started;

  //attribute
  private int runtimeInMilliseconds = -1;

  //constructor
  public TestPoolRun(final TestPool parentTestPool, final ILinePrinter linePrinter) {

    if (parentTestPool == null) {
      throw ArgumentIsNullException.forArgumentName("parent TestPool");
    }

    if (linePrinter == null) {
      throw ArgumentIsNullException.forArgumentName("line printer");
    }

    this.parentTestPool = parentTestPool;
    this.linePrinter = linePrinter;
  }

  //method
  public String getRuntimeAndUnitAsString() {
    return (String.valueOf(getRuntimeInMilliseconds()) + " ms");
  }

  //method
  public String getRuntimeAndUnitAsStringInBrackets() {
    return ("(" + getRuntimeAndUnitAsString() + ")");
  }

  //method
  public int getRuntimeInMilliseconds() {

    supposeIsFinished();

    return runtimeInMilliseconds;
  }

  //method
  public boolean hasStarted() {
    return started;
  }

  //method
  public boolean isFinished() {
    return (runtimeInMilliseconds > -1);
  }

  //method
  public TestPoolResult runAndGetResult() {

    //setup phase
    setStarted();
    final var startTimeInMilliseconds = System.currentTimeMillis();

    //main phase part 1
    final var testPoolResults = new List<TestPoolResult>();
    for (final var tp : parentTestPool.internalGetTestPools()) {

      final var result = tp.runAndGetResult(linePrinter);

      testPoolResults.addAtEnd(result);
    }

    //main phase part 2
    final var testResults = new List<TestResult>();
    for (final var tc : parentTestPool.internalGetTestClasses()) {

      final var testResult = runTestAndGetResult(tc);

      testResults.addAtEnd(testResult);
    }

    //result phase
    final var testPoolResult = TestPoolResult.forTestPoolResultsAndTestResults(testPoolResults, testResults);
    setFinished((int) (System.currentTimeMillis() - startTimeInMilliseconds));
    printSummaryOfTestPoolResult(testPoolResult);

    return testPoolResult;
  }

  //method
  private <BT extends BaseTest> BT createTestOrNull(final Class<BT> testClass) {
    try {
      return ReflectionTool.getDefaultConstructor(testClass).newInstance();
    } catch (final //NOSONAR: The exception is logged.
    IllegalAccessException
    | InstantiationException
    | InvocationTargetException exception) {
      linePrinter.printErrorLine("-->Could not create a " + testClass.getName() + ".");
      return null;
    }
  }

  //method
  private void printSummaryOfTestPoolResult(final TestPoolResult testPoolResult) {

    linePrinter.printInfoLine(
      "   FINISHED: "
      + testPoolResult.getPassedTestCaseCount()
      + " of "
      + testPoolResult.getTestCaseCount()
      + " test cases of "
      + parentTestPool.getSimpleName()
      + " passed "
      + getRuntimeAndUnitAsStringInBrackets());

    linePrinter.printEmptyLine();
  }

  //method
  private <BT extends BaseTest> TestResult runTestAndGetResult(final Class<BT> testClass) {

    final var test = createTestOrNull(testClass);

    if (test != null) {
      return test.runAndGetResult(linePrinter);
    }

    return TestResult.forTestCaseResults(new List<>());
  }

  //method
  private void setFinished(final int runtimeInMilliseconds) {

    if (runtimeInMilliseconds < 0) {
      throw NegativeArgumentException.forArgumentNameAndArgument("runtime in milliseconds", runtimeInMilliseconds);
    }

    supposeIsNotFinished();

    this.runtimeInMilliseconds = runtimeInMilliseconds;
  }

  //method
  private void setStarted() {

    supposeHasNotStarted();

    started = true;
    linePrinter.printInfoLine("   STARTED: " + parentTestPool.getSimpleName());
    linePrinter.printEmptyLine();
  }

  //method
  private void supposeHasNotStarted() {
    if (hasStarted()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "has started already");
    }
  }

  //method
  private void supposeIsFinished() {
    if (!isFinished()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is not finished");
    }
  }

  //method
  private void supposeIsNotFinished() {
    if (isFinished()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is already finished");
    }
  }
}
