//package declaration
package ch.nolix.core.testing.basetest;

//own imports
import ch.nolix.core.independent.container.List;

//class
public final class TestPoolResult {

  //multi-attribute
  private final List<TestPoolResult> testPoolResults;

  //multi-attribute
  private final List<TestResult> testResults;

  //constructor
  private TestPoolResult(final Iterable<TestPoolResult> testPoolResults, final Iterable<TestResult> testResults) {

    this.testPoolResults = List.withElements(testPoolResults);

    this.testResults = List.withElements(testResults);
  }

  //static method
  public static TestPoolResult forTestPoolResultsAndTestResults(
    final Iterable<TestPoolResult> testPoolResults,
    final Iterable<TestResult> testResults) {
    return new TestPoolResult(testPoolResults, testResults);
  }

  //method
  public int getPassedTestCaseCount() {
    return (getPassedTestCaseCountOfTestPoolResults() + getPassedTestCaseCountOfTestResults());
  }

  //method
  public int getTestCaseCount() {
    return (getTestCaseCountOfTestPoolResults() + getTestCaseCountOfTestResults());
  }

  //method
  private int getPassedTestCaseCountOfTestPoolResults() {

    var passedTestCaseCount = 0;

    for (final var tpr : testPoolResults) {
      passedTestCaseCount += tpr.getPassedTestCaseCount();
    }

    return passedTestCaseCount;
  }

  //method
  private int getPassedTestCaseCountOfTestResults() {

    var passedTestCaseCount = 0;

    for (final var tr : testResults) {
      passedTestCaseCount += tr.getPassedTestCaseCount();
    }

    return passedTestCaseCount;
  }

  //method
  private int getTestCaseCountOfTestPoolResults() {

    var testCaseCount = 0;

    for (final var tpr : testPoolResults) {
      testCaseCount += tpr.getTestCaseCount();
    }

    return testCaseCount;
  }

  //method
  private int getTestCaseCountOfTestResults() {

    var testCaseCount = 0;

    for (final var tr : testResults) {
      testCaseCount += tr.getTestCaseCount();
    }

    return testCaseCount;
  }
}
