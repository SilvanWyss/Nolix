//package declaration
package ch.nolix.templatetest.consoleclientlooktest;

import ch.nolix.template.consoleclientlook.GreyBlueConsoleClientLook;

//class
public final class GreyBlueConsoleClientLookTest extends ConsoleClientLookTest<GreyBlueConsoleClientLook> {
	
	//method
	@Override
	protected GreyBlueConsoleClientLook createTestUnit() {
		return new GreyBlueConsoleClientLook();
	}
}
