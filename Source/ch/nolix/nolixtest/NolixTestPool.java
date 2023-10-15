//package declaration
package ch.nolix.nolixtest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.coreapitest.CoreApiTestPool;
import ch.nolix.coretest.CoreTestPool;
import ch.nolix.systemtest.SystemTestPool;
import ch.nolix.techapitest.TechApiTestPool;
import ch.nolix.techtest.TechTestPool;
import ch.nolix.templatetest.TemplateTestPool;

//class
/**
 * A {@link NolixTestPool} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2017-11-17
 */
public final class NolixTestPool extends TestPool {

  // constructor
  /**
   * Creates a new {@link NolixTestPool}.
   */
  public NolixTestPool() {
    super(
        new CoreApiTestPool(),
        new CoreTestPool(),
        new SystemTestPool(),
        new TechApiTestPool(),
        new TechTestPool(),
        new TemplateTestPool());
  }
}
