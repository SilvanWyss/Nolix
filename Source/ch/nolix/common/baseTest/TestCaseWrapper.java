//package declaration
package ch.nolix.common.baseTest;

//Java import
import java.lang.reflect.Method;

import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.invalidArgumentException.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidArgumentException.ArgumentIsNullException;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;

//class
public final class TestCaseWrapper {
	
	//attributes
	private final BaseTest parentTest;
	private final Method testCase;
	
	//optional attributes
	private final Method setup;
	private final Method cleanup;
	
	//constructor
	public TestCaseWrapper(final BaseTest parentTest, final Method testCase) {
		
		if (parentTest == null) {
			throw new ArgumentIsNullException("parent test");
		}
		
		if (testCase == null) {
			throw new ArgumentIsNullException(VariableNameCatalogue.TEST_CASE);
		}
		
		this.parentTest = parentTest;
		this.testCase = testCase;
		setup = extractSetup();
		cleanup = extractCleanup();
	}
	
	//method
	public BaseTest createTestInstance() {
		return parentTest.toTestInstance();
	}
	
	//method
	public BaseTest getParentTest() {
		return parentTest;
	}
	
	//method
	public Method getRefCleanup() {
		
		supposeHasCleanup();
		
		return cleanup;
	}
	
	//method
	public Method getRefSetup() {
		
		supposeHasSetup();
		
		return setup;
	}
	
	//method
	public Method getRefTestCase() {
		return testCase;
	}
	
	//method
	public boolean hasCleanup() {
		return (cleanup != null);
	}

	//method
	public boolean hasSetup() {
		return (setup != null);
	}
	
	//method
	public boolean testCaseHasTimeout() {
		return !ReflectionHelper.methodHasAnnotation(getRefTestCase(), IgnoreTimeout.class);
	}
	
	//method
	private Method extractCleanup() {
		
		Method lCleanup = null;
		for (final var m : parentTest.getClass().getMethods()) {
			
			if (ReflectionHelper.methodHasAnnotation(m, Cleanup.class)) {
				
				if (lCleanup != null) {
					throw new InvalidArgumentException(parentTest.getClass(), "contains more than 1 cleanup");
				}
				
				lCleanup = m;
			}
		}
		
		return lCleanup;
	}
	
	//method
	private Method extractSetup() {
		
		Method lSetup = null;
		for (final var m : parentTest.getClass().getMethods()) {
			
			if (ReflectionHelper.methodHasAnnotation(m, Setup.class)) {
				
				if (lSetup != null) {
					throw new InvalidArgumentException(parentTest.getClass(), "contains more than 1 setup");
				}
				
				lSetup = m;
			}
		}
		
		return lSetup;
	}
	
	//method
	private void supposeHasCleanup() {
		if (!hasCleanup()) {
			throw new ArgumentDoesNotHaveAttributeException(this, VariableNameCatalogue.CLEANUP);
		}
	}
	
	//method
	private void supposeHasSetup() {
		if (!hasSetup()) {
			throw new ArgumentDoesNotHaveAttributeException(this, VariableNameCatalogue.SETUP);
		}
	}
}
