//package declaration
package ch.nolix.coretest.commontypetooltest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.coretest.commontypetooltest.commontypehelpertest.CommonTypeHelperTestPool;
import ch.nolix.coretest.commontypetooltest.unsignedbytetest.UnsignedTypeTestPool;

//class
public final class CommonTypeToolTestPool extends TestPool {

  //constructor
  public CommonTypeToolTestPool() {
    super(new CommonTypeHelperTestPool(), new UnsignedTypeTestPool());
  }
}
