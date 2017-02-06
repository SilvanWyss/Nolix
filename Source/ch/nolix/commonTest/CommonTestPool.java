//package declaration
package ch.nolix.commonTest;

//own imports
import ch.nolix.common.test.TestPool;
import ch.nolix.commonTest.containerTest.ContainerTestPool;
import ch.nolix.commonTest.financeTest.FinanceTestPool;
import ch.nolix.commonTest.mathematicsTest.MathematicsTestPool;
import ch.nolix.commonTest.specificationTest.SpecificationTestPool;
import ch.nolix.commonTest.utilTest.UtilTestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 30
 */
public final class CommonTestPool extends TestPool {

	//constructor
	/**
	 * Creates new common test pool.
	 */
	public CommonTestPool() {
		addTestPool(
			new ContainerTestPool(),
			new FinanceTestPool(),
			new MathematicsTestPool(),
			new SpecificationTestPool(),
			new UtilTestPool()
		);
	}
}
