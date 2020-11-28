//package declaration
package ch.nolix.commonTest;

//own imports
import ch.nolix.common.baseTest.TestPool;
import ch.nolix.commonTest.cachingContainerTest.CachingContainerTestPool;
import ch.nolix.commonTest.chainedNodeTest.ChainedNodeTestPool;
import ch.nolix.commonTest.commonTypeHelperTest.CommonTypeHelpersTestPool;
import ch.nolix.commonTest.commonTypeWrapperTest.CommonTypeWrapperTestPool;
import ch.nolix.commonTest.containerTest.ContainerTestPool;
import ch.nolix.commonTest.licenseTest.LicenseTestPool;
import ch.nolix.commonTest.mathTest.MathematicsTestPool;
import ch.nolix.commonTest.nodeTest.NodeTestPool;
import ch.nolix.commonTest.sequencerTest.SequencerTestPool;
import ch.nolix.commonTest.validatorTest.ValidatorTestPool;
import ch.nolix.commonTest.webSocketTest.WebSocketTestPool;
import ch.nolix.elementTest.elementEnumTest.ElementEnumTestPool;
import ch.nolix.systemtest.entitytest.EntityTestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 50
 */
public final class CommonTestPool extends TestPool {
	
	//constructor
	/**
	 * Creates a new {@link CommonTestPool}.
	 */
	public CommonTestPool() {
		super(
			new CachingContainerTestPool(),
			new CommonTypeHelpersTestPool(),
			new CommonTypeWrapperTestPool(),
			new ContainerTestPool(),
			new EntityTestPool(),
			new NodeTestPool(),
			new ElementEnumTestPool(),
			new LicenseTestPool(),
			new MathematicsTestPool(),
			new SequencerTestPool(),
			new ChainedNodeTestPool(),
			new ValidatorTestPool(),
			new WebSocketTestPool(),
			new ch.nolix.commonTest.endPointTest.EndPointTestPool(),
			new ch.nolix.commonTest.endPoint2Test.EndPointTestPool(),
			new ch.nolix.commonTest.endPoint3Test.EndPointTestPool(),
			new ch.nolix.commonTest.endPoint5Test.EndPointTestPool()
		);
	}
}
