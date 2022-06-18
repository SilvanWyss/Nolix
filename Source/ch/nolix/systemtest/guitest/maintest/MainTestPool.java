//package declaration
package ch.nolix.systemtest.guitest.maintest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class MainTestPool extends TestPool {
	
	//constructor
	public MainTestPool() {
		super(FrameTest.class, InvisibleGUITest.class, LayerTest.class);
	}
}
