//package declaration
package ch.nolix.systemtest.webguitest.atomiccontroltest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class AtomicControlTestPool extends TestPool {
	
	//constructor
	public AtomicControlTestPool() {
		super(
			ButtonTest.class,
			ImageControlTest.class,
			LinkTest.class,
			LabelTest.class,
			TextboxTest.class,
			ToggleButtonTest.class
		);
	}
}
