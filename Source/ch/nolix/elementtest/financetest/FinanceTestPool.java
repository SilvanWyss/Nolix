//package declaration
package ch.nolix.elementtest.financetest;

//own import
import ch.nolix.common.basetest.TestPool;

//class
/**
 * @author Silvan
 * @date 2016-09-01
 * @lines 20
 */
public final class FinanceTestPool extends TestPool {
	
	//constructor
	/**
	 * Creates a new {@link FinanceTestPool}.
	 */
	public FinanceTestPool() {
		super(CandleStickTest.class, VolumeCandleStickTest.class);
	}
}
