//package declaration
package ch.nolix.templateTest.consoleClientLookTest;

//own imports
import ch.nolix.common.baseTest.TestCase;
import ch.nolix.common.test.ObjectTest;
import ch.nolix.element.GUI.GUI;
import ch.nolix.element.GUI.InvisibleLayerGUI;
import ch.nolix.element.configuration.StandardConfiguration;
import ch.nolix.element.containerWidgets.ContainerRole;
import ch.nolix.element.widgets.Console;
import ch.nolix.element.widgets.VerticalStack;

//class
public abstract class ConsoleClientLookTest<CCL extends StandardConfiguration> extends ObjectTest<CCL> {
	
	//method
	@TestCase
	public void testCase_configure() {
		
		//setup
		final var lGUI = createConsoleClientEquivalentGUI();
		
		//execution & verification
		expect(() -> createTestObject().configure(lGUI)).doesNotThrowException();
	}
	
	//method
	private GUI<?> createConsoleClientEquivalentGUI() {
		return
		new InvisibleLayerGUI(
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
