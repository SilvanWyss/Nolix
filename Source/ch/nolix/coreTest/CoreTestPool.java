//package declaration
package ch.nolix.coreTest;

//own imports
import ch.nolix.core.baseTest.TestPool;
import ch.nolix.coreTest.containerTest.ContainerTestPool;
import ch.nolix.coreTest.enumTest.EnumTestPool;
import ch.nolix.coreTest.mathematicsTest.MathematicsTestPool;
import ch.nolix.coreTest.specificationTest.SpecificationTestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 30
 */
public final class CoreTestPool extends TestPool {

	//constructor
	/**
	 * Creates new core test pool.
	 */
	public CoreTestPool() {
		addTestPool(
			new ContainerTestPool(),
			new ch.nolix.coreTest.endPointTest.EndPointTestPool(),
			new ch.nolix.coreTest.endPoint3Test.EndPointTestPool(),
			new EnumTestPool(),
			new MathematicsTestPool(),
			new SpecificationTestPool()
		);
	}
}
