//package declaration
package ch.nolix.coreapitest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.coreapitest.attributeapitest.AttributeApiTestPool;
import ch.nolix.coreapitest.commontypetoolapitest.CommonTypeApiTestPool;
import ch.nolix.coreapitest.datamodelapitest.DataModelApiTestPool;
import ch.nolix.coreapitest.environmentapitest.EnvironmentApiTestPool;
import ch.nolix.coreapitest.programatomapitest.ProgramAtomApiTestPool;
import ch.nolix.coreapitest.programcontrolapitest.ProgramControlApiTestPool;

//class
public final class CoreApiTestPool extends TestPool {

  //constructor
  public CoreApiTestPool() {
    super(
      new AttributeApiTestPool(),
      new CommonTypeApiTestPool(),
      new DataModelApiTestPool(),
      new EnvironmentApiTestPool(),
      new ProgramAtomApiTestPool(),
      new ProgramControlApiTestPool());
  }
}
