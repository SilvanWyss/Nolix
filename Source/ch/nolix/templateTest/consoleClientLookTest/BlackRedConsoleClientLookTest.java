//package declaration
package ch.nolix.templateTest.consoleClientLookTest;

//own import
import ch.nolix.template.consoleClientLook.BlackRedConsoleClientLook;

//class
public final class BlackRedConsoleClientLookTest extends ConsoleClientLookTest<BlackRedConsoleClientLook> {
	
	//method
	@Override
	protected BlackRedConsoleClientLook createTestUnit() {
		return new BlackRedConsoleClientLook();
	}
}
