//package declaration
package ch.nolix.elementTest.financeTest;

//own import
import ch.nolix.core.test.TestPool;

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
