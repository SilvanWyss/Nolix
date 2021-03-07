//package declaration
package ch.nolix.templatetest.consoleclientlooktest;

import ch.nolix.common.testing.basetest.TestCase;
import ch.nolix.common.testing.test.ObjectTest;
import ch.nolix.element.configuration.Configuration;
import ch.nolix.element.gui.base.GUI;
import ch.nolix.element.gui.base.InvisibleGUI;
import ch.nolix.element.gui.containerwidget.ContainerRole;
import ch.nolix.element.gui.containerwidget.VerticalStack;
import ch.nolix.element.gui.widget.Console;

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
