//package declaration
package ch.nolix.common.basetest;

//Java imports
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//own imports
import ch.nolix.common.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.processproperty.Result;

//class
public final class TestCaseRunner extends Thread {
	
	//attributes
	private final TestCaseWrapper testCaseWrapper;
	private final BaseTest testInstance;
	private TestCaseResult result;
	private long startTime;
	private int runtimeInMilliseconds;
	
	//optional attribute
	private Error exceptionError;
	
	//constructor
	public TestCaseRunner(final BaseTest parentTest, final Method testCase) {
		this(new TestCaseWrapper(parentTest, testCase));
	}
	
	//constructor
	public TestCaseRunner(final TestCaseWrapper testCaseWrapper) {
		
		if (testCaseWrapper == null) {
			throw new ArgumentIsNullException("test case wrapper");
		}
		
		this.testCaseWrapper = testCaseWrapper;
		testInstance = testCaseWrapper.createTestInstance();
		
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
		
		return result;
	}
	
	//method
	public int getRuntimeInMilliseconds() {
			
		if (!isFinished()) {
			
			if (!hasStarted()) {
				return 0;
			}
			
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
		return (result != null);
	}
	
	//method
	@Override
	public void run() {
		
		//setup phase
		setStarted();
		final var setupResult = runProbableSetupAndGetResult();
		
		//main phase
		if (setupResult == Result.SUCCESS) {
			runTestCase();
		}
		
		//result phase
		runProbableCleanup();
		closeClosableElements();
		result = createResult();
	}
	
	//method
	public void stop(final Error stopReason) {
		
		if (stopReason == null) {
			throw new ArgumentIsNullException("stop reason");
		}
		
		supposeIsNotFinished();
				
		exceptionError = stopReason;
		interrupt();
		result = createResult();
	}
	
	//method
	private void closeClosableElements() {
		for (final var ce : testInstance.getRefClosableElements()) {
			try {
				ce.close();
			} catch (final Exception exception) {
				
				//TODO: Evaluate lineNumber.
				final var lineNumber = 1;
				
				if (!hasExceptionError()) {
					exceptionError =
					new Error("An error occured at the try to close an element.", testInstance.getName(), lineNumber);
				}
			}
		}
	}
	
	//method
	private TestCaseResult createResult() {
				
		if (!hasExceptionError()) {
			return
			new TestCaseResult(
				testCaseWrapper.getRefTestCase(),
				getRuntimeInMilliseconds(),
				testInstance.getExpectationErrors()
			);
		}
		
		return
		new TestCaseResult(
			testCaseWrapper.getRefTestCase(),
			getRuntimeInMilliseconds(),
			testInstance.getExpectationErrors(),
			getExceptionError()
		);
	}
	
	//method
	private boolean hasStarted() {
		return (startTime != 0);
	}
	
	//method
	private boolean runCleanup() {		
		try {
			testCaseWrapper.getRefCleanup().invoke(testInstance);
			return true;
		} catch (final
			IllegalAccessException
			| IllegalArgumentException
			| InvocationTargetException
			exception
		) {
			
			//TODO: Evaluate lineNumber.
			final var lineNumber = 1;
			
			exceptionError = new Error("Cleanup failed", testCaseWrapper.getRefCleanup().getName(), lineNumber);
			
			return false;
		}
	}
	
	//method
	private boolean runProbableCleanup() {
		
		if (testCaseWrapper.hasCleanup()) {
			return runCleanup();
		}
		
		return true;
	}
	
	//method
	private Result runProbableSetupAndGetResult()  {
		
		if (testCaseWrapper.hasSetup()) {
			return runSetupAndGetResult();
		}
		
		return Result.SUCCESS;
	}
	
	//method
	private Result runSetupAndGetResult() {
		try {
			testCaseWrapper.getRefSetup().invoke(testInstance);
			return Result.SUCCESS;
		} catch (
			final
			IllegalAccessException
			| IllegalArgumentException
			| InvocationTargetException
			exception
		) {
			
			//TODO: Evaluate lineNumber.
			final var lineNumber = 1;
			
			exceptionError = new Error("Setup failed", testCaseWrapper.getRefSetup().getName(), lineNumber);
			
			return Result.FAILURE;
		}
	}
	
	//method
	private boolean runTestCase() {
		try {
			testCaseWrapper.getRefTestCase().invoke(testInstance, (Object[])new Class[0]);
			return true;
		} catch (final InvocationTargetException invocationTargetException) {
			
			String className = null;
			int lineNumber = 0;
			for (final var ste : invocationTargetException.getCause().getStackTrace()) {
				if (ste.getClassName().equals(testInstance.getClass().getName())) {
					className = ste.getClassName();
					lineNumber = ste.getLineNumber();
					break;
				}
			}
			
			final var cause = invocationTargetException.getCause();
			final var errorMessage = cause.getMessage() == null ? "An error occured." : cause.getMessage();
			
			exceptionError = new Error(errorMessage, className, lineNumber);
			
			return false;
		} catch (final IllegalAccessException | IllegalArgumentException exception) {
			exceptionError = new Error("An error occured.", testInstance.getClass().getName(), 0);
			return false;
		}
	}
	
	//method
	private void setStarted() {
		supposeHasNotStarted();
		startTime = System.currentTimeMillis();
	}
	
	//method
	private void supposeHasNotStarted() {
		if (hasStarted()) {
			throw new InvalidArgumentException(this, "has started already");
		}
	}
	
	//method
	private void supposeIsFinished() {
		if (!isFinished()) {
			throw new InvalidArgumentException(this, "is not finished");
		}
	}
	
	//method
	private void supposeIsNotFinished() {
		if (isFinished()) {
			throw new InvalidArgumentException(this, "is finished already");
		}
	}
}
