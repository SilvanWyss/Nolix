//package declaration
package ch.nolix.coretest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.coretest.commontypetest.CommonTypeTestPool;
import ch.nolix.coretest.containertest.ContainerTestPool;
import ch.nolix.coretest.documenttest.DocumentTestPool;
import ch.nolix.coretest.environmenttest.EnvironmentTestPool;
import ch.nolix.coretest.errorcontroltest.ErrorControlTestPool;
import ch.nolix.coretest.licensetest.LicenseTestPool;
import ch.nolix.coretest.mathtest.MathTestPool;
import ch.nolix.coretest.nettest.NetTestPool;
import ch.nolix.coretest.programatomtest.ProgramAtomTestPool;
import ch.nolix.coretest.programcontroltest.ProgramControlTestPool;
import ch.nolix.coretest.programstructuretest.ProgramStructureTestPool;
import ch.nolix.coretest.webtest.WebTestPool;

//class
/**
 * @author Silvan Wyss
 * @date 2016-09-10
 */
public final class CoreTestPool extends TestPool {

  //constructor
  /**
   * Creates a new {@link CoreTestPool}.
   */
  public CoreTestPool() {
    super(
        new CommonTypeTestPool(),
        new ContainerTestPool(),
        new DocumentTestPool(),
        new EnvironmentTestPool(),
        new ErrorControlTestPool(),
        new LicenseTestPool(),
        new MathTestPool(),
        new NetTestPool(),
        new ProgramAtomTestPool(),
        new ProgramControlTestPool(),
        new ProgramStructureTestPool(),
        new WebTestPool());
  }
}
