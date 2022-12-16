//package declaration
package ch.nolix.core.testing.basetest;

//Java imports
import java.lang.reflect.Method;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.independent.independentcontainer.List;

//class
public final class TestRun {
	
	//attributes
	private final BaseTest parentTest;
	private final ILinePrinter linePrinter;
	private boolean started;
	private int runtimeInMilliseconds = -1;
	
	//multi-attribute
	private final List<TestCaseResult> testCaseResults = new List<>();
	
	//constructor
	public TestRun(final BaseTest parentTest, final ILinePrinter linePrinter) {
		
		if (parentTest == null) {
			throw ArgumentIsNullException.forArgumentName("parent test");
		}
		
		if (linePrinter == null) {
			throw ArgumentIsNullException.forArgumentName("line printer");
		}
		
		this.parentTest = parentTest;
		this.linePrinter = linePrinter;
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
	public int getTestCaseCount() {
		return testCaseResults.getElementCount();
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
		
		//setup phase
		setStarted();
		final var startTimeInMilliseconds = System.currentTimeMillis();
		linePrinter.printInfoLine("   STARTED: " + parentTest.getSimpleName());
		
		//main phase
		for (final var tc : getRefTestCasesOrderedAlphabetically()) {
			addAndPrintTestCaseResult(new TestCaseRun(parentTest, tc).runAndGetResult());
		}
		
		//result phase
		setFinished((int)(System.currentTimeMillis() - startTimeInMilliseconds));
		printSummary();
	}
	
	//method
	private void addAndPrintTestCaseResult(final TestCaseResult testCaseResult) {
		
		if (testCaseResult == null) {
			throw ArgumentIsNullException.forArgumentType(TestCaseResult.class);
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
		
		linePrinter.printInfoLine(
			"   FINISHED: "
			+ getPassedTestCaseCount()
			+ " of "
			+ getTestCaseCount()
			+ " test cases passed of "
			+ parentTest.getSimpleName()
			+ " "
			+ getRuntimeAndUnitAsStringInBrackets()
		);
		
		linePrinter.printEmptyLine();
	}
	
	//method
	private void printTestCaseResult(final TestCaseResult testCaseResult) {
		if (testCaseResult.isPassed()) {
			linePrinter.printInfoLines(testCaseResult.getOutputLines());
		} else {
			linePrinter.printErrorLines(testCaseResult.getOutputLines());
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
	}
	
	//method
	private void supposeHasNotStarted() {
		if (hasStarted()) {
			throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "started already");
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
