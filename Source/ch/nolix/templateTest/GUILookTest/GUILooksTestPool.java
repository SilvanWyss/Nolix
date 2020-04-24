//package declaration
package ch.nolix.templateTest.GUILookTest;

//own import
import ch.nolix.common.baseTest.TestPool;

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
