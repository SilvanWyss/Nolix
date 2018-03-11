//package declaration
package ch.nolix.elementTest.financeTest;

import ch.nolix.primitive.testoid.TestPool;

//class
/**
 * @author Silvan
 * @month 2016-08
 * @lines 20
 */
public final class FinanceTestPool extends TestPool {

	//constructor
	/**
	 * Creates a new finance test pool.
	 */
	public FinanceTestPool() {
		addTest(
			new CandleStickTest(),
			new DataProviderTest()
		);
	}
}
