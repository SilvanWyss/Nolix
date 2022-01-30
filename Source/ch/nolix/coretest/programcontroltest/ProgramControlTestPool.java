//package declaration
package ch.nolix.coretest.programcontroltest;

import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.coretest.programcontroltest.sequencertest.SequencerTestPool;

//class
public final class ProgramControlTestPool extends TestPool {
	
	//constructor
	public ProgramControlTestPool() {
		super(new SequencerTestPool());
	}
}
