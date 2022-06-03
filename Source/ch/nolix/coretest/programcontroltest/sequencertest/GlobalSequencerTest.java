//package declaration
package ch.nolix.coretest.programcontroltest.sequencertest;

//own imports
import ch.nolix.core.programcontrol.sequencer.ResultFuture;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
/** 
 * A sequencer test is a test for the sequencer class.
 * A sequencer test is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2017-11-18
 */
public final class GlobalSequencerTest extends Test {

	//method
	@TestCase
	public void testCase_runInBackground() {
				
		//execution
			final ResultFuture<Integer> resultFuture
			= GlobalSequencer.runInBackground(() -> 2 * 3 * 4 * 5);
			
			resultFuture.waitUntilIsFinished();
		
		//verification
		expect(resultFuture.isFinishedSuccessfully());
		expect(resultFuture.getResult()).isEqualTo(120);
	}
}
