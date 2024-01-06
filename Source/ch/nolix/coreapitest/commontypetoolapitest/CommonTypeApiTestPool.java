//package declaration
package ch.nolix.coreapitest.commontypetoolapitest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.coreapitest.commontypetoolapitest.stringutilapitest.StringUtilApiTestPool;

//class
public final class CommonTypeApiTestPool extends TestPool {

  //constructor
  public CommonTypeApiTestPool() {
    super(new StringUtilApiTestPool());
  }
}
