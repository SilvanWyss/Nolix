//package declaration
package ch.nolix.templatetest.consoleclientlooktest;

import ch.nolix.template.consoleclientlook.BlackRedConsoleClientLook;

//class
public final class BlackRedConsoleClientLookTest extends ConsoleClientLookTest<BlackRedConsoleClientLook> {
	
	//method
	@Override
	protected BlackRedConsoleClientLook createTestUnit() {
		return new BlackRedConsoleClientLook();
	}
}
