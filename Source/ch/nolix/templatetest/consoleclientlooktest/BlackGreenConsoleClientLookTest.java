//package declaration
package ch.nolix.templatetest.consoleclientlooktest;

//own imports
import ch.nolix.template.consoleclientlook.BlackGreenConsoleClientLook;

//class
public final class BlackGreenConsoleClientLookTest extends ConsoleClientLookTest<BlackGreenConsoleClientLook> {
	
	//method
	@Override
	protected BlackGreenConsoleClientLook createTestUnit() {
		return new BlackGreenConsoleClientLook();
	}
}
