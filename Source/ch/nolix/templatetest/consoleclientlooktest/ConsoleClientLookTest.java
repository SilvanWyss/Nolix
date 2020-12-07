//package declaration
package ch.nolix.templatetest.consoleclientlooktest;

//own imports
import ch.nolix.common.basetest.TestCase;
import ch.nolix.common.test.ObjectTest;
import ch.nolix.element.configuration.Configuration;
import ch.nolix.element.containerwidget.ContainerRole;
import ch.nolix.element.gui.GUI;
import ch.nolix.element.gui.InvisibleGUI;
import ch.nolix.element.widget.Console;
import ch.nolix.element.widget.VerticalStack;

//class
public abstract class ConsoleClientLookTest<CCL extends Configuration> extends ObjectTest<CCL> {
	
	//method
	@TestCase
	public void testCase_configure() {
		
		//setup
		final var lGUI = createConsoleClientEquivalentGUI();
		
		//execution & verification
		expect(() -> createTestUnit().configure(lGUI)).doesNotThrowException();
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
