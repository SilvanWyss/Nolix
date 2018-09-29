//package declaration
package ch.nolix.coreTest.sequencerTest;

//own imports
import ch.nolix.core.sequencer.ResultFuture;
import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.core.test2.Test;

//test class
/** 
 * A sequencer test is a test for the sequencer class.
 * A sequencer test is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 30
 */
public final class SequencerTest extends Test {

	//test case
	public void testCase_runInBackground() {		
				
		//execution
			final ResultFuture<Integer> resultFuture
			= Sequencer.runInBackground(() -> 2 * 3 * 4 * 5);
			
			resultFuture.waitUntilFinished();		
		
		//verification
		expect(resultFuture.isFinishedSuccessfully());
		expect(resultFuture.getResult()).isEqualTo(120);		
	}
}
