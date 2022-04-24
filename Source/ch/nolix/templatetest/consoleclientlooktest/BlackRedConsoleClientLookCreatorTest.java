//package declaration
package ch.nolix.templatetest.consoleclientlooktest;

import ch.nolix.system.configuration.Configuration;
import ch.nolix.template.consoleclientlook.BlackRedConsoleClientLookCreator;

//class
public final class BlackRedConsoleClientLookCreatorTest extends ConsoleClientLookCreatorTest {
	
	//method
	@Override
	protected Configuration createTestUnit() {
		return new BlackRedConsoleClientLookCreator().createClientLook();
	}
}
