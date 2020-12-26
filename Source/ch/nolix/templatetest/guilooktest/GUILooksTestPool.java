//package declaration
package ch.nolix.templatetest.guilooktest;

//own imports
import ch.nolix.common.basetest.TestPool;

//class
public final class GUILooksTestPool extends TestPool {
	
	//constructor
	public GUILooksTestPool() {
		super(AnthrazitGUILookTest.class,	RedLineGUILookTest.class);
	}
}
