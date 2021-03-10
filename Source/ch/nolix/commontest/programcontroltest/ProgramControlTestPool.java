//package declaration
package ch.nolix.commontest.programcontroltest;

//own imports
import ch.nolix.common.testing.basetest.TestPool;
import ch.nolix.commontest.programcontroltest.sequencertest.SequencerTestPool;

//class
public final class ProgramControlTestPool extends TestPool {
	
	//constructor
	public ProgramControlTestPool() {
		super(new SequencerTestPool());
	}
}
