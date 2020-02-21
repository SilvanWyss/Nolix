//package declaration
package ch.nolix.elementTest.GUITest;

//own import
import ch.nolix.common.baseTest.TestPool;

//class
public final class GUITestPool extends TestPool {
	
	//constructor
	public GUITestPool() {
		addTestClass(WidgetStateTest.class);
	}
}
