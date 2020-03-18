//package declaration
package ch.nolix.common.baseTest;

//Java imports
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

//own import
import ch.nolix.common.invalidArgumentExceptions.ArgumentIsNullException;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;

//class
public final class TestPoolRun {
	
	//attributes
	private final TestPool parentTestPool;
	private final ILinePrinter linePrinter;
	private boolean started = false;
	
	//constructor
	public TestPoolRun(final TestPool parentTestPool, final ILinePrinter linePrinter) {
		
		if (parentTestPool == null) {
			throw new ArgumentIsNullException("parent TestPool");
		}
		
		if (linePrinter == null) {
			throw new ArgumentIsNullException(ILinePrinter.class);
		}
		
		this.parentTestPool = parentTestPool;
		this.linePrinter = linePrinter;
	}
	
	//method
	public void run() {
		
		supposeDidNotStart();
		started = true;
		
		linePrinter.printInfoLine("   Started " + parentTestPool.getName());
		linePrinter.printEmptyLine();
		
		for (final var tp : parentTestPool.getRefTestPools()) {
			tp.run(linePrinter);
		}
		
		for (final var tc : parentTestPool.getRefTestClasses()) {
			runTest(tc);
		}
		
		printSummary();
	}
	
	//method
	public boolean started() {
		return started;
	}
	
	//method
	private <BT extends BaseTest> BT createTestOrNull(final Class<BT> testClass) {
		
		final var constructor = getDefaultConstructorOrNull(testClass);
		
		if (constructor == null) {
			return null;
		}
		
		return createTestOrNull(constructor);
	}
	
	//method
	private <BT extends BaseTest> BT createTestOrNull(final Constructor<BT> constructor) {
		try {
			return constructor.newInstance();
		}
		catch (
			final
			InstantiationException
			| IllegalAccessException
			| IllegalArgumentException
			| InvocationTargetException
			exception
		) {
			linePrinter.printErrorLine("An error occured.");
			return null;
		}
	}

	//method
	private <BT extends BaseTest> Constructor<BT> getDefaultConstructorOrNull(final Class<BT> testClass) {
		try {
			
			final var constructor = testClass.getConstructor();
			constructor.setAccessible(true);
			
			return constructor;
		}
		catch (final NoSuchMethodException exception) {
			linePrinter.printErrorLine(testClass.getName() +  " does not have a default constructor.");
			return null;
		}
	}
	
	//method
	private void printSummary() {
		
		//TODO: Print duration and count of passed tests.
		linePrinter.printInfoLine("   Finished " + parentTestPool.getName());
		linePrinter.printEmptyLine();
	}
	
	//method
	private <BT extends BaseTest> void runTest(final Class<BT> testClass) {
		try {
			
			final var test = createTestOrNull(testClass);
			
			if (test != null) {
				test.run(linePrinter);
			}
		}
		catch (final Exception exception) {
			linePrinter.printErrorLine("An error occured.");
		}
	}
	
	//method
	private void supposeDidNotStart() {
		if (started()) {
			throw new InvalidArgumentException(this, "started already");
		}
	}
}
