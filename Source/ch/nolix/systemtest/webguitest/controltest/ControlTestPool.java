//package declaration
package ch.nolix.systemtest.webguitest.controltest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class ControlTestPool extends TestPool {
	
	//constructor
	public ControlTestPool() {
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
