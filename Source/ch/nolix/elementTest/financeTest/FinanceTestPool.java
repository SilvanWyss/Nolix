//package declaration
package ch.nolix.elementTest.financeTest;

import ch.nolix.core.baseTest.TestPool;

//class
/**
 * @author Silvan
 * @month 2016-08
 * @lines 20
 */
public final class FinanceTestPool extends TestPool {

	//constructor
	/**
	 * Creates new finance test pool.
	 */
	public FinanceTestPool() {
		addTest(
			new CandleStickTest(),
			new DataProviderTest()
		);
	}
}
