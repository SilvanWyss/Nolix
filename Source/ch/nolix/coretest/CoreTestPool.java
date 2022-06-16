//package declaration
package ch.nolix.coretest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.coretest.cachingtest.CachingTestPool;
import ch.nolix.coretest.commontypetest.CommonTypeTestPool;
import ch.nolix.coretest.commontypetest.constanttest.ConstantTestPool;
import ch.nolix.coretest.containertest.ContainerTestPool;
import ch.nolix.coretest.demotest.DemoTestPool;
import ch.nolix.coretest.documenttest.DocumentTestPool;
import ch.nolix.coretest.errorcontroltest.ErrorControlTestPool;
import ch.nolix.coretest.licensetest.LicenseTestPool;
import ch.nolix.coretest.mathtest.MathTestPool;
import ch.nolix.coretest.nettest.NetTestPool;
import ch.nolix.coretest.programatomtest.ProgramAtomTestPool;
import ch.nolix.coretest.programcontroltest.ProgramControlTestPool;
import ch.nolix.coretest.providertest.ProviderTestPool;

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
			new CachingTestPool(),
			new CommonTypeTestPool(),
			new ConstantTestPool(),
			new ContainerTestPool(),
			new DemoTestPool(),
			new DocumentTestPool(),
			new ErrorControlTestPool(),
			new LicenseTestPool(),
			new MathTestPool(),
			new NetTestPool(),
			new ProgramAtomTestPool(),
			new ProgramControlTestPool(),
			new ProviderTestPool()
		);
	}
}
