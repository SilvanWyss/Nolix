//package declaration
package ch.nolix.templateTest.consoleClientLookTest;

//own imports
import ch.nolix.common.baseTest.TestCase;
import ch.nolix.common.test.ObjectTest;
import ch.nolix.element.GUI.GUI;
import ch.nolix.element.GUI.InvisibleGUI;
import ch.nolix.element.configuration.Configuration;
import ch.nolix.element.containerWidget.ContainerRole;
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
			new VerticalStack(
				new Console()
				.setId("InfoPanel"),
				new Console()
				.setId("Console")
			)
			.setRole(ContainerRole.MainContainer)
		);
	}
}
