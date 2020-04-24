//package declaration
package ch.nolix.templateTest.consoleClientLookTest;

import ch.nolix.template.consoleClientLook.BlackRedConsoleClientLook;

//class
public final class BlackRedConsoleClientLookTest extends ConsoleClientLookTest<BlackRedConsoleClientLook> {
	
	//method
	@Override
	protected BlackRedConsoleClientLook createTestObject() {
		return new BlackRedConsoleClientLook();
	}
}
