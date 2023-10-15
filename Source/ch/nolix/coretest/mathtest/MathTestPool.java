//package declaration
package ch.nolix.coretest.mathtest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.coretest.mathtest.algebratest.AlgebraTestPool;
import ch.nolix.coretest.mathtest.maintest.MainTestPool;
import ch.nolix.coretest.mathtest.stochastictest.StochasticTestPool;

//class
/**
 * @author Silvan Wyss
 * @date 2016-09-01
 */
public final class MathTestPool extends TestPool {

  // constructor
  /**
   * Creates a new {@link MathTestPool}.
   */
  public MathTestPool() {
    super(
        new AlgebraTestPool(),
        new MainTestPool(),
        new StochasticTestPool());
  }
}
