//package declaration
package ch.nolix.templatetest.consoleclientlooktest;

import ch.nolix.core.testing.basetest.TestPool;

//class
public final class ConsoleClientLookTestPool extends TestPool {
	
	//constructor
	public ConsoleClientLookTestPool() {
		super(
			BlackGreenConsoleClientLookCreatorTest.class,
			BlackRedConsoleClientLookCreatorTest.class,
			GreyBlueConsoleClientLookCreatorTest.class
		);
	}
}
