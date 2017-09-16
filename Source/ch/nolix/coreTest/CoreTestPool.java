//package declaration
package ch.nolix.coreTest;

//own imports
import ch.nolix.core.baseTest.TestPool;
import ch.nolix.coreTest.containerTest.ContainerTestPool;
import ch.nolix.coreTest.endPointTest.EndPointTestPool;
import ch.nolix.coreTest.enumTest.EnumTestPool;
import ch.nolix.coreTest.mathematicsTest.MathematicsTestPool;
import ch.nolix.coreTest.specificationTest.SpecificationTestPool;
import ch.nolix.coreTest.utilTest.UtilTestPool;
import ch.nolix.elementTest.financeTest.FinanceTestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 30
 */
public final class CoreTestPool extends TestPool {

	//constructor
	/**
	 * Creates new common test pool.
	 */
	public CoreTestPool() {
		addTestPool(
			new ContainerTestPool(),
			new EndPointTestPool(),
			new EnumTestPool(),
			new FinanceTestPool(),
			new MathematicsTestPool(),
			new SpecificationTestPool(),
			new UtilTestPool()
		);
	}
}
