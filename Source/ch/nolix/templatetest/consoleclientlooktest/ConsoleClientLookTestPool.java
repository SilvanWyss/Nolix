//package declaration
package ch.nolix.templatetest.consoleclientlooktest;

//own imports
import ch.nolix.common.basetest.TestPool;

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
