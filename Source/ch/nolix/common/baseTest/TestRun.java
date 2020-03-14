//package declaration
package ch.nolix.common.baseTest;

//Java import
import java.lang.reflect.Method;

//own imports
import ch.nolix.common.independentContainers.List;
import ch.nolix.common.invalidArgumentExceptions.ArgumentIsNullException;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.invalidArgumentExceptions.NegativeArgumentException;

//class
public final class TestRun {
	
	//attributes
	private final BaseTest parentTest;
	private int runtimeInMilliseconds = -1;
	private final ILinePrinter linePrinter;
	private boolean started = false;
	
	//multi-attribute
	private final List<TestCaseResult> testCaseResults = new List<>();
	
	//constructor
	public TestRun(final BaseTest parentTest, final ILinePrinter linePrinter) {
		
		if (parentTest == null) {
			throw new ArgumentIsNullException("parent test");
		}
		
		if (linePrinter == null) {
			throw new ArgumentIsNullException(ILinePrinter.class);
		}
		
		this.parentTest = parentTest;
		this.linePrinter = linePrinter;
	}
	
	//method
	public int getTestCaseCount() {
		return testCaseResults.getElementCount();
	}
	
	//method
	public int getPassedTestCaseCount() {
		
		var passedTestCaseCount = 0;
		for (final var tcr : testCaseResults) {
			if (tcr.isPassed()) {
				passedTestCaseCount++;
			}
		}
		
		return passedTestCaseCount;
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
		
		supposeIsComplete();
		
		return runtimeInMilliseconds;
	}
	
	//method
	public boolean isComplete() {
		return (runtimeInMilliseconds > -1);
	}
	
	//method
	public boolean isPassed() {
		
		supposeIsComplete();
		
		for (var tcr : testCaseResults) {
			if (tcr.isFailed()) {
				return false;
			}
		}
		
		return true;
	}
	
	//method
	public void run() {
		
		supposeDidNotStart();
		
		started = true;
		final var startTimeInMilliseconds = System.currentTimeMillis();
		linePrinter.printLine("Start " + parentTest.getName());
		
		for (final var tc : getRefTestCases()) {
			addAndPrintTestCaseResult(new TestCaseRun(parentTest, tc).getResult());
		}
		
		complete((int)(System.currentTimeMillis() - startTimeInMilliseconds));
		printSummary();
	}
	
	//method
	public boolean started() {
		return started;
	}
	
	//method
	private void addAndPrintTestCaseResult(final TestCaseResult testCaseResult) {
		
		if (testCaseResult == null) {
			throw new ArgumentIsNullException(TestCaseResult.class);
		}
		
		supposeIsIncomplete();
		
		testCaseResults.addAtEnd(testCaseResult);
		linePrinter.printLines(testCaseResult.getOutputLines());
	}

	//method
	private void complete(final int runtimeInMilliseconds) {
		
		if (runtimeInMilliseconds < 0) {
			throw new NegativeArgumentException("runtime in milliseconds", runtimeInMilliseconds);
		}
		
		supposeIsIncomplete();
		
		this.runtimeInMilliseconds = runtimeInMilliseconds;
	}

	//method
	private List<Method> getRefTestCases() {
		return parentTest.getRefTestCases();
	}
	
	//method
	private void printSummary() {
		linePrinter.printLine(
			"Summary "
			+ parentTest.getName()
			+ ": "
			+ getPassedTestCaseCount()
			+ "/"
			+ getTestCaseCount()
			+ getRuntimeAndUnitAsStringInBrackets()
		);
	}
	
	//method
	private void supposeDidNotStart() {
		if (started()) {
			throw new InvalidArgumentException(this, "started already");
		}
	}
	
	//method
	private void supposeIsComplete() {
		if (!isComplete()) {
			throw new InvalidArgumentException(this, "is not complete");
		}
	}
	
	//method
	private void supposeIsIncomplete() {
		if (isComplete()) {
			throw new InvalidArgumentException(this, "is already complete");
		}
	}
}
