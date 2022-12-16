//package declaration
package ch.nolix.core.testing.basetest;

//Java imports
import java.lang.reflect.InvocationTargetException;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;

//class
public final class TestPoolRun {
	
	//attributes
	private final TestPool parentTestPool;
	private final ILinePrinter linePrinter;
	private boolean started;
	private int runtimeInMilliseconds = -1;
	
	//constructor
	public TestPoolRun(final TestPool parentTestPool, final ILinePrinter linePrinter) {
		
		if (parentTestPool == null) {
			throw ArgumentIsNullException.forArgumentName("parent TestPool");
		}
		
		if (linePrinter == null) {
			throw ArgumentIsNullException.forArgumentName("line printer");
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
		} catch (
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
		
		linePrinter.printInfoLine(
			"   FINISHED: " + parentTestPool.getSimpleName() + " " + getRuntimeAndUnitAsStringInBrackets()
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
			throw NegativeArgumentException.forArgumentNameAndArgument("runtime in milliseconds", runtimeInMilliseconds);
		}
		
		supposeIsNotFinished();
		
		this.runtimeInMilliseconds = runtimeInMilliseconds;
	}
	
	//method
	private void setStarted() {
		
		supposeHasNotStarted();
		
		started = true;
		linePrinter.printInfoLine("   STARTED: " + parentTestPool.getSimpleName());
		linePrinter.printEmptyLine();
	}
	
	//method
	private void supposeHasNotStarted() {
		if (hasStarted()) {
			throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "has started already");
		}
	}
	
	//method
	private void supposeIsFinished() {
		if (!isFinished()) {
			throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is not finished");
		}
	}
	
	//method
	private void supposeIsNotFinished() {
		if (isFinished()) {
			throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is already finished");
		}
	}
}
