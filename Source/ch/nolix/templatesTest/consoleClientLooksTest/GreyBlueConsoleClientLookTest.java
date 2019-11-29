//package declaration
package ch.nolix.templatesTest.consoleClientLooksTest;

//own import
import ch.nolix.templates.consoleClientLooks.GreyBlueConsoleClientLook;

//test class
public final class GreyBlueConsoleClientLookTest extends ConsoleClientLookTest<GreyBlueConsoleClientLook> {
	
	//method
	@Override
	protected GreyBlueConsoleClientLook createTestObject() {
		return new GreyBlueConsoleClientLook();
	}
}
