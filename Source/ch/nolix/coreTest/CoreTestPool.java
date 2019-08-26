//package declaration
package ch.nolix.coreTest;

//own imports
import ch.nolix.core.baseTest.TestPool;
import ch.nolix.coreTest.chainedNodeTest.ChainedNodeTestPool;
import ch.nolix.coreTest.commonTypesHelperTest.CommonTypeHelpersTestPool;
import ch.nolix.coreTest.containersTest.ContainerTestPool;
import ch.nolix.coreTest.licenseTest.LicenseTestPool;
import ch.nolix.coreTest.mathTest.MathematicsTestPool;
import ch.nolix.coreTest.nodeTest.NodeTestPool;
import ch.nolix.coreTest.sequencerTest.SequencerTestPool;
import ch.nolix.coreTest.validatorTest.ValidatorTestPool;
import ch.nolix.coreTest.webSocketTest.WebSocketTestPool;
import ch.nolix.elementTest.elementEnumsTest.ElementEnumTestPool;
import ch.nolix.systemTest.databaseAdapterTest.DatabaseAdapterTestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 40
 */
public final class CoreTestPool extends TestPool {
	
	//constructor
	/**
	 * Creates a new {@link CoreTestPool}.
	 */
	public CoreTestPool() {
		addTestPool(
			new ContainerTestPool(),
			new DatabaseAdapterTestPool(),
			new NodeTestPool(),
			new ch.nolix.coreTest.endPointTest.EndPointTestPool(),
			new ch.nolix.coreTest.endPoint2Test.EndPointTestPool(),
			new ch.nolix.coreTest.endPoint3Test.EndPointTestPool(),
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
