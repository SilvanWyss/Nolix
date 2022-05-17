//package declaration
package ch.nolix.templatetest.consoleclientlooktest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.ObjectTest;
import ch.nolix.system.configuration.Configuration;
import ch.nolix.system.gui.base.GUI;
import ch.nolix.system.gui.base.InvisibleGUI;
import ch.nolix.system.gui.containerwidget.ContainerRole;
import ch.nolix.system.gui.containerwidget.VerticalStack;
import ch.nolix.system.gui.widget.Console;

//class
public abstract class ConsoleClientLookCreatorTest extends ObjectTest<Configuration> {
	
	//method
	@TestCase
	public void testCase_configure() {
		
		//setup
		final var lGUI = createConsoleClientEquivalentGUI();
		
		//execution & verification
		expectRunning(() -> createTestUnit().configure(lGUI)).doesNotThrowException();
	}
	
	//method
	private GUI<?> createConsoleClientEquivalentGUI() {
		return
		new InvisibleGUI(
			new VerticalStack()
			.setRole(ContainerRole.MAIN_CONTENT_CONTAINER)
			.add(
				new Console()
				.setId("InfoPanel"),
				new Console()
				.setId("Console")
			)
		);
	}
}
