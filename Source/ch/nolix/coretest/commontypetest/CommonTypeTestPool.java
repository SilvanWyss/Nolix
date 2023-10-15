//package declaration
package ch.nolix.coretest.commontypetest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.coretest.commontypetest.commontypehelpertest.CommonTypeHelperTestPool;
import ch.nolix.coretest.commontypetest.commontypewrappertest.CommonTypeWrapperTestPool;
import ch.nolix.coretest.commontypetest.constanttest.ConstantTestPool;

//class
public final class CommonTypeTestPool extends TestPool {

  // constructor
  public CommonTypeTestPool() {
    super(new CommonTypeHelperTestPool(), new CommonTypeWrapperTestPool(), new ConstantTestPool());
  }
}
