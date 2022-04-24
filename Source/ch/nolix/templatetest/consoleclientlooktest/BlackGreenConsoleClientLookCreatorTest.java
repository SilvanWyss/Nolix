//package declaration
package ch.nolix.templatetest.consoleclientlooktest;

import ch.nolix.system.configuration.Configuration;
import ch.nolix.template.consoleclientlook.BlackGreenConsoleClientLookCreator;

//class
public final class BlackGreenConsoleClientLookCreatorTest extends ConsoleClientLookCreatorTest {
	
	//method
	@Override
	protected Configuration createTestUnit() {
		return new BlackGreenConsoleClientLookCreator().createClientLook();
	}
}
