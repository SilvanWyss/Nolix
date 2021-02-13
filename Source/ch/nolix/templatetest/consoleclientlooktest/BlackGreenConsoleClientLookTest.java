//package declaration
package ch.nolix.templatetest.consoleclientlooktest;

//own imports
import ch.nolix.element.configuration.Configuration;
import ch.nolix.template.consoleclientlook.BlackGreenConsoleClientLookCreator;

//class
public final class BlackGreenConsoleClientLookTest extends ConsoleClientLookTest {
	
	//method
	@Override
	protected Configuration createTestUnit() {
		return new BlackGreenConsoleClientLookCreator().createClientLook();
	}
}
