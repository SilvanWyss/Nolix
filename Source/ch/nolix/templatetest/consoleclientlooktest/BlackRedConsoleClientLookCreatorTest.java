//package declaration
package ch.nolix.templatetest.consoleclientlooktest;

//own imports
import ch.nolix.element.configuration.Configuration;
import ch.nolix.template.consoleclientlook.BlackRedConsoleClientLookCreator;

//class
public final class BlackRedConsoleClientLookCreatorTest extends ConsoleClientLookCreatorTest {
	
	//method
	@Override
	protected Configuration createTestUnit() {
		return new BlackRedConsoleClientLookCreator().createClientLook();
	}
}
