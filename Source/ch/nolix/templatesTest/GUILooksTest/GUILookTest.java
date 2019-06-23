//package declaration
package ch.nolix.templatesTest.GUILooksTest;

//own imports
import ch.nolix.core.test.ObjectTest;
import ch.nolix.element.GUI.InvisibleGUI;
import ch.nolix.element.configuration.StandardConfiguration;

//abstract test class
public abstract class GUILookTest<GL extends StandardConfiguration> extends ObjectTest<GL> {
	
	//test case
	public void testCase_configure_whenTheGivenGUIIsEmpty() {
		
		//setup
		final var GUI = new InvisibleGUI();
		
		//execution & verification
		expect(() -> createTestObject().configure(GUI)).doesNotThrowException();
	}
}
