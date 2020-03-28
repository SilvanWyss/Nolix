//package declaration
package ch.nolix.common.baseTest;

//Java import
import java.lang.reflect.InvocationTargetException;

//own imports
import ch.nolix.common.invalidArgumentExceptions.ArgumentIsNullException;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.invalidArgumentExceptions.NegativeArgumentException;

//class
public final class TestPoolRun {
	
	//attributes
	private final TestPool parentTestPool;
	private final ILinePrinter linePrinter;
	private boolean started = false;
	private int runtimeInMilliseconds = -1;
	
	//constructor
	public TestPoolRun(final TestPool parentTestPool, final ILinePrinter linePrinter) {
		
		if (parentTestPool == null) {
			throw new ArgumentIsNullException("parent TestPool");
		}
		
		if (linePrinter == null) {
			throw new ArgumentIsNullException("line printer");
		}
		
		this.parentTestPool = parentTestPool;
		this.linePrinter = linePrinter;
	}
	
	//method
	public String getRuntimeAndUnitAsString() {
		return (String.valueOf(getRuntimeInMilliseconds()) + " ms");
	}
	
	//method
	public String getRuntimeAndUnitAsStringInBrackets() {
		return ("(" + getRuntimeAndUnitAsString() + ")");
	}
	
	//method
	public int getRuntimeInMilliseconds() {
		
		supposeIsFinished();
		
		return runtimeInMilliseconds;
	}
	
	//method
	public boolean hasStarted() {
		return started;
	}
	
	//method
	public boolean isFinished() {
		return (runtimeInMilliseconds > -1);
	}
	
	//method
	public void run() {
		
		//setup phase
		setStarted();
		final var startTimeInMilliseconds = System.currentTimeMillis();
		
		//main phase part 1
		for (final var tp : parentTestPool.getRefTestPools()) {
			tp.run(linePrinter);
		}
		
		//main phase part 2
		parentTestPool.getRefTestClasses().forEach(this::runTest);
		
		//result phase
		setFinished((int)(System.currentTimeMillis() - startTimeInMilliseconds));
		printSummary();
	}
	
	//method
	private <BT extends BaseTest> BT createTestOrNull(final Class<BT> testClass) {
		try {
			return ReflectionHelper.getDefaultConstructor(testClass).newInstance();
		}
		catch (
			final
			IllegalAccessException
			| InstantiationException
			| InvocationTargetException
			exception
		) {
			linePrinter.printErrorLine("-->Could not create a " + testClass.getName() + ".");
			return null;
		}
	}
	
	//method
	private void printSummary() {
		
		//TODO: Print count of passed tests.
		linePrinter.printInfoLine(
			"   Summary " + parentTestPool.getName() + " " + getRuntimeAndUnitAsStringInBrackets()
		);
		
		linePrinter.printEmptyLine();
	}
	
	//method
	private <BT extends BaseTest> void runTest(final Class<BT> testClass) {
		
		final var test = createTestOrNull(testClass);
		
		if (test != null) {
			test.run(linePrinter);
		}
	}
	
	//method
	private void setFinished(final int runtimeInMilliseconds) {
		
		if (runtimeInMilliseconds < 0) {
			throw new NegativeArgumentException("runtime in milliseconds", runtimeInMilliseconds);
		}
		
		supposeIsNotFinished();
		
		this.runtimeInMilliseconds = runtimeInMilliseconds;
	}
	
	//method
	private void setStarted() {
		
		supposeHasNotStarted();
		
		started = true;
		linePrinter.printInfoLine("   Started " + parentTestPool.getName());
		linePrinter.printEmptyLine();
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
			throw new InvalidArgumentException(this, "is already finished");
		}
	}
}
