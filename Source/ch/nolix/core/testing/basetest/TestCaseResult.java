//package declaration
package ch.nolix.core.testing.basetest;

//Java imports
import java.lang.reflect.Method;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.independent.container.List;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
public final class TestCaseResult {

  //attribute
  private final Method testCase;

  //attribute
  private final int runtimeInMilliseconds;

  //optional attribute
  private final Error exceptionError;

  //multi-attribute
  private final List<Error> expectationErrors;

  //constructor
  public TestCaseResult(
    final Method testCase,
    final int runtimeInMilliseconds,
    final List<Error> expectationErrors) {

    if (testCase == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.TEST_CASE);
    }

    if (runtimeInMilliseconds < 0) {
      throw NegativeArgumentException.forArgumentNameAndArgument("runtime in milliseconds", runtimeInMilliseconds);
    }

    if (expectationErrors == null) {
      throw ArgumentIsNullException.forArgumentName("expectation errors");
    }

    this.testCase = testCase;
    this.runtimeInMilliseconds = runtimeInMilliseconds;
    exceptionError = null;
    this.expectationErrors = expectationErrors;
  }

  //constructor
  public TestCaseResult(
    final Method testCase,
    final int runtimeInMilliseconds,
    final List<Error> expectationErrors,
    final Error exceptionError) {

    if (testCase == null) {
      throw ArgumentIsNullException.forArgumentName("test case method");
    }

    if (runtimeInMilliseconds < 0) {
      throw NegativeArgumentException.forArgumentNameAndArgument("runtime in milliseconds", runtimeInMilliseconds);
    }

    if (expectationErrors == null) {
      throw ArgumentIsNullException.forArgumentName("expection errors");
    }

    if (exceptionError == null) {
      throw ArgumentIsNullException.forArgumentName("exception error");
    }

    this.testCase = testCase;
    this.runtimeInMilliseconds = runtimeInMilliseconds;
    this.exceptionError = exceptionError;
    this.expectationErrors = expectationErrors;
  }

  //method
  public int getExpectationErrorCount() {
    return expectationErrors.getElementCount();
  }

  //method
  public List<String> getOutputLines() {

    if (isPassed()) {
      return getOutputLinesWhenPassed();
    }

    return getOutputLinesWhenFailed();
  }

  //method
  public String getRuntimeAndUnitAsString() {
    return (String.valueOf(getRuntimeInMilliseconds()) + " ms");
  }

  //method
  public int getRuntimeInMilliseconds() {
    return runtimeInMilliseconds;
  }

  //method
  public String getTestCaseName() {
    return testCase.getName();
  }

  //method
  public boolean hasExceptionError() {
    return (exceptionError != null);
  }

  //method
  public boolean hasExpectationErrors() {
    return !expectationErrors.isEmpty();
  }

  //method
  public boolean isFailed() {
    return !isPassed();
  }

  //method
  public boolean isPassed() {
    return (!hasExpectationErrors() && !hasExceptionError());
  }

  //method
  private void fillUpExpectationErrors(final List<String> outputLines) {
    var i = 1;
    for (final var es : expectationErrors) {

      outputLines.addAtEnd("           " + i + ") " + es.toString());

      i++;
    }
  }

  //method
  private void fillUpProbableExceptionError(final List<String> outputLines) {
    if (hasExceptionError()) {
      outputLines.addAtEnd("   " + (getExpectationErrorCount() + 1) + ")" + exceptionError.toString());
    }
  }

  //method
  private List<String> getOutputLinesWhenFailed() {

    final var outputLines = new List<>("-->FAILED: " + getTestCaseName() + " (" + getRuntimeAndUnitAsString() + ")");
    fillUpExpectationErrors(outputLines);
    fillUpProbableExceptionError(outputLines);

    return outputLines;
  }

  //method
  private List<String> getOutputLinesWhenPassed() {
    return new List<>("   PASSED: " + getTestCaseName() + " (" + getRuntimeAndUnitAsString() + ")");
  }
}
