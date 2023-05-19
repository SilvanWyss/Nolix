//package declaration
package ch.nolix.core.testing.basetest;

//Java imports
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//own imports
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;

//class
public final class TestCaseRunner extends Thread {
	
	//static attribute
	private static final ErrorCreator errorCreator = new ErrorCreator();
	
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
			throw ArgumentIsNullException.forArgumentName("test case wrapper");
		}
		
		this.testCaseWrapper = testCaseWrapper;
		testInstance = testCaseWrapper.createTestInstance();
		
		start();
	}
	
	//method
	public Error getExceptionError() {
		
		if (!hasExceptionError()) {
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "exception error");
		}
		
		return exceptionError;
	}
	
	//method
	public TestCaseResult getResult() {
		
		assertIsFinished();
		
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
		runOptionalSetup();
		
		//main phase
		runTestCase();
		
		//cleanup phase
		runOptionalCleanup();
		closeCloseableElements();
		
		//result phase
		result = createResult();
	}
	
	//method
	public void stop(final Error stopReason) {
		
		if (stopReason == null) {
			throw ArgumentIsNullException.forArgumentName("stop reason");
		}
		
		assertIsNotFinished();
				
		exceptionError = stopReason;
		interrupt();
		result = createResult();
	}
	
	//method
	private void assertHasNotStarted() {
		if (hasStarted()) {
			throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "has started already");
		}
	}
	
	//method
	private void assertIsFinished() {
		if (!isFinished()) {
			throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is not finished");
		}
	}
	
	//method
	private void assertIsNotFinished() {
		if (isFinished()) {
			throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is finished already");
		}
	}
	
	//method
	private void closeCloseableElements() {
		for (final var ce : testInstance.getOriClosableElements()) {
			closeCloseableElement(ce);
		}
	}
	
	//method
	private void closeCloseableElement(final AutoCloseable closeableElement) {
		try {
			closeableElement.close();
		} catch (final Exception exception) {
			if (!hasExceptionError()) {
				exceptionError = errorCreator.createErrorFromThrowableInInstance(exception, closeableElement);
			}
		}
	}

	//method
	private TestCaseResult createResult() {
				
		if (!hasExceptionError()) {
			return
			new TestCaseResult(
				testCaseWrapper.getOriTestCase(),
				getRuntimeInMilliseconds(),
				testInstance.getExpectationErrors()
			);
		}
		
		return
		new TestCaseResult(
			testCaseWrapper.getOriTestCase(),
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
	private void runCleanup() {
		try {
			testCaseWrapper.getOriCleanup().invoke(testInstance);
		} catch (final InvocationTargetException invocationTargetException) {
			if (!hasExceptionError()) {
				exceptionError =
				errorCreator.createErrorFromInvocationTargetExceptionInInstance(invocationTargetException, testInstance);
			}
		} catch (final IllegalAccessException illegalAccessException) {
			throw WrapperException.forError(illegalAccessException);
		}
	}
	
	//method
	private void runOptionalCleanup() {
		if (testCaseWrapper.hasCleanup()) {
			runCleanup();
		}
	}
	
	//method
	private void runOptionalSetup()  {
		if (testCaseWrapper.hasSetup()) {
			runSetup();
		}
	}
	
	//method
	private void runSetup() {
		try {
			testCaseWrapper.getOriSetup().invoke(testInstance);
		} catch (final InvocationTargetException invocationTargetException) {
			if (!hasExceptionError()) {
				exceptionError =
				errorCreator.createErrorFromInvocationTargetExceptionInInstance(invocationTargetException, testInstance);
			}
		} catch (final IllegalAccessException illegalAccessException) {
			throw WrapperException.forError(illegalAccessException);
		}
	}
	
	//method
	private void runTestCase() {
		try {
			testCaseWrapper.getOriTestCase().invoke(testInstance, (Object[])new Class[0]);
		} catch (final InvocationTargetException invocationTargetException) {
			if (!hasExceptionError()) {
				exceptionError =
				errorCreator.createErrorFromInvocationTargetExceptionInInstance(invocationTargetException, testInstance);
			}
		} catch (final IllegalAccessException illegalAccessException) {
			throw WrapperException.forError(illegalAccessException);
		}
	}
	
	//method
	private void setStarted() {
		assertHasNotStarted();
		startTime = System.currentTimeMillis();
	}
}
