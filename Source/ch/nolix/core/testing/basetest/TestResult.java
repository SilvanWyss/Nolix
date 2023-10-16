//package declaration
package ch.nolix.core.testing.basetest;

import ch.nolix.core.independent.container.List;

//class
public final class TestResult {

  //static method
  public static TestResult forTestCaseResults(final Iterable<TestCaseResult> testCaseResults) {
    return new TestResult(testCaseResults);
  }

  //multi-attribute
  private final List<TestCaseResult> testCaseResults;

  //constructor
  private TestResult(final Iterable<TestCaseResult> testCaseResults) {
    this.testCaseResults = List.withElements(testCaseResults);
  }

  //method
  public int getTestCaseCount() {
    return testCaseResults.getElementCount();
  }

  //method
  public int getPassedTestCaseCount() {

    var passedTestCaseCount = 0;

    for (final var tcr : testCaseResults) {
      if (tcr.isPassed()) {
        passedTestCaseCount++;
      }
    }

    return passedTestCaseCount;
  }

  //method
  public boolean isFailed() {
    return !isPassed();
  }

  //method
  public boolean isPassed() {

    for (var tcr : testCaseResults) {
      if (tcr.isFailed()) {
        return false;
      }
    }

    return true;
  }
}
