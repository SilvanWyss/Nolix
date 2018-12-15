//package declaration
package ch.nolix.coreTest.sequencerTest;

//own imports
import ch.nolix.core.testoid.TestPool;

//class
/**
 * A sequencer test pool is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 20
 */
public final class SequencerTestPool extends TestPool {

	//constructor
	/**
	 * Creates a new sequencerx test pool.
	 */
	public SequencerTestPool() {
		addTestClass(SequencerTest.class);
	}
}
