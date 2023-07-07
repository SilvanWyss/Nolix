//package declaration
package ch.nolix.templatetest.mathtest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.coretest.programcontroltest.sequencertest.SequencerTestPool;

//class
public final class MathTestPool extends TestPool {
	
	//constructor
	public MathTestPool() {
		super(new SequencerTestPool());
	}
}
