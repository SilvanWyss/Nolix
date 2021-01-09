//package declaration
package ch.nolix.common.basetest;

//Java imports
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//own imports
import ch.nolix.common.independentcontainer.List;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.skillapi.Closeable;

//class
/**
 * @author Silvan Wyss
 * @date 2016-09-01
 * @lines 220
 */
public abstract class BaseTest {
	
	//attribute
	private boolean isTestInstance;
	
	//multi-attributes
	private final List<Error> expectationErrors = new List<>();
	private final List<AutoCloseable> closableElements = new List<>();
	
	//method
	/**
	 * @return the name of the current {@link BaseTest}.
	 */
	public final String getName() {
		return getClass().getName();
	}
	
	//method
	/**
	 * @return the simple name of the current {@link BaseTest}.
	 */
	public final String getSimpleName() {
		return getClass().getSimpleName();
	}
		
	//method
	/**
	 * Runs the test cases of the current {@link BaseTest} and prints out the result to the console.
	 */
	public final void run() {	
		run(new StandardConsoleLinePrinter());
	}
	
	//method
	/**
	 * Runs the test cases of the current {@link BaseTest} and prints out the result using the given linePrinter.
	 * 
	 * @param linePrinter
	 * @throws ArgumentIsNullException if the given linePrinter is null.
	 */
	public void run(final ILinePrinter linePrinter) {
		new TestRun(this, linePrinter).run();
	}
	
	//method
	/**
	 * Lets the current {@link BaseTest} register the given element to close.
	 * 
	 * @param element
	 */
	protected final void registerToClose(final AutoCloseable element) {
		if (element != null) {
			closableElements.addAtEnd(element);
		}
	}
	
	//method
	/**
	 * Lets the current {@link BaseTest} register the given element to close.
	 * 
	 * @param element
	 */
	protected final void registerToClose(final Closeable element) {
		if (element != null) {
			registerToClose(element.asAutoClosable());
		}
	}
	
	//method
	/**
	 * Adds the given expectationError to the current {@link BaseTest}.
	 * 
	 * @param expectationError
	 */
	protected final void addExpectationError(final String expectationError) {
		
		String className = null;
		var lineNumber = -1;
		Class<?> lClass = getClass();
		while (lClass != null) {
			
			for (final var ste : Thread.currentThread().getStackTrace()) {
				if (ste.getClassName().equals(lClass.getName())) {
					className = ste.getClassName();
					lineNumber = ste.getLineNumber();
					break;
				}
			}
			
			if (className != null) {
				break;
			}
			
			lClass = lClass.getSuperclass();
		}
		
		expectationErrors.addAtEnd(new Error(expectationError, className, lineNumber));
	}
	
	//method
	/**
	 * @return the expectation errors of the current {@link BaseTest}.
	 */
	final List<Error> getExpectationErrors() {
		return expectationErrors;
	}
	
	//method
	/**
	 * @return the {@link AutoCloseable}s of the current {@link BaseTest}.
	 */
	final List<AutoCloseable> getRefClosableElements() {
		return closableElements;
	}
	
	//method
	/**
	 * @return the test cases of the current {@link BaseTest} ordered alphabetically.
	 */
	List<Method> getRefTestCasesOrderedAlphabetically() {
		
		final var testCasesOrderedAlphabetically = new List<Method>();
		final var testCases = getRefTestCases();
		while (!testCases.isEmpty()) {
			
			var testCase = testCases.getRefFirst();
			for (final var tc : testCases) {
				if (tc.getName().compareTo(testCase.getName()) < 0) {
					testCase = tc;
				}
			}
			
			testCases.removeFirst(testCase);
			testCasesOrderedAlphabetically.addAtEnd(testCase);
		}
		
		return testCasesOrderedAlphabetically;
	}
	
	//method
	/**
	 * @return true if the current {@link BaseTest} is a test instance.
	 */
	final boolean isTestInstance() {
		return isTestInstance;
	}
	
	//method
	/**
	 * @return a new test instance from the current {@link BaseTest}.
	 */
	final BaseTest toTestInstance() {
		
		final var testInstance = getCopy();
		testInstance.isTestInstance = true;
		
		return testInstance;
	}
	
	//method
	/**
	 * @return a new copy of the current {@link BaseTest}.
	 * @throws InvalidArgumentException if the current {@link BaseTest} could not be copied.
	 */
	private BaseTest getCopy() {
		try {
			return ReflectionHelper.getDefaultConstructor(getClass()).newInstance();
		} catch (
			final
			IllegalAccessException
			| InstantiationException
			| InvocationTargetException
			exception
		) {
			throw new InvalidArgumentException(this, "could not be copied");
		}
	}
	
	/**
	 * @return the test cases of the current {@link BaseTest}.
	 */
	private List<Method> getRefTestCases() {
		
		final var testCases = new List<Method>();
		Class<?> lClass = getClass();
		while (lClass != null) {
			
			for (final var m : lClass.getDeclaredMethods()) {
				if (isTestCase(m)) {
					testCases.addAtEnd(m);
				}
			}
			
			lClass = lClass.getSuperclass();
		}
		
		return testCases;
	}
	
	//method
	/**
	 * @param method
	 * @return true if the given method is a test case.
	 */
	private boolean isTestCase(final Method method) {
		return (method != null && ReflectionHelper.methodHasAnnotation(method, TestCase.class));
	}
}
