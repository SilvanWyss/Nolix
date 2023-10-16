//package declaration
package ch.nolix.systemtest.timetest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.systemtest.timetest.momenttest.MomentTestPool;

//class
/**
 * @author Silvan Wyss
 * @date 2017-11-14
 */
public final class TimeTestPool extends TestPool {

  //constructor
  /**
   * Creates a new {@link TimeTestPool}.
   */
  public TimeTestPool() {
    super(new MomentTestPool());
  }
}
