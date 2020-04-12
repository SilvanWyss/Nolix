//package declaration
package ch.nolix.templatesTest.consoleClientLooksTest;

//own imports
import ch.nolix.templates.consoleClientLooks.BlackGreenConsoleClientLook;

//class
public final class BlackRedConsoleClientLookTest extends ConsoleClientLookTest<BlackGreenConsoleClientLook> {
	
	//method
	@Override
	protected BlackGreenConsoleClientLook createTestObject() {
		return new BlackGreenConsoleClientLook();
	}
}
