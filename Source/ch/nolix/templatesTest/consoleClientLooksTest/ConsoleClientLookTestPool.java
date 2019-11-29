//package declaration
package ch.nolix.templatesTest.consoleClientLooksTest;

//own import
import ch.nolix.common.baseTest.TestPool;

//class
public final class ConsoleClientLookTestPool extends TestPool {
	
	//constructor
	public ConsoleClientLookTestPool() {
		addTestClass(
			BlackGreenConsoleClientLookTest.class,
			BlackRedConsoleCllientLookTest.class,
			GreyBlueConsoleClientLookTest.class
		);
	}
}
