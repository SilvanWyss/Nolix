//package declaration
package ch.nolix.coretest.programcontroltest.sequencertest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
/**
 * @author Silvan Wyss
 * @date 2017-11-18
 */
public final class SequencerTestPool extends TestPool {

  //constructor
  /**
   * Creates a new {@link SequencerTestPool}.
   */
  public SequencerTestPool() {
    super(ForCountMediatorTest.class, GlobalSequencerTest.class);
  }
}
