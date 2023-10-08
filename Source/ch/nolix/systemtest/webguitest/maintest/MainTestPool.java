//package declaration
package ch.nolix.systemtest.webguitest.maintest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class MainTestPool extends TestPool {
	
	//constructor
	public MainTestPool() {
		super(LayerTest.class, WebGuiTest.class);
	}
}
