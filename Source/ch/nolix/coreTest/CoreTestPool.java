//package declaration
package ch.nolix.coreTest;

//own imports
import ch.nolix.coreTest.containerTest.ContainerTestPool;
import ch.nolix.coreTest.databaseAdapter.DatabaseAdapterTestPool;
import ch.nolix.coreTest.enumTest.EnumTestPool;
import ch.nolix.coreTest.helperTest.HelperTestPool;
import ch.nolix.coreTest.mathematicsTest.MathematicsTestPool;
import ch.nolix.coreTest.sequencerTest.SequencerTestPool;
import ch.nolix.coreTest.specificationTest.SpecificationTestPool;
import ch.nolix.coreTest.utilTest.UtilTestPool;
import ch.nolix.primitive.testoid.TestPool;

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
			new SpecificationTestPool(),
			new UtilTestPool()
		);
	}
}
