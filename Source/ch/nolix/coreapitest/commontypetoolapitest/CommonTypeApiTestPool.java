//package declaration
package ch.nolix.coreapitest.commontypetoolapitest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.coreapitest.commontypetoolapitest.stringtoolapitest.StringToolApiTestPool;

//class
public final class CommonTypeApiTestPool extends TestPool {

  //constructor
  public CommonTypeApiTestPool() {
    super(new StringToolApiTestPool());
  }
}
