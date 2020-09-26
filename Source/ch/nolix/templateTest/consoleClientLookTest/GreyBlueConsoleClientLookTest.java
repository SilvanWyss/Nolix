//package declaration
package ch.nolix.templateTest.consoleClientLookTest;

//own import
import ch.nolix.template.consoleClientLook.GreyBlueConsoleClientLook;

//class
public final class GreyBlueConsoleClientLookTest extends ConsoleClientLookTest<GreyBlueConsoleClientLook> {
	
	//method
	@Override
	protected GreyBlueConsoleClientLook createTestUnit() {
		return new GreyBlueConsoleClientLook();
	}
}
