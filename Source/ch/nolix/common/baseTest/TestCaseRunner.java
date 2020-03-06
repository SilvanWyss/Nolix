//package declaration
package ch.nolix.common.baseTest;

//Java imports
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//own imports
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.invalidArgumentExceptions.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidArgumentExceptions.ArgumentIsNullException;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;

//class
final class TestCaseRunner extends Thread {
	
	//attributes
	private final BaseTest parentTest;
	private final Method testCase;
	private boolean finished = false;
	private long startTime;
	private int runtimeInMilliseconds = 0;
	
	//optional attribute
	private Error exceptionError;
	
	//constructor
	public TestCaseRunner(final BaseTest parentTest, final Method testCase) {
		
		if (parentTest == null) {
			throw new ArgumentIsNullException("parent test");
		}
		
		if (testCase == null) {
			throw new ArgumentIsNullException(VariableNameCatalogue.TEST_CASE);
		}
		
		this.parentTest = parentTest;
		this.testCase = testCase;
		startTime = System.currentTimeMillis();
		
		start();
	}
	
	//method
	public Error getExceptionError() {
		
		if (!hasExceptionError()) {
			throw new ArgumentDoesNotHaveAttributeException(this, "exception error");
		}
		
		return exceptionError;
	}
	
	//method
	public TestCaseResult getResult() {
		
		supposeIsFinished();
		
		if (!hasExceptionError()) {
			return
			new TestCaseResult(
				testCase,
				getRuntimeInMilliseconds(),
				parentTest.getAndRemoveExpectationErrors()
			);
		}
		
		return
		new TestCaseResult(
			testCase,
			getRuntimeInMilliseconds(),
			parentTest.getAndRemoveExpectationErrors(),
			getExceptionError()
		);
	}
	
	//method
	public int getRuntimeInMilliseconds() {
		
		if (!isFinished()) {
			runtimeInMilliseconds = (int)(System.currentTimeMillis() - startTime);
		}
		
		return runtimeInMilliseconds;
	}
	
	//method
	public boolean hasExceptionError() {
		return (exceptionError != null);
	}
	
	//method
	public boolean isFinished() {
		return finished;
	}
	
	//method
	@Override
	public void run() {
		try {
			testCase.invoke(parentTest, (Object[])new Class[0]);
		}
		catch (final InvocationTargetException invocationTargetException) {
			
			String className = null;
			int lineNumber = -1;
			for (final var ste : Thread.currentThread().getStackTrace()) {
				if (ste.getClassName().equals(parentTest.getClass().getName())) {
					className = ste.getClassName();
					lineNumber = ste.getLineNumber();
					break;
				}
			}
			
			final var cause = invocationTargetException.getCause();
			final var errorMessage = cause.getMessage() == null ? "An error occured." : cause.getMessage();
			
			exceptionError = new Error(errorMessage, className, lineNumber);
			
		} catch (IllegalAccessException | IllegalArgumentException exception) {
			exceptionError = new Error("An error occured.", parentTest.getClass().getName(), 0);
		}
		finally {
			finished = true;
		}
	}
	
	//method
	public void stop(final Error stopReason) {
		
		if (stopReason == null) {
			throw new ArgumentIsNullException("stop reason");
		}
		
		interrupt();
		finished = true;
		exceptionError = stopReason;
	}
	
	//method
	private void supposeIsFinished() {
		if (!isFinished()) {
			throw new InvalidArgumentException(this, "is not finished");
		}
	}
}
