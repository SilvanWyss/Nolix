//package declaration
package ch.nolix.templatesTest.consoleClientLooksTest;

//own import
import ch.nolix.templates.consoleClientLooks.BlackGreenConsoleClientLook;

//test class
public final class BlackGreenConsoleClientLookTest extends ConsoleClientLookTest<BlackGreenConsoleClientLook> {
	
	//method
	@Override
	protected BlackGreenConsoleClientLook createTestObject() {
		return new BlackGreenConsoleClientLook();
	}
}
