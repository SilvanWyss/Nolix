/*
 * file:	Test.java
 * author:	Silvan Wyss
 * month:	2016-08
 * lines:	100
 */

//package declaration
package ch.nolix.core.testBase;

//Java imports
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Vector;

//class
public abstract class Test {
	
	//attribute
	private final Vector<String> lastErrors = new Vector<String>();

	//method
	/**
	 * Executes this test and prints out the test result to the console.
	 */
	public final void run() {
		
		int testMethodsCount = 0;
		int passedTestMethodsCount = 0;
		Method[] testMethods = this.getClass().getDeclaredMethods();
		
		boolean swap = true;
		while (swap) {
			swap = false;
			for (int i = 0; i < testMethods.length - 1; i++) {
				if (testMethods[i].getName().compareTo(testMethods[i + 1].getName()) > 0) {
					swap = true;
					final Method temp = testMethods[i];
					testMethods[i] = testMethods[i + 1];
					testMethods[i + 1] = temp;
				}
			}
		}
		
		long timeInMiliseconds = System.currentTimeMillis();
		for (Method m: testMethods) {				
			if (!Modifier.isStatic(m.getModifiers())) {
				testMethodsCount++;
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
			}
		}
		
		timeInMiliseconds = System.currentTimeMillis() - timeInMiliseconds;
		System.out.println("  =" + getClass().getSimpleName() + ": " + passedTestMethodsCount + "/" + testMethodsCount + " passed test methods (" + timeInMiliseconds + "ms)");
		System.out.println();
		System.out.flush();
	}
	
	//package-visible method
	/**
	 * Adds the given current test method error to this test.
	 * 
	 * @param currentTestMethodError
	 */
	final void addCurrentTestMethodError(final String currentTestMethodError) {
		final String className = Thread.currentThread().getStackTrace()[4].getClassName();
		final int line = Thread.currentThread().getStackTrace()[4].getLineNumber();
		lastErrors.addElement(currentTestMethodError + " (" + className + ".java:" + line + ")");
	}
}
