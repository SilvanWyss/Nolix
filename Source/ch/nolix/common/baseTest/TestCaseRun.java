//package declaration
package ch.nolix.common.baseTest;

//Java import
import java.lang.reflect.Method;

//own import
import ch.nolix.common.invalidArgumentExceptions.ArgumentIsNullException;

//class
public final class TestCaseRun {
	
	//constant
	public static final long MAX_DURATION_IN_MILLISECONDS = 5000;
	
	//attributes
	private final TestCaseWrapper testCaseWrapper;
	
	//constructor
	public TestCaseRun(final BaseTest parentTest, final Method testCase) {
		this(new TestCaseWrapper(parentTest, testCase));
	}
	
	//constructor
	public TestCaseRun(final TestCaseWrapper testCaseWrapper) {
		
		if (testCaseWrapper == null) {
			throw new ArgumentIsNullException("test case wrapper");
		}
		
		this.testCaseWrapper = testCaseWrapper;
	}
	
	//method
	public TestCaseResult getResult() {
		return (hasTimeout() ? getResultWorkingWithTimeout() : getResultIgnoringTimeout());
	}
	
	//method
	public TestCaseResult getResultIgnoringTimeout() {

		final var testCaseRunner = new TestCaseRunner(testCaseWrapper);
		
		//This loop suffers from being optimized away by the compiler or the JVM.
		while (!testCaseRunner.isFinished()) {
			
			//This statement, which is actually unnecessary, makes that the current loop is not optimized away.
			System.err.flush();
		}
		
		return testCaseRunner.getResult();
	}
	
	//method
	public TestCaseResult getResultWorkingWithTimeout() {
		
		final var testCaseRunner = new TestCaseRunner(testCaseWrapper);
		
		while (!testCaseRunner.isFinished()) {
			
			//This statement, which is actually unnecessary, makes that the current loop is not optimized away.
			System.err.flush();
			
			if (testCaseRunner.getRuntimeInMilliseconds() > MAX_DURATION_IN_MILLISECONDS) {
				
				//TODO: Evaluate lineNumber.
				final var lineNumber = 0;
				
				testCaseRunner.stop(
					new Error(
						"Reached timeout.",
						testCaseWrapper.getRefTestCase().getName(),
						lineNumber
					)
				);
			}
		}
		
		return testCaseRunner.getResult();
	}
	
	//method
	public boolean hasTimeout() {
		return (testCaseWrapper.testCaseHasTimeout());
	}
}
