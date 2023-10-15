//package declaration
package ch.nolix.coretest.commontypetest.commontypehelpertest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
/**
 * @author Silvan Wyss
 * @date 2018-09-01
 */
public final class CommonTypeHelperTestPool extends TestPool {

  // constructor
  /**
   * Creates a new {@link CommonTypeHelperTestPool}.
   */
  public CommonTypeHelperTestPool() {
    super(
        GlobalCharacterHelperTest.class,
        GlobalIterableHelperTest.class,
        GlobalStringHelperTest.class);
  }
}
