/*
 * file:	CommonTestPool.java
 * author:	Silvan Wyss
 * month:	2016-08
 * lines.	10
 */

//package declaration
package ch.nolix.commonTest;

//own imports
import ch.nolix.common.helperTest.CharacterHelperTest;
import ch.nolix.common.helperTest.StringHelperTest;
import ch.nolix.common.test.TestPool;
import ch.nolix.commonTest.containerTest.ContainerTestPool;
import ch.nolix.commonTest.financeTest.DataProviderTest;
import ch.nolix.commonTest.financeTest.FinanceTestPool;
import ch.nolix.commonTest.mathematicsTest.MathematicsTestPool;
import ch.nolix.commonTest.specificationTest.SpecificationTest;
import ch.nolix.commonTest.specificationTest.StatementTest;

//class
public final class CommonTestPool extends TestPool {

	public CommonTestPool() {

		//Adds test pools to this common test pool.
		addTestPool(new ContainerTestPool());
		addTestPool(new FinanceTestPool());
		addTestPool(new MathematicsTestPool());

		//TODO
		addTest(new CharacterHelperTest());
		addTest(new DataProviderTest());
		addTest(new SpecificationTest());
		addTest(new StatementTest());
		addTest(new StringHelperTest());
	}
}
