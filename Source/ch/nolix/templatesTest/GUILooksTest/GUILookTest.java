//package declaration
package ch.nolix.templatesTest.GUILooksTest;

import ch.nolix.common.test.ObjectTest;
import ch.nolix.element.GUI.InvisibleLayerGUI;
import ch.nolix.element.configuration.StandardConfiguration;

//abstract test class
public abstract class GUILookTest<GL extends StandardConfiguration> extends ObjectTest<GL> {
	
	//test case
	public void testCase_configure_whenTheGivenGUIIsEmpty() {
		
		//setup
		final var GUI = new InvisibleLayerGUI();
		
		//execution & verification
		expect(() -> createTestObject().configure(GUI)).doesNotThrowException();
	}
}
