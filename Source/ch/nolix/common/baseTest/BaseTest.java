//package declaration
package ch.nolix.common.baseTest;

//Java imports
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

//own imports
import ch.nolix.common.independentContainers.List;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;

//class
/**
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 300
 */
public abstract class BaseTest {
	
	//attribute
	private static final long TEST_CASE_MAX_DURATION_IN_MILLISECONDS = 5000;
	
	//optional attribute
	private Method afterTestCaseMethod;
	
	//multi-attributes
	private final List<String> lastErrors = new List<>();
	private final List<AutoCloseable> closableElements = new List<>();
	
	//constructor
	/**
	 * Creates a new {@link BaseTest}.
	 */
	public BaseTest() {
		
		Class<?> class_ = getClass();
		while (!class_.equals(BaseTest.class)) {
			
			for (final var m : class_.getDeclaredMethods()) {
				if (m.getAnnotation(AfterTestCase.class) != null) {
					m.setAccessible(true);
					afterTestCaseMethod = m;
					break;
				}
			}
			
			class_ = class_.getSuperclass();
		}
	}
	
	//method
	public BaseTest getCopy() {
		try {
			
			final var constructor = getClass().getConstructor();
			constructor.setAccessible(true);
			
			return constructor.newInstance();
		}
		catch (final NoSuchMethodException noSuchMethodException) {
			throw new InvalidArgumentException(getClass(), "does not have a default constructor");
		}
		catch (
			final
			IllegalAccessException
			| IllegalArgumentException
			| InstantiationException
			| InvocationTargetException
			| SecurityException
			exception
		) {
			throw new InvalidArgumentException(this, "could not be copied");
		}
	}
	
	//method
	public final String getName() {
		return getClass().getName();
	}
	
	//method
	public final String getSimpleName() {
		return getClass().getSimpleName();
	}
	
	//method
	/**
	 * @return true if the current {@link BaseTest} has a afterTestCaseMethod.
	 */
	public final boolean hasAfterTestCaseMethod() {
		return (afterTestCaseMethod != null);
	}
	
	//method
	/**
	 * Runs the test cases of the current {@link BaseTest} and prints out the test results to the console.
	 */
	public final void run() {
		
		System.out.println(getClass().getName());
		
		var passedTestMethodsCount = 0;
		
		final var testCases = getRefTestCases();
		final var testMethodArray = new Method[testCases.getElementCount()];
		var i = 0;
		for (final var tc : testCases) {
			testMethodArray[i] = tc;
			i++;
		}
		
		boolean swap = true;
		while (swap) {
			swap = false;
			for (i = 0; i < testMethodArray.length - 1; i++) {
				if (testMethodArray[i + 1].getName().compareTo(testMethodArray[i].getName()) < 0) {
					swap = true;
					final Method temp = testMethodArray[i];
					testMethodArray[i] = testMethodArray[i + 1];
					testMethodArray[i + 1] = temp;
				}
			}
		}
		
		long timeInMiliseconds = System.currentTimeMillis();
		for (final var m : testMethodArray) {
			
			final var testCaseRunner = new TestCaseRunner(this, m);
			
			//This loop suffers from being optimized away by the compiler or the JVM.
			while (!testCaseRunner.isFinished()) {
				
				//This statement, that is actually unnecessary, makes that the current loop is not optimized away.
				System.err.flush();
				
				if (m.getAnnotation(IgnoreTimeout.class) == null && testCaseRunner.getRuntimeInMilliseconds() > TEST_CASE_MAX_DURATION_IN_MILLISECONDS) {
					testCaseRunner.interrupt();
					break;
				}
			}	
			
			if (!testCaseRunner.hasExceptionError()) {
				if (!lastErrors.isEmpty()) {
					System.err.println("-->FAILED: " + m.getName() + ": (" + testCaseRunner.getRuntimeInMilliseconds() + "ms)");
					System.err.flush();
					var errorCount = 0;
					for (final var le: lastErrors) {
						errorCount++;
						System.err.println("      #" + errorCount + ": " + le);
						System.err.flush();
					}
					lastErrors.clear();
				}
				else {
					passedTestMethodsCount++;
					System.out.println("   PASSED: " + m.getName() + " (" + testCaseRunner.getRuntimeInMilliseconds() + "ms)");
					System.out.flush();
				}
			}
			else {
				
				final var fatalError = testCaseRunner.getExceptionError();
				
				System.err.println("-->FAILED: " + m.getName() + ": " + fatalError.getErrorMessage() + " (" + testCaseRunner.getRuntimeInMilliseconds() + "ms)");
				
				System.err.flush();
			}

			closeAndClearClosableElements();
			runProbableAfterTestCaseMethod();
		}
		
		timeInMiliseconds = System.currentTimeMillis() - timeInMiliseconds;
		System.out.println(
			"   summary: "
			+ passedTestMethodsCount
			+ "/"
			+ testCases.getElementCount()
			+ " passed test cases ("
			+ timeInMiliseconds
			+ "ms)");
		System.out.println();
		System.out.flush();
	}
	
	//TODO: Implement registerToClose(Closable).
	//method
	/**
	 * Lets the current {@link BaseTest} register the given element to close.
	 * 
	 * @param element
	 */
	final protected void registerToClose(final AutoCloseable element) {
		if (element != null) {
			closableElements.addAtEnd(element);
		}
	}
	
	//method
	/**
	 * Adds the given current test case error to the current {@link BaseTest}.
	 * 
	 * @param currentTestMethodError
	 */
	final void addCurrentTestCaseError(final String currentTestMethodError) {
		
		String className = null;
		int lineNumber = -1;
		for (final var ste : Thread.currentThread().getStackTrace()) {
			if (ste.getClassName().equals(getClass().getName())) {
				className = ste.getClassName();
				lineNumber = ste.getLineNumber();
			}
		}
		
		if (className == null) {
			for (final var ste : Thread.currentThread().getStackTrace()) {
				Class<?> class_ = getClass();
				do {
					
					if (ste.getClassName().equals(class_.getName())) {
						className = ste.getClassName();
						lineNumber = ste.getLineNumber();
						break;
					}
					
					class_ = class_.getSuperclass();
				}
				while (class_ != null);
			}
		}
		
		if (className == null) {
			throw new RuntimeException("Class was not found.");
		}
		
		lastErrors.addAtEnd(currentTestMethodError + " (" + className + ".java:" + lineNumber + ")");
	}
	
	//method
	/**
	 * Removes the expectation errors of the current {@link BaseTest}.
	 * 
	 * @return the expectation errors of the current {@link BaseTest}.
	 */
	final List<Error> getAndRemoveExpectationErrors() {
		// TODO: Implement.
		return new List<>();
	}
	
	//method
	/**
	 * @return the test cases of the current {@link BaseTest}.
	 */
	final List<Method> getRefTestCases() {
		
		final var testCases = new List<Method>();
				
		Class<?> lClass = getClass();
		while (!lClass.equals(BaseTest.class)) {
			
			for (final var m : lClass.getDeclaredMethods()) {
				if (!Modifier.isStatic(m.getModifiers()) && Modifier.isPublic(m.getModifiers())) {
					testCases.addAtEnd(m);
				}
			}
			
			lClass = lClass.getSuperclass();
		}
		
		return testCases;
	}
	
	//method
	private void closeAndClearClosableElements() {
		
		for (final var ac : closableElements) {
			try {
				ac.close();
			} catch (final Exception exception) {
				System.err.println("   An error occured by trying to close an element.");
				System.out.flush();
			}
		}
		
		closableElements.clear();
	}
	
	//method
	private void runProbableAfterTestCaseMethod() {
		if (hasAfterTestCaseMethod()) {
			try {
				afterTestCaseMethod.invoke(this);
			}
			catch (final IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
				System.err.println("   An error occured by trying to run an afterTestCase method.");
				System.out.flush();
			}
		}
	}
}
