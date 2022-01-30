//package declaration
package ch.nolix.commontest.programcontroltest;

import ch.nolix.commontest.programcontroltest.sequencertest.SequencerTestPool;
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class ProgramControlTestPool extends TestPool {
	
	//constructor
	public ProgramControlTestPool() {
		super(new SequencerTestPool());
	}
}
