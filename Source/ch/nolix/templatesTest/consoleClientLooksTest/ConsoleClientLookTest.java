//package declaration
package ch.nolix.templatesTest.consoleClientLooksTest;

//own imports
import ch.nolix.common.baseTest.TestCase;
import ch.nolix.common.test.ObjectTest;
import ch.nolix.element.GUI.GUI;
import ch.nolix.element.GUI.InvisibleLayerGUI;
import ch.nolix.element.configuration.StandardConfiguration;
import ch.nolix.element.widgets.Console;
import ch.nolix.element.widgets.VerticalStack;

//test class
public abstract class ConsoleClientLookTest<CCL extends StandardConfiguration> extends ObjectTest<CCL> {
	
	//method
	@TestCase
	public void testCase_configure() {
		
		//setup
		final var GUI = createConsoleClientEquivalentGUI();
		
		//execution & verification
		expect(() -> createTestObject().configure(GUI)).doesNotThrowException();
	}
	
	private static GUI<?> createConsoleClientEquivalentGUI() {
		return new InvisibleLayerGUI(new VerticalStack(new Console(), new Console()));
	}
}
