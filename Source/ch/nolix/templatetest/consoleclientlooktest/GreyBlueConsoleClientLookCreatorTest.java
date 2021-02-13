//package declaration
package ch.nolix.templatetest.consoleclientlooktest;

//own imports
import ch.nolix.element.configuration.Configuration;
import ch.nolix.template.consoleclientlook.GreyBlueConsoleClientLookCreator;

//class
public final class GreyBlueConsoleClientLookCreatorTest extends ConsoleClientLookCreatorTest {
	
	//method
	@Override
	protected Configuration createTestUnit() {
		return new GreyBlueConsoleClientLookCreator().createConsoleClientLook();
	}
}
