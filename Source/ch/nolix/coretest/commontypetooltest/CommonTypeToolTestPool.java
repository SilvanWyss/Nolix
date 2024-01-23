//package declaration
package ch.nolix.coretest.commontypetooltest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class CommonTypeToolTestPool extends TestPool {

  //constructor
  public CommonTypeToolTestPool() {
    super(
      GlobalCharacterToolTest.class,
      GlobalIterableToolTest.class,
      GlobalStringToolTest.class);
  }
}
