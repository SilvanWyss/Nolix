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
	private final ILinePrinter linePrinter;
	private boolean started = false;
	private int runtimeInMilliseconds = -1;
	
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
		
		supposeIsFinished();
		
		return runtimeInMilliseconds;
	}
	
	//method
	public boolean isFinished() {
		return (runtimeInMilliseconds > -1);
	}
	
	//method
	public boolean isPassed() {
		
		supposeIsFinished();
		
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
		linePrinter.printInfoLine("   Started " + parentTest.getName());
		
		for (final var tc : getRefTestCasesOrderedAlphabetically()) {
			addAndPrintTestCaseResult(new TestCaseRun(parentTest, tc).getResult());
		}
		
		setFinished((int)(System.currentTimeMillis() - startTimeInMilliseconds));
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
		
		supposeIsNotFinished();
		
		testCaseResults.addAtEnd(testCaseResult);
		printTestCaseResult(testCaseResult);
	}
	
	//method
	private List<Method> getRefTestCasesOrderedAlphabetically() {
		return parentTest.getRefTestCasesOrderedAlphabetically();
	}
	
	//method
	private void printSummary() {
		if (isPassed()) {
			printSummaryWhenPassed();
		}
		else {
			printSummaryWhenFailed();
		}
	}
	
	//method
	private void printSummaryWhenFailed() {
		
		linePrinter.printErrorLine(
			"-->Summary "
			+ parentTest.getName()
			+ ": "
			+ getPassedTestCaseCount()
			+ "/"
			+ getTestCaseCount()
			+ " test cases passed "
			+ getRuntimeAndUnitAsStringInBrackets()
		);
		
		linePrinter.printEmptyLine();
	}
	
	//method
	private void printSummaryWhenPassed() {
		
		linePrinter.printInfoLine(
			"   Summary "
			+ parentTest.getName()
			+ ": "
			+ getPassedTestCaseCount()
			+ " of "
			+ getTestCaseCount()
			+ " test cases passed "
			+ getRuntimeAndUnitAsStringInBrackets()
		);
		
		linePrinter.printEmptyLine();
	}
	
	//method
	private void printTestCaseResult(final TestCaseResult testCaseResult) {
		if (testCaseResult.isPassed()) {
			linePrinter.printInfoLines(testCaseResult.getOutputLines());
		}
		else {
			linePrinter.printErrorLines(testCaseResult.getOutputLines());
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
	private void supposeDidNotStart() {
		if (started()) {
			throw new InvalidArgumentException(this, "started already");
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
