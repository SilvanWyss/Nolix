//package declaration
package ch.nolix.common.baseTest;

//Java import
import java.lang.reflect.Method;

//own imports
import ch.nolix.common.independentContainers.List;
import ch.nolix.common.invalidArgumentExceptions.ArgumentIsNotNullException;
import ch.nolix.common.invalidArgumentExceptions.ArgumentIsNullException;
import ch.nolix.common.invalidArgumentExceptions.NegativeArgumentException;

//class
public final class TestCaseResult {
	
	//attributes
	private final Method testCase;
	private int runtimeInMilliseconds = -1;
	
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
			throw new ArgumentIsNotNullException("test case method");
		}
		
		if (runtimeInMilliseconds < 0) {
			throw new NegativeArgumentException("runtime in milliseconds", runtimeInMilliseconds);
		}
		
		if (expectationErrors == null) {
			throw new ArgumentIsNullException("expection errors");
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
		
		this.testCase = testCase;
		this.runtimeInMilliseconds = runtimeInMilliseconds;
		this.exceptionError = exceptionError;
		this.expectationErrors = expectationErrors;
	}
	
	//method
	public int getExpectionErrorsCount() {
		return expectationErrors.getElementCount();
	}
	
	//method
	public String getName() {
		return testCase.getName();
	}
	
	//method
	public List<String> getOutputLines() {
		return (isPassed() ? getOutputLinesWhenPassed() : getOutputLinesWhenFailed());
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
	private void fillUpExpectationErrors(final List<String> consoleLines) {
		var i = 1;
		for (final var es : expectationErrors) {
			
			consoleLines.addAtEnd("      " + i + ") " + es.toString());
			
			i++;
		}
	}
	
	//method
	private void fillUpProbableExceptionError(final List<String> consoleLines) {
		if (hasExceptionError()) {
			consoleLines.addAtEnd("   " + getExpectionErrorsCount() + " )" + exceptionError.toString());
		}
	}
	
	//method
	private List<String> getOutputLinesWhenFailed() {
		
		final var consoleLines =  new List<>("-->FAILED" + getName() + " (" + getRuntimeAndUnitAsString() + ")");
		fillUpExpectationErrors(consoleLines);
		fillUpProbableExceptionError(consoleLines);
		
		return consoleLines;
	}
	
	//method
	private List<String> getOutputLinesWhenPassed() {
		return new List<>("   PASSED: " + getName() + " (" + getRuntimeAndUnitAsString() + ")");
	}
}
