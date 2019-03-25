//package declaration
package ch.nolix.core.testoid;

//Java imports
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

//own imports
import ch.nolix.core.primitiveContainer.List;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 240
 */
public abstract class Testoid {
	
	//optional attribute
	private Method afterTestCaseMethod;
	
	//multi-attributes
	private final List<String> lastErrors = new List<String>();
	private final List<AutoCloseable> closableElements = new List<AutoCloseable>();
	
	//constructor
	/**
	 * Creates a new {@link Testoid}.
	 */
	public Testoid() {
		
		Class<?> class_ = getClass();
		while (!class_.equals(Testoid.class)) {
			
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
	/**
	 * @return true if the current {@link Testoid} has a afterTestCaseMethod.
	 */
	public final boolean hasAfterTestCaseMethod() {
		return (afterTestCaseMethod != null);
	}
	
	//method
	/**
	 * Runs the test cases of the current {@link Testoid} and prints out the test results to the console.
	 */
	public final void run() {
		
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
			long methodTimeInMiliseconds = System.currentTimeMillis();
			try {
				
				m.invoke(this, (Object[])new Class[0]);
				methodTimeInMiliseconds = System.currentTimeMillis() - methodTimeInMiliseconds;
				
				if (!lastErrors.isEmpty()) {
					System.err.println("-->FAILED: " + m.getName() + ": (" + methodTimeInMiliseconds + "ms)");
					System.err.flush();
					var errorCount = 0;
					for (final var le: lastErrors) {
						errorCount++;
						System.err.println("   #" + errorCount + ": " + le);
						System.err.flush();
					}
					lastErrors.clear();
				}
				else {
					passedTestMethodsCount++;
					System.out.println("   PASSED: " + m.getName() + " (" + methodTimeInMiliseconds + "ms)");
					System.out.flush();
				}
			}
			catch (final Throwable error) {
				methodTimeInMiliseconds = System.currentTimeMillis() - methodTimeInMiliseconds;
				if (error.getCause() != null) {
					System.err.println("-->FAILED: " + m.getName() + ": " + error.getCause().getMessage() + " (" + methodTimeInMiliseconds + "ms)");
				}
				else {
					System.err.println("-->FAILED: " + m.getName() + " (" + methodTimeInMiliseconds + "ms)");
				}
				System.err.flush();
			}
			finally {
				closeAndClearClosableElements();
				runProbableAfterTestCaseMethod();
			}
		}
		
		timeInMiliseconds = System.currentTimeMillis() - timeInMiliseconds;
		System.out.println(
			" = "
			+ getClass().getSimpleName()
			+ ": "
			+ passedTestMethodsCount
			+ "/"
			+ testCases.getElementCount()
			+ " passed test cases ("
			+ timeInMiliseconds
			+ "ms)");
		System.out.println();
		System.out.flush();
	}
	
	//method
	/**
	 * Lets the current {@link Testoid} register the given element to close.
	 * 
	 * @param element
	 */
	protected void registerToClose(final AutoCloseable element) {
		if (element != null) {
			closableElements.addAtEnd(element);
		}
	}
	
	//package-visible method
	/**
	 * Adds the given current test case error to the current {@link Testoid}.
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
	/**
	 * @return the test cases of the current {@link Testoid}.
	 */
	private List<Method> getRefTestCases() {
		
		final var testCases = new List<Method>();
				
		Class<?> class_ = getClass();
		while (!class_.equals(Testoid.class)) {
			
			for (final var m : class_.getDeclaredMethods()) {
				if (!Modifier.isStatic(m.getModifiers()) && Modifier.isPublic(m.getModifiers())) {
					testCases.addAtEnd(m);
				}
			}
			
			class_ = class_.getSuperclass();
		}
		
		return testCases;
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
