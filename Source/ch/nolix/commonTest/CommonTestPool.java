//package declaration
package ch.nolix.commonTest;

import ch.nolix.common.baseTest.TestPool;
import ch.nolix.commonTest.chainedNodeTest.ChainedNodeTestPool;
import ch.nolix.commonTest.commonTypeHelpersTest.CommonTypeHelpersTestPool;
import ch.nolix.commonTest.containersTest.ContainerTestPool;
import ch.nolix.commonTest.licenseTest.LicenseTestPool;
import ch.nolix.commonTest.mathTest.MathematicsTestPool;
import ch.nolix.commonTest.nodeTest.NodeTestPool;
import ch.nolix.commonTest.sequencerTest.SequencerTestPool;
import ch.nolix.commonTest.validatorTest.ValidatorTestPool;
import ch.nolix.commonTest.webSocketTest.WebSocketTestPool;
import ch.nolix.elementTest.elementEnumsTest.ElementEnumTestPool;
import ch.nolix.systemTest.databaseAdapterTest.DatabaseAdapterTestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 40
 */
public final class CommonTestPool extends TestPool {
	
	//constructor
	/**
	 * Creates a new {@link CommonTestPool}.
	 */
	public CommonTestPool() {
		addTestPool(
			new ContainerTestPool(),
			new DatabaseAdapterTestPool(),
			new NodeTestPool(),
			new ch.nolix.commonTest.endPointTest.EndPointTestPool(),
			new ch.nolix.commonTest.endPoint2Test.EndPointTestPool(),
			new ch.nolix.commonTest.endPoint3Test.EndPointTestPool(),
			new ElementEnumTestPool(),
			new CommonTypeHelpersTestPool(),
			new LicenseTestPool(),
			new MathematicsTestPool(),
			new SequencerTestPool(),
			new ChainedNodeTestPool(),
			new ValidatorTestPool(),
			new WebSocketTestPool()
		);
	}
}
