//package declaration
package ch.nolix.templatesTest.consoleClientLooksTest;

//own imports
import ch.nolix.templates.consoleClientLooks.BlackGreenConsoleClientLook;

//test class
public final class BlackRedConsoleCllientLookTest extends ConsoleClientLookTest<BlackGreenConsoleClientLook> {
	
	//method
	@Override
	protected BlackGreenConsoleClientLook createTestObject() {
		return new BlackGreenConsoleClientLook();
	}
}
