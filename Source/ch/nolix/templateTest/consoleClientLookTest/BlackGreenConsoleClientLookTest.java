//package declaration
package ch.nolix.templateTest.consoleClientLookTest;

import ch.nolix.template.consoleClientLook.BlackGreenConsoleClientLook;

//class
public final class BlackGreenConsoleClientLookTest extends ConsoleClientLookTest<BlackGreenConsoleClientLook> {
	
	//method
	@Override
	protected BlackGreenConsoleClientLook createTestUnit() {
		return new BlackGreenConsoleClientLook();
	}
}
