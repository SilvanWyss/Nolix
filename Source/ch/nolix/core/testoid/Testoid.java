//package declaration
package ch.nolix.core.testoid;

//Java imports
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import ch.nolix.core.primitiveContainer.List;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 170
 */
public abstract class Testoid {
	
	//multi-attributes
	private final List<String> lastErrors = new List<String>();
	private final List<AutoCloseable> closableElements = new List<AutoCloseable>();

	//method
	/**
	 * Runs this test and prints out the test results to the console.
	 */
	public final void run() {
		
		int passedTestMethodsCount = 0;
		
		final var testMethods = getTestMethods();
		final var testMethodArray = new Method[testMethods.getElementCount()];
		var i = 0;
		for (final var tm : testMethods) {
			testMethodArray[i] = tm;
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
		for (final Method m : testMethodArray) {				
			long methodTimeInMiliseconds = System.currentTimeMillis();
			try {		
				m.invoke(this, (Object[])new Class[0]);
				methodTimeInMiliseconds = System.currentTimeMillis() - methodTimeInMiliseconds;
				
				if (!lastErrors.isEmpty()) {
					System.err.println("-->FAILED: " + m.getName() + ": (" + methodTimeInMiliseconds + "ms)");
					System.err.flush();
					int errorCount = 0;
					for (String le: lastErrors) {
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
			catch (Exception e) {
				methodTimeInMiliseconds = System.currentTimeMillis() - methodTimeInMiliseconds;
				if (e.getCause() != null) {
					System.err.println("-->FAILED: " + m.getName() + ": " + e.getCause().getMessage() + " (" + methodTimeInMiliseconds + "ms)");
				}
				else {
					System.err.println("-->FAILED: " + m.getName() + " (" + methodTimeInMiliseconds + "ms)");
				}
				System.err.flush();
			}
			finally {
				for (final AutoCloseable ce : closableElements) {
					try {
						ce.close();
					} catch (final Exception exception) {
						System.err.println("   An error occured by the try to close an element.");
					}
				}
				
				closableElements.clear();
			}
		}
		
		timeInMiliseconds = System.currentTimeMillis() - timeInMiliseconds;
		System.out.println(
			" = "
			+ getClass().getSimpleName()
			+ ": "
			+ passedTestMethodsCount
			+ "/"
			+ testMethods.getElementCount()
			+ " passed test cases ("
			+ timeInMiliseconds
			+ "ms)");
		System.out.println();
		System.out.flush();
	}
	
	//method
	/**
	 * Lets this test register the given element to close.
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
	 * Adds the given current test case error to this test.
	 * 
	 * @param currentTestMethodError
	 */
	final void addCurrentTestCaseError(final String currentTestMethodError) {
		
		String className = null;
		int lineNumber = -1;
		for (final StackTraceElement ste : Thread.currentThread().getStackTrace()) {
			if (ste.getClassName().equals(getClass().getName())) {
				className = ste.getClassName();
				lineNumber = ste.getLineNumber();
			}
		}
		
		if (className == null) {
			for (final StackTraceElement ste : Thread.currentThread().getStackTrace()) {
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
	 * @return the test methods of this test.
	 */
	private List<Method> getTestMethods() {
		
		final var testMethods = new List<Method>();
				
		Class<?> class_ = getClass();
		while (!class_.equals(Testoid.class)) {
			
			for (final Method m : class_.getDeclaredMethods()) {
				if (!Modifier.isStatic(m.getModifiers()) && Modifier.isPublic(m.getModifiers())) {
					testMethods.addAtEnd(m);
				}
			}
			
			class_ = class_.getSuperclass();
		}
		
		return testMethods;
	}
}
