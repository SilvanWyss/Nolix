//package declaration
package ch.nolix.coreapitest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.coreapitest.attributeapitest.AttributeApiTestPool;
import ch.nolix.coreapitest.programcontrolapitest.ProgramControlApiTestPool;

//class
public final class CoreApiTestPool extends TestPool {

  //constructor
  public CoreApiTestPool() {
    super(new AttributeApiTestPool(), new ProgramControlApiTestPool());
  }
}
