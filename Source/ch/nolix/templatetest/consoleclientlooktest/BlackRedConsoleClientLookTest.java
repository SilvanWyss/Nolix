//package declaration
package ch.nolix.templatetest.consoleclientlooktest;

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
