//package declaration
package ch.nolix.common.testing.basetest;

//Java import
import java.lang.reflect.Method;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNotNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.common.independent.independentcontainer.List;

//class
public final class TestCaseResult {
	
	//attributes
	private final Method testCase;
	private final int runtimeInMilliseconds;
	
	//optional attribute
	private final Error exceptionError;
	
	//multi-attribute
	private final List<Error> expectationErrors;
	
	//constructor
	public TestCaseResult(
		final Method testCase,
		final int runtimeInMilliseconds,
		final List<Error> expectationErrors
	) {
		
		if (testCase == null) {
			throw new ArgumentIsNotNullException(LowerCaseCatalogue.TEST_CASE);
		}
		
		if (runtimeInMilliseconds < 0) {
			throw new NegativeArgumentException("runtime in milliseconds", runtimeInMilliseconds);
		}
		
		if (expectationErrors == null) {
			throw new ArgumentIsNullException("expectation errors");
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
		final Error exceptionError
	) {
		
		if (testCase == null) {
			throw new ArgumentIsNotNullException("test case method");
		}
		
		if (runtimeInMilliseconds < 0) {
			throw new NegativeArgumentException("runtime in milliseconds", runtimeInMilliseconds);
		}
		
		if (expectationErrors == null) {
			throw new ArgumentIsNullException("expection errors");
		}
		
		if (exceptionError == null) {
			throw new ArgumentIsNullException("exception error");
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
			
			outputLines.addAtEnd("      " + i + ") " + es.toString());
			
			i++;
		}
	}
	
	//method
	private void fillUpProbableExceptionError(final List<String> outputLines) {
		if (hasExceptionError()) {
			outputLines.addAtEnd("   " + getExpectationErrorCount() + " )" + exceptionError.toString());
		}
	}
	
	//method
	private List<String> getOutputLinesWhenFailed() {
		
		final var outputLines =  new List<>("-->FAILED: " + getTestCaseName() + " (" + getRuntimeAndUnitAsString() + ")");
		fillUpExpectationErrors(outputLines);
		fillUpProbableExceptionError(outputLines);
		
		return outputLines;
	}
	
	//method
	private List<String> getOutputLinesWhenPassed() {
		return new List<>("   PASSED: " + getTestCaseName() + " (" + getRuntimeAndUnitAsString() + ")");
	}
}
