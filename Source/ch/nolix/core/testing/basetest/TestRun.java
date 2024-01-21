//package declaration
package ch.nolix.core.testing.basetest;

//Java imports
import java.lang.reflect.Method;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.independent.container.List;
import ch.nolix.coreapi.functionapi.loggingapi.ILinePrinter;

//class
public final class TestRun {

  //attribute
  private final BaseTest parentTest;

  //attribute
  private final ILinePrinter linePrinter;

  //attribute
  private boolean started;

  //attribute
  private int runtimeInMilliseconds = -1;

  //constructor
  public TestRun(final BaseTest parentTest, final ILinePrinter linePrinter) {

    if (parentTest == null) {
      throw ArgumentIsNullException.forArgumentName("parent test");
    }

    if (linePrinter == null) {
      throw ArgumentIsNullException.forArgumentName("line printer");
    }

    this.parentTest = parentTest;
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
  public TestResult runAndGetResult() {

    //setup phase
    setStarted();
    final var startTimeInMilliseconds = System.currentTimeMillis();
    linePrinter.printInfoLine("   STARTED: " + parentTest.getSimpleName());

    //main phase
    final var testCaseResults = new List<TestCaseResult>();
    for (final var tc : getStoredTestCasesOrderedAlphabetically()) {

      final var testCaseResult = new TestCaseRun(parentTest, tc).runAndGetResult();

      testCaseResults.addAtEnd(testCaseResult);

      printTestCaseResult(testCaseResult);
    }

    //result phase
    final var testResult = TestResult.forTestCaseResults(testCaseResults);
    setFinished((int) (System.currentTimeMillis() - startTimeInMilliseconds));
    printSummaryOfTestResult(testResult);
    return testResult;
  }

  //method
  private List<Method> getStoredTestCasesOrderedAlphabetically() {
    return parentTest.getStoredTestCasesOrderedAlphabetically();
  }

  //method
  private void printSummaryOfTestResult(final TestResult testResult) {

    linePrinter.printInfoLine(
      "   FINISHED: "
      + testResult.getPassedTestCaseCount()
      + " of "
      + testResult.getTestCaseCount()
      + " test cases of "
      + parentTest.getSimpleName()
      + " passed "
      + getRuntimeAndUnitAsStringInBrackets());

    linePrinter.printEmptyLine();
  }

  //method
  private void printTestCaseResult(final TestCaseResult testCaseResult) {
    if (testCaseResult.isPassed()) {
      linePrinter.printInfoLines(testCaseResult.getOutputLines());
    } else {
      linePrinter.printErrorLines(testCaseResult.getOutputLines());
    }
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
  }

  //method
  private void supposeHasNotStarted() {
    if (hasStarted()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "started already");
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
