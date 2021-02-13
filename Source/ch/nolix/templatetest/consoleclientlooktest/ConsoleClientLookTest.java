//package declaration
package ch.nolix.templatetest.consoleclientlooktest;

//own imports
import ch.nolix.common.basetest.TestCase;
import ch.nolix.common.test.ObjectTest;
import ch.nolix.element.configuration.Configuration;
import ch.nolix.element.containerwidget.ContainerRole;
import ch.nolix.element.containerwidget.VerticalStack;
import ch.nolix.element.gui.GUI;
import ch.nolix.element.gui.InvisibleGUI;
import ch.nolix.element.widget.Console;

//class
public abstract class ConsoleClientLookTest extends ObjectTest<Configuration> {
	
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
			.setRole(ContainerRole.MAINT_CONTAINER)
			.addWidget(
				new Console()
				.setId("InfoPanel"),
				new Console()
				.setId("Console")
			)
		);
	}
}
