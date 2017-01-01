package ch.nolix.commonTest.financeTest;

import ch.nolix.common.test.TestPool;

public class FinanceTestPool extends TestPool {

	public FinanceTestPool() {
		addTest(new CandleStickTest());
		addTest(new DataProviderTest());
	}
}
