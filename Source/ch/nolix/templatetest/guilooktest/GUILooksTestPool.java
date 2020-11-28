//package declaration
package ch.nolix.templatetest.guilooktest;

import ch.nolix.common.basetest.TestPool;

//class
public final class GUILooksTestPool extends TestPool {
	
	//constructor
	public GUILooksTestPool() {
		super(
			AnthrazitGUILookTest.class,
			BlackBlueGUILookTest.class,
			RedLineGUILookTest.class,
			WhiteGreenGUILookTest.class
		);
	}
}
