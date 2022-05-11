//package declaration
package ch.nolix.systemtest.guitest.basetest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class BaseTestPool extends TestPool {
	
	//constructor
	public BaseTestPool() {
		super(FrameTest.class, InvisibleGUITest.class, LayerTest.class);
	}
}
