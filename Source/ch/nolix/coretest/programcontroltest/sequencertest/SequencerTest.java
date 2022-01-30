//package declaration
package ch.nolix.coretest.programcontroltest.sequencertest;

import ch.nolix.core.programcontrol.sequencer.ResultFuture;
import ch.nolix.core.programcontrol.sequencer.Sequencer;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
/** 
 * A sequencer test is a test for the sequencer class.
 * A sequencer test is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2017-11-18
 * @lines 30
 */
public final class SequencerTest extends Test {

	//method
	@TestCase
	public void testCase_runInBackground() {
				
		//execution
			final ResultFuture<Integer> resultFuture
			= Sequencer.runInBackground(() -> 2 * 3 * 4 * 5);
			
			resultFuture.waitUntilIsFinished();
		
		//verification
		expect(resultFuture.isFinishedSuccessfully());
		expect(resultFuture.getResult()).isEqualTo(120);
	}
}
