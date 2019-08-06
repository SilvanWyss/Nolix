//package declaration
package ch.nolix.templatesTest.GUILooksTest;

import ch.nolix.core.baseTest.TestPool;

//class
public final class GUILooksTestPool extends TestPool {
	
	//constructor
	public GUILooksTestPool() {
		addTestClass(
			AnthrazitGUILookTest.class,
			BlackBlueGUILookTest.class,
			RedLineGUILookTest.class,
			WhiteGreenGUILookTest.class
		);
	}
}
