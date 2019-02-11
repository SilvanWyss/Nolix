//package declaration
package ch.nolix.coreTest;

//own imports
import ch.nolix.core.testoid.TestPool;
import ch.nolix.coreTest.containerTest.ContainerTestPool;
import ch.nolix.coreTest.documentNodeTest.DocumentNodeTestPool;
import ch.nolix.coreTest.enumTest.EnumTestPool;
import ch.nolix.coreTest.helperTest.HelperTestPool;
import ch.nolix.coreTest.mathematicsTest.MathematicsTestPool;
import ch.nolix.coreTest.sequencerTest.SequencerTestPool;
import ch.nolix.coreTest.utilTest.UtilTestPool;
import ch.nolix.coreTest.validatorTest.ValidatorTestPool;
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
			new ch.nolix.coreTest.endPointTest.EndPointTestPool(),
			new ch.nolix.coreTest.endPoint3Test.EndPointTestPool(),
			new EnumTestPool(),
			new HelperTestPool(),
			new MathematicsTestPool(),
			new SequencerTestPool(),
			new DocumentNodeTestPool(),
			new UtilTestPool(),
			new ValidatorTestPool()
		);
	}
}
