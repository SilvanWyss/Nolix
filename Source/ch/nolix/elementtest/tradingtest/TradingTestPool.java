//package declaration
package ch.nolix.elementtest.tradingtest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
/**
 * @author Silvan
 * @date 2016-09-01
 */
public final class TradingTestPool extends TestPool {
	
	//constructor
	/**
	 * Creates a new {@link TradingTestPool}.
	 */
	public TradingTestPool() {
		super(CandleStickTest.class, VolumeCandleStickTest.class);
	}
}
