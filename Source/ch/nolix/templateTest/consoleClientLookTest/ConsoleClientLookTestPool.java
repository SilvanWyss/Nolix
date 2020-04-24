//package declaration
package ch.nolix.templateTest.consoleClientLookTest;

//own import
import ch.nolix.common.baseTest.TestPool;

//class
public final class ConsoleClientLookTestPool extends TestPool {
	
	//constructor
	public ConsoleClientLookTestPool() {
		super(
			BlackGreenConsoleClientLookTest.class,
			BlackRedConsoleClientLookTest.class,
			GreyBlueConsoleClientLookTest.class
		);
	}
}
