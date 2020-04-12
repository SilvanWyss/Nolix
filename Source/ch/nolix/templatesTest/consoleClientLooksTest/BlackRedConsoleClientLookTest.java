//package declaration
package ch.nolix.templatesTest.consoleClientLooksTest;

//own imports
import ch.nolix.templates.consoleClientLooks.BlackRedConsoleClientLook;

//class
public final class BlackRedConsoleClientLookTest extends ConsoleClientLookTest<BlackRedConsoleClientLook> {
	
	//method
	@Override
	protected BlackRedConsoleClientLook createTestObject() {
		return new BlackRedConsoleClientLook();
	}
}
