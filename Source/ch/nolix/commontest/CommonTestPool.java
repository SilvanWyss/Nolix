//package declaration
package ch.nolix.commontest;

import ch.nolix.common.testing.basetest.TestPool;
import ch.nolix.commontest.cachingtest.CachingTestPool;
import ch.nolix.commontest.commontypetest.commontypehelpertest.CommonTypeHelpersTestPool;
import ch.nolix.commontest.commontypetest.commontypewrappertest.CommonTypeWrapperTestPool;
import ch.nolix.commontest.constanttest.ConstantTestPool;
import ch.nolix.commontest.containertest.ContainerTestPool;
import ch.nolix.commontest.demotest.DemoTestPool;
import ch.nolix.commontest.documenttest.DocumentNodeTestPool;
import ch.nolix.commontest.errorcontroltest.ErrorControlTestPool;
import ch.nolix.commontest.implprovidertest.ImplProviderTestPool;
import ch.nolix.commontest.licensetest.LicenseTestPool;
import ch.nolix.commontest.mathtest.MathTestPool;
import ch.nolix.commontest.nettest.NetTestPool;
import ch.nolix.commontest.nettest.websockettest.WebSocketTestPool;
import ch.nolix.commontest.sequencertest.SequencerTestPool;

//class
/**
 * @author Silvan Wyss
 * @date 2016-09-10
 * @lines 40
 */
public final class CommonTestPool extends TestPool {
	
	//constructor
	/**
	 * Creates a new {@link CommonTestPool}.
	 */
	public CommonTestPool() {
		super(
			new CachingTestPool(),
			new CommonTypeHelpersTestPool(),
			new CommonTypeWrapperTestPool(),
			new ConstantTestPool(),
			new ContainerTestPool(),
			new DemoTestPool(),
			new DocumentNodeTestPool(),
			new ErrorControlTestPool(),
			new ImplProviderTestPool(),
			new LicenseTestPool(),
			new MathTestPool(),
			new NetTestPool(),
			new SequencerTestPool(),
			new WebSocketTestPool()
		);
	}
}
