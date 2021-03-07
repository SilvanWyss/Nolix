//package declaration
package ch.nolix.elementtest.tradingtest;

import ch.nolix.common.testing.basetest.TestPool;

//class
/**
 * @author Silvan
 * @date 2016-09-01
 * @lines 20
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
