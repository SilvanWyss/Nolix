//package declaration
package ch.nolix.coreapitest.commontypeapitest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.coreapitest.commontypeapitest.stringutilapitest.StringUtilApiTestPool;

//class
public final class CommonTypeApiTestPool extends TestPool {

  //constructor
  public CommonTypeApiTestPool() {
    super(new StringUtilApiTestPool());
  }
}
