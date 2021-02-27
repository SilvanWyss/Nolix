//package declaration
package ch.nolix.commontest;

//own imports
import ch.nolix.common.basetest.TestPool;
import ch.nolix.commontest.cachingtest.CachingTestPool;
import ch.nolix.commontest.chainednodetest.ChainedNodeTestPool;
import ch.nolix.commontest.commontypehelpertest.CommonTypeHelpersTestPool;
import ch.nolix.commontest.commontypewrappertest.CommonTypeWrapperTestPool;
import ch.nolix.commontest.constanttest.ConstantTestPool;
import ch.nolix.commontest.containertest.ContainerTestPool;
import ch.nolix.commontest.implprovidertest.ImplProviderTestPool;
import ch.nolix.commontest.licensetest.LicenseTestPool;
import ch.nolix.commontest.mathtest.MathematicsTestPool;
import ch.nolix.commontest.nodetest.NodeTestPool;
import ch.nolix.commontest.sequencertest.SequencerTestPool;
import ch.nolix.commontest.validatortest.ValidatorTestPool;
import ch.nolix.commontest.websockettest.WebSocketTestPool;
import ch.nolix.commontest.xmltest.XMLTestPool;

//class
/**
 * @author Silvan Wyss
 * @date 2016-09-10
 * @lines 50
 */
public final class CommonTestPool extends TestPool {
	
	//constructor
	/**
	 * Creates a new {@link CommonTestPool}.
	 */
	public CommonTestPool() {
		super(
			new CachingTestPool(),
			new ChainedNodeTestPool(),
			new CommonTypeHelpersTestPool(),
			new CommonTypeWrapperTestPool(),
			new ConstantTestPool(),
			new ContainerTestPool(),
			new ImplProviderTestPool(),
			new LicenseTestPool(),
			new MathematicsTestPool(),
			new NodeTestPool(),
			new SequencerTestPool(),
			new ValidatorTestPool(),
			new WebSocketTestPool(),
			new XMLTestPool(),
			new ch.nolix.commontest.endpointtest.EndPointTestPool(),
			new ch.nolix.commontest.endpoint2test.EndPointTestPool(),
			new ch.nolix.commontest.endpoint3test.EndPointTestPool()
		);
	}
}
